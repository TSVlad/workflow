package ru.tsvlad.workflow.adapter.hibernate;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.tsvlad.workflow.common.Process;

@Mapper
public interface ProcessMapper {

    @Mapping(target = "id", expression = "java(process.getId().toString())")
    Process entityToProcess(ProcessEntity process);
}
