package ru.tsvlad.workflow.adapter.hibernate.spring.boot.starter;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import ru.tsvlad.workflow.adapter.api.ProcessAdapter;
import ru.tsvlad.workflow.adapter.hibernate.*;

@AutoConfiguration
public class HibernateWorkflowAdapterAutoConfiguration {

    @Bean
    public ProcessAdapter processAdapter(ProcessRepository processRepository, SessionFactory sessionFactory) {
        return new HibernateProcessAdapter(processRepository, new ProcessMapperImpl(), sessionFactory);
    }

    @Bean
    public ProcessRepository processRepository(SessionFactory sessionFactory) {
        return new SessionProcessRepository(sessionFactory);
    }

    @Bean
    public SessionFactory sessionFactory(DatabaseProperties databaseProperties) {
        return new Configuration()
                .addAnnotatedClass(ProcessEntity.class)
                .setProperty(AvailableSettings.URL, PropertiesConstants.JDBC_POSTGRES_SCHEMA + databaseProperties.getHost() + ":" + databaseProperties.getPort() + "/" + databaseProperties.getName())
                .setProperty(AvailableSettings.USER, databaseProperties.getUser())
                .setProperty(AvailableSettings.PASS, databaseProperties.getPassword())
                .setProperty(AvailableSettings.DRIVER, databaseProperties.getDriver())
                .setProperty(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                .buildSessionFactory();
    }


    /*@Bean
    public EntityManager entityManager(@Qualifier("workflowAdapterEntityManagerFactory")
                                       LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        return entityManagerFactory.getObject().createEntityManager();
    }

    @Bean("workflowAdapterDataSource")
    public DataSource datasource(DatabaseProperties databaseProperties) {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                databaseProperties.getDriver());
        dataSource.setUrl(PropertiesConstants.JDBC_POSTGRES_SCHEMA + databaseProperties.getHost() + ":" + databaseProperties.getPort() + "/" + databaseProperties.getName());
        dataSource.setUsername(databaseProperties.getUser());
        dataSource.setPassword(databaseProperties.getPassword());
        return dataSource;
    }*/

    /*@Bean("workflowAdapterTransactionManager")
    public PlatformTransactionManager transactionManager(
            @Qualifier("workflowAdapterEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean) {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                entityManagerFactoryBean.getObject());
        return transactionManager;
    }

    @Bean("workflowAdapterEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("workflowAdapterDataSource") DataSource datasource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(datasource);
        em.setPackagesToScan("ru.tsvlad.workflow.adapter.hibernate");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        *//*properties.put("hibernate.hbm2ddl.auto",
                env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect",
                env.getProperty("hibernate.dialect"));*//*
        em.setJpaPropertyMap(properties);
        return em;
    }*/
}
