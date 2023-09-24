package ru.tsvlad.workflow.workflowdemo.process.action;

import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessContext;
import ru.tsvlad.workflow.common.Step;
import ru.tsvlad.workflow.workflowdemo.process.param.SecondParams;

@Component
public class Action4 implements Step<SecondParams> {
    @Override
    public ProcessContext<SecondParams> execute(ProcessContext<SecondParams> processContext) {
        int num1 = processContext.getParams().getNum1();
        System.out.println(num1);
        return processContext.withResult("SUCCESS");
    }
}
