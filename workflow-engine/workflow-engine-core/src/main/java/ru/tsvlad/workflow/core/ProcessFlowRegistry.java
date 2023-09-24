package ru.tsvlad.workflow.core;

import ru.tsvlad.workflow.common.ProcessFlow;

import java.util.Optional;

public interface ProcessFlowRegistry <T> {
    void addProcessFlow(ProcessFlow<T> processFlow);
    void addProcessFlows(Iterable<ProcessFlow<T>> processFlows);
    Optional<ProcessFlow<T>> getProcessFlowByName(String name);
}
