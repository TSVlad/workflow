package ru.tsvlad.workflow.workflowdemo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsvlad.workflow.common.Process;
import ru.tsvlad.workflow.core.ProcessApi;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {

    private final ProcessApi processApi;

    @PostMapping
    public void createProcess(@RequestBody Process process) {
        processApi.createProcess(process.getProcessName(), process.getParams(), process.getParamClass());
    }

    @PostMapping("/{num}")
    public void createNProcesses(@PathVariable int num, @RequestBody Process process) {
        for (int i = 0; i < num; i++) {
            processApi.createProcess(process.getProcessName(), process.getParams(), process.getParamClass());
        }
    }
}
