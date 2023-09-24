package ru.tsvlad.workflow.core;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Builder
@Getter
public class WorkflowEngineProperties {
    @NonNull
    int processQueueMaxSize;
    @NonNull
    int processConsumingDelay;
    @NonNull
    int processConsumingInitialDelay;
    @NonNull
    String nodeName;
    @NonNull
    int processExecutingThreadsNumber;
}
