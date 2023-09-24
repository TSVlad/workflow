package ru.tsvlad.workflow.adapter.hibernate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import ru.tsvlad.workflow.common.ProcessStatus;

import java.time.ZonedDateTime;
import java.util.UUID;

@Entity
@Table(name = "processes")
@Getter
@Setter
public class ProcessEntity {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "idempotence_id")
    private String idempotenceId;
    @Column(name = "process_name")
    private String processName;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProcessStatus status;
    @Column(name = "created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private ZonedDateTime created;
    @Column(name = "params")
    private String params;
    @Column(name = "paramClass")

    private Class<?> paramClass;
}
