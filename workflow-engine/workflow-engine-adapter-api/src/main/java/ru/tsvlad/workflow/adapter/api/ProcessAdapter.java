package ru.tsvlad.workflow.adapter.api;

import ru.tsvlad.workflow.common.Process;

import java.util.List;

public interface ProcessAdapter {
    String createProcess(String processName, String paramsJson, Class<?> paramClass);
    List<Process> getProcessesForQueue(String nodeName, int maxNumber);
}
