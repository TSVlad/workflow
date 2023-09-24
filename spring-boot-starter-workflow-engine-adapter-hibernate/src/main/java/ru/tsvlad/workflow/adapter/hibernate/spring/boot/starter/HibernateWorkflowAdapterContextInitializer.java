package ru.tsvlad.workflow.adapter.hibernate.spring.boot.starter;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.PropertiesPropertySource;

import java.util.Properties;

import static ru.tsvlad.workflow.adapter.hibernate.spring.boot.starter.PropertiesConstants.*;

public class HibernateWorkflowAdapterContextInitializer implements ApplicationContextInitializer {

    private final String ADAPTER_MIGRATION_PATH = "ru/tsvlad/workflow/adapter/hibernate/db/migration";

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        String databaseName = applicationContext.getEnvironment().getProperty(addDatabasePrefix(NAME));
        String databaseHost = applicationContext.getEnvironment().getProperty(addDatabasePrefix(HOST));
        String databasePort = applicationContext.getEnvironment().getProperty(addDatabasePrefix(PORT));
        String databaseUser = applicationContext.getEnvironment().getProperty(addDatabasePrefix(USER));
        String databasePassword = applicationContext.getEnvironment().getProperty(addDatabasePrefix(PASSWORD));

        Properties properties = new Properties();
        properties.put(addFlywayPrefix(ENABLED), true);
        properties.put(addFlywayPrefix(USER), databaseUser);
        properties.put(addFlywayPrefix(PASSWORD), databasePassword);
        properties.put(addFlywayPrefix(URL), addJdbcPostgresSchema(databaseHost + ":" + databasePort + "/" + databaseName));
        properties.put(addFlywayPrefix(LOCATIONS), ADAPTER_MIGRATION_PATH);

        applicationContext.getEnvironment().getPropertySources().addFirst(new PropertiesPropertySource("from-workflow-adapter", properties));

    }

    private String addDatabasePrefix(String propertyName) {
        return DATABASE_PROPERTIES_PREFIX + "." + propertyName;
    }

    private String addFlywayPrefix(String propertyName) {
        return FLYWAY_PROPERTIES_PREFIX + "." + propertyName;
    }
    private String addJdbcPostgresSchema(String host) {
        return JDBC_POSTGRES_SCHEMA + host;
    }

}
