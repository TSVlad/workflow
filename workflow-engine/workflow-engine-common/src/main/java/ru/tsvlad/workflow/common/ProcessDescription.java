package ru.tsvlad.workflow.common;

public interface ProcessDescription <T> {
    ProcessFlow<T> getFlow();
}
