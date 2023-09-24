package ru.tsvlad.workflow.core;

import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.common.Step;

public interface StepErrorHandler {
    <T> void handleStepError(Step<T> step, Exception e, ProcessContext<T> processContext);
}
