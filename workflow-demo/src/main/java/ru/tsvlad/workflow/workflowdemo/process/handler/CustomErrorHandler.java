package ru.tsvlad.workflow.workflowdemo.process.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.common.Step;
import ru.tsvlad.workflow.core.StepErrorHandler;
import ru.tsvlad.workflow.workflowdemo.process.param.ProcessParams;

@Component
@Slf4j
public class CustomErrorHandler implements StepErrorHandler {
    @Override
    public <T> void handleStepError(Step<T> step, Exception e, ProcessContext<T> context) {
        if (context.getParamClass() == ProcessParams.class) {
            log.info("Error handling: {}", step.getClass().getSimpleName());
            ProcessParams params = (ProcessParams) context.getParams();
            // some handling
        }
    }
}
