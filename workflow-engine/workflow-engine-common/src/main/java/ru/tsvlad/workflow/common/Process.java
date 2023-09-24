package ru.tsvlad.workflow.common;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Setter
public class Process {
    private String id;
    private String idempotenceId;
    private String processName;
    private ProcessStatus status;
    private ZonedDateTime created;
    private String params;

    private Class<?> paramClass;
}
