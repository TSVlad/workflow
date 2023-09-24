package ru.tsvlad.workflow.spring.boot.starter;

import org.mapstruct.Mapper;
import ru.tsvlad.workflow.core.WorkflowEngineProperties;

@Mapper(componentModel = "spring")
public interface PropertiesMapper {
    WorkflowEngineProperties springToEngineProperties(WorkflowProperties workflowProperties);
}
