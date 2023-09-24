package ru.tsvlad.workflow.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ru.tsvlad.workflow.adapter.api.ProcessAdapter;

@RequiredArgsConstructor
public class CoreProcessApi implements ProcessApi {

    private final ProcessAdapter processAdapter;

    @Override
    public String createProcess(String processName, String params, Class<?> paramClass) {
        return processAdapter.createProcess(processName, params, paramClass);
    }
}
