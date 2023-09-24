package ru.tsvlad.workflow.workflowdemo.process.action;

import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.workflowdemo.process.param.ProcessParams;

@Component
public class Action2 implements ParentAction {
    @Override
    public ProcessContext<ProcessParams> execute(ProcessContext<ProcessParams> processContext) {
        int num2 = processContext.getParams().getSomeObject().getNum2();
        System.out.println(num2);
        throw new RuntimeException();
//        return processContext.withResult("SUCCESS");
    }
}
