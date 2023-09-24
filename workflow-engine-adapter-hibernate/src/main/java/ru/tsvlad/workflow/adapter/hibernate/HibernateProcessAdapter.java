package ru.tsvlad.workflow.adapter.hibernate;

import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tsvlad.workflow.adapter.api.ProcessAdapter;
import ru.tsvlad.workflow.common.Process;
import ru.tsvlad.workflow.common.ProcessStatus;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class HibernateProcessAdapter implements ProcessAdapter {

    private final ProcessRepository processRepository;
    private final ProcessMapper processMapper;
    private final SessionFactory sessionFactory;

    @Override
    public String createProcess(String processName, String paramsJson, Class<?> paramClass) {
        UUID id = UUID.randomUUID();
        ProcessEntity processEntity = new ProcessEntity();
        processEntity.setId(id);
        processEntity.setStatus(ProcessStatus.CREATED);
        processEntity.setParamClass(paramClass);
        processEntity.setParams(paramsJson);
        processEntity.setProcessName(processName);
        processEntity.setIdempotenceId(id.toString());

        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        try {
            processRepository.createProcess(processEntity);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }

        transaction.commit();

        return id.toString();
    }

    @Override
    public List<Process> getProcessesForQueue(String nodeName, int limit) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();

        List<ProcessEntity> processes;
        try {
            processes = processRepository.getProcessesByState(ProcessStatus.CREATED, limit, LockModeType.PESSIMISTIC_WRITE);
            for (ProcessEntity process : processes) {
                process.setStatus(ProcessStatus.QUEUED);
                processRepository.updateProcess(process);
            }
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }


        transaction.commit();

        return processes.stream().map(processMapper::entityToProcess).collect(Collectors.toList());
    }
}
