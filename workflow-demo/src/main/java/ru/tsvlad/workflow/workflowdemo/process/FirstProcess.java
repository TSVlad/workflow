package ru.tsvlad.workflow.workflowdemo.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessFlow;
import ru.tsvlad.workflow.common.ProcessFlowNode;
import ru.tsvlad.workflow.workflowdemo.process.action.Action1;
import ru.tsvlad.workflow.workflowdemo.process.action.Action2;
import ru.tsvlad.workflow.workflowdemo.process.param.ProcessParams;

@Component
@RequiredArgsConstructor
public class FirstProcess implements ParentProcess{

    private final Action1 action1;
    private final Action2 action2;

    @Override
    public ProcessFlow<ProcessParams> getFlow() {

        ProcessFlowNode<ProcessParams> node1 = new ProcessFlowNode<>(action1);
        ProcessFlowNode<ProcessParams> node2 = new ProcessFlowNode<>(action2);

        ProcessFlow<ProcessParams> processFlow = new ProcessFlow<ProcessParams>("FirstProcess", node1);
        node1.addLink("SUCCESS", node2);
        return processFlow;
    }
}
