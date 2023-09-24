package ru.tsvlad.workflow.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class ProcessFlow<T> {
    private final String name;
    private final ProcessFlowNode<T> startNode;
}
