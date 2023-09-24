package ru.tsvlad.workflow.spring.boot.starter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "workflow.engine")
@Data
public class WorkflowProperties {
    Integer processQueueMaxSize = 10;
    Integer processConsumingDelay = 500;
    Integer processConsumingInitialDelay = 10000;
    String nodeName;
    Integer processExecutingThreadsNumber = 10;
}
