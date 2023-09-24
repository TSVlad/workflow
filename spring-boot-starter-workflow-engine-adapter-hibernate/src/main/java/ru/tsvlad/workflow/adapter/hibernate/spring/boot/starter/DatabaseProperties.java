package ru.tsvlad.workflow.adapter.hibernate.spring.boot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = PropertiesConstants.DATABASE_PROPERTIES_PREFIX)
@Data
public class DatabaseProperties {
    private String host;
    private String port;
    private String name;
    private String user;
    private String password;
    private String driver;
}
