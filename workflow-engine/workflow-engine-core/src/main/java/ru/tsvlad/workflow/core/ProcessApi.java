package ru.tsvlad.workflow.core;

import ru.tsvlad.workflow.common.Process;

public interface ProcessApi {
    String createProcess(String processName, String params, Class<?> paramClass);
}
