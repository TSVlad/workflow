package ru.tsvlad.workflow.workflowdemo.process;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.tsvlad.workflow.common.ProcessDescription;
import ru.tsvlad.workflow.common.ProcessFlow;
import ru.tsvlad.workflow.common.ProcessFlowNode;
import ru.tsvlad.workflow.workflowdemo.process.action.Action3;
import ru.tsvlad.workflow.workflowdemo.process.action.Action4;
import ru.tsvlad.workflow.workflowdemo.process.param.SecondParams;

@Component
@RequiredArgsConstructor
public class SecondProcess implements ProcessDescription<SecondParams> {

    private final Action3 action3;
    private final Action4 action4;

    @Override
    public ProcessFlow<SecondParams> getFlow() {
        ProcessFlowNode<SecondParams> node3 = new ProcessFlowNode<>(action3);
        ProcessFlowNode<SecondParams> node4 = new ProcessFlowNode<>(action4);
        ProcessFlow<SecondParams> processFlow = new ProcessFlow<>("SecondProcess", node3);
        node3.addLink("SUCCESS", node4);
        return processFlow;
    }
}
