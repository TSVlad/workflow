package ru.tsvlad.workflow.workflowdemo.process.action;

import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.common.Step;
import ru.tsvlad.workflow.workflowdemo.process.param.SecondParams;

@Component
public class Action3 implements Step<SecondParams> {
    @Override
    public ProcessContext<SecondParams> execute(ProcessContext<SecondParams> processContext) {
        return processContext.withResult("SUCCESS");
    }
}
