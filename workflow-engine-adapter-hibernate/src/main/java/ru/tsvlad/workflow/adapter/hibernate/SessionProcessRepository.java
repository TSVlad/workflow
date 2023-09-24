package ru.tsvlad.workflow.adapter.hibernate;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.tsvlad.workflow.common.ProcessStatus;

import java.util.List;

@RequiredArgsConstructor
public class SessionProcessRepository implements ProcessRepository {
//    @PersistenceContext
    private final SessionFactory sessionFactory;

    @Override
    public List<ProcessEntity> getProcessesByState(ProcessStatus processStatus, int limit, LockModeType lockModeType) {
        Session session = sessionFactory.getCurrentSession();
        return session.createSelectionQuery("FROM ProcessEntity p WHERE p.status = :status", ProcessEntity.class)
                .setParameter("status", processStatus)
                .setMaxResults(limit)
                .setLockMode(lockModeType)
                .getResultList();
    }

    @Override
    public void updateProcess(ProcessEntity process) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(process);
    }

    @Override
    public void createProcess(ProcessEntity process) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(process);
    }
}
