package ru.tsvlad.workflow.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProcessContext<T> {
    private String processId;
    private T params;
    private String lastResult;
    private Class<?> paramClass;

    public ProcessContext<T> withResult(String result) {
        lastResult = result;
        return this;
    }
}
