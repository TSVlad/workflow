package ru.tsvlad.workflow.adapter.hibernate;

import jakarta.persistence.LockModeType;
import ru.tsvlad.workflow.common.ProcessStatus;

import java.util.List;

public interface ProcessRepository {
    List<ProcessEntity> getProcessesByState(ProcessStatus processStatus, int limit, LockModeType lockModeType);
    void updateProcess(ProcessEntity process);

    void createProcess(ProcessEntity process);
}
