package ru.tsvlad.workflow.workflowdemo.process.action;

import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.workflowdemo.process.param.ProcessParams;

@Component
public class Action1 implements ParentAction {
    @Override
    public ProcessContext<ProcessParams> execute(ProcessContext<ProcessParams> processContext) {
        return processContext.withResult("SUCCESS");
    }
}
