package ru.tsvlad.workflow.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class ProcessFlowNode<T> {
    @Getter
    private final Step<T> step;
    private final Map<String, ProcessFlowNode<T>> links = new HashMap<>();

    public void addLink(String result, ProcessFlowNode<T> node) {
        links.put(result, node);
    }

    public ProcessFlowNode<T> getNext(String result) {
        return links.get(result);
    }
    public boolean hasLinks() {
        return !links.isEmpty();
    }

    public Map<String, ProcessFlowNode<T>> getLinks() {
        return links;
    }
}
