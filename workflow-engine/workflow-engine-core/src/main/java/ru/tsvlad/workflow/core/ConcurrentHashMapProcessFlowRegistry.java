package ru.tsvlad.workflow.core;

import ru.tsvlad.workflow.common.ProcessFlow;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapProcessFlowRegistry<T> implements ProcessFlowRegistry<T> {
    private ConcurrentHashMap<String, ProcessFlow<T>> nameToProcessFlow = new ConcurrentHashMap<>();


    @Override
    public void addProcessFlow(ProcessFlow<T> processFlow) {
        nameToProcessFlow.put(processFlow.getName(), processFlow);
    }

    @Override
    public void addProcessFlows(Iterable<ProcessFlow<T>> processFlows) {
        for (ProcessFlow<T> processFlow : processFlows) {
            addProcessFlow(processFlow);
        }
    }

    @Override
    public Optional<ProcessFlow<T>> getProcessFlowByName(String name) {
        return Optional.ofNullable(nameToProcessFlow.get(name));
    }
}
