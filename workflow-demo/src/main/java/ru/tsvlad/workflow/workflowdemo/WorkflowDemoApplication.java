package ru.tsvlad.workflow.workflowdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import ru.tsvlad.workflow.workflowdemo.process.param.ProcessParams;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WorkflowDemoApplication {

	@SneakyThrows
	public static void main(String[] args) {
		SpringApplication.run(WorkflowDemoApplication.class, args);
	}

}
