package ru.tsvlad.workflow.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import ru.tsvlad.workflow.adapter.api.ProcessAdapter;
import ru.tsvlad.workflow.common.Process;
import ru.tsvlad.workflow.common.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.*;

@Slf4j
public class WorkflowEngine<T> {
    private final ProcessFlowRegistry<T> processFlowRegistry;
    private final ProcessAdapter processAdapter;
    private final WorkflowEngineProperties workflowEngineProperties;

    private final BlockingQueue<Process> processQueue;
    private final ObjectMapper objectMapper;

    private final ScheduledExecutorService consumerExecutorService;
    private final ExecutorService executorService;
    private final List<StepErrorHandler> stepErrorHandlers;

    public WorkflowEngine(ProcessFlowRegistry<T> processFlowRegistry, ProcessAdapter processAdapter,
                          WorkflowEngineProperties workflowEngineProperties, ObjectMapper objectMapper,
                          List<StepErrorHandler> stepErrorHandlers) {
        this.processFlowRegistry = processFlowRegistry;
        this.processAdapter = processAdapter;
        this.workflowEngineProperties = workflowEngineProperties;
        this.objectMapper = objectMapper;

        this.processQueue = new LinkedBlockingQueue<>(workflowEngineProperties.getProcessQueueMaxSize());
        this.stepErrorHandlers = stepErrorHandlers;
        this.consumerExecutorService = Executors.newSingleThreadScheduledExecutor();
        this.executorService = Executors.newFixedThreadPool(workflowEngineProperties.getProcessExecutingThreadsNumber());
    }

    public void start() {
        startProcessConsuming();
        startProcessExecuting();
    }

    public void startProcessConsuming() {
        log.info("Starting process consuming task");
        ScheduledFuture<?> scheduledFuture = consumerExecutorService.scheduleWithFixedDelay(() -> {
            try {
                int processNumber = workflowEngineProperties.getProcessQueueMaxSize() - processQueue.size();
                log.debug("Requesting {} processes for queue", processNumber);
                List<Process> processes = processAdapter.getProcessesForQueue(
                        workflowEngineProperties.getNodeName(), processNumber);
                log.debug("Got {} processes for queue", processes.size());
                processQueue.addAll(processes);
            } catch (Exception e) {
                log.error("Error: ", e);
            }

                },
                this.workflowEngineProperties.getProcessConsumingDelay(),
                this.workflowEngineProperties.getProcessConsumingDelay(),
                TimeUnit.MILLISECONDS);
    }

    public void startProcessExecuting() {
        log.info("Starting process executing tasks");
        for (int i = 0; i < workflowEngineProperties.getProcessExecutingThreadsNumber(); i++) {
            executorService.submit(() -> {
                while (true) { //TODO: добавить условие и метод остановки
                    try {
                        Process process = processQueue.take();
                        execute(process);
                    } catch (Exception e) {
                        log.error("Error: ", e);
                    }
                }
            });
        }
    }


    private void execute(Process process) {
        log.debug("Starting process {} {} execution", process.getId(), process.getProcessName());
        Optional<ProcessFlow<T>> flowOptional = processFlowRegistry.getProcessFlowByName(process.getProcessName());
        if (flowOptional.isPresent()) {
            T params = null;
            try {
                params = (T) objectMapper.readValue(process.getParams(), process.getParamClass());
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            ProcessContext<T> processContext = new ProcessContext<T>(UUID.randomUUID().toString(), params, null,
                    process.getParamClass());
            ProcessFlowNode<T> curNode = flowOptional.get().getStartNode();
            while (curNode != null) {
                log.debug("Starting step {} in process {} {}", curNode.getStep().getClass().getSimpleName(),
                        process.getId(), process.getProcessName());
                ProcessContext<T> contextAfter = null;
                contextAfter = curNode.getStep().beforeExecute(processContext);
                try {

                    contextAfter = curNode.getStep().execute(contextAfter);

                } catch (Exception e) {
                    log.error("Error while executing step: ", e);
                    for (StepErrorHandler stepErrorHandler : stepErrorHandlers) {
                        stepErrorHandler.handleStepError(curNode.getStep(), e, contextAfter);
                    }
                    return;
                }
                contextAfter = curNode.getStep().afterExecute(contextAfter);

                curNode = curNode.getLinks().get(contextAfter.getLastResult());
            }
            log.debug("Finished process {} {} execution", process.getId(), process.getProcessName());
        } else {
            throw new RuntimeException(String.format("Process %s not found", process.getProcessName()));
        }
    }
}
