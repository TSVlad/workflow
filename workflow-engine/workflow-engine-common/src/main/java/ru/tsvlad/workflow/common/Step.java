package ru.tsvlad.workflow.common;

public interface Step<T> {
    default ProcessContext<T> beforeExecute(ProcessContext<T> processContext) {
        return processContext;
    }
    ProcessContext<T> execute(ProcessContext<T> processContext);

    default ProcessContext<T> afterExecute(ProcessContext<T> processContext) {
        return processContext;
    }
}
