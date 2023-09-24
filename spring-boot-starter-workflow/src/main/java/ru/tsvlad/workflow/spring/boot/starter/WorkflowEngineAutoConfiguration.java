package ru.tsvlad.workflow.spring.boot.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import ru.tsvlad.workflow.adapter.api.ProcessAdapter;
import ru.tsvlad.workflow.common.ProcessDescription;
import ru.tsvlad.workflow.core.*;

import java.util.List;

@AutoConfiguration
public class WorkflowEngineAutoConfiguration<T> {
	@Bean
	public WorkflowEngine<T> engine(ProcessFlowRegistry<T> processFlowRegistry, ProcessAdapter processAdapter,
									WorkflowProperties workflowProperties, PropertiesMapper propertiesMapper,
									ObjectMapper objectMapper, List<StepErrorHandler> errorHandlers) {
		WorkflowEngine<T> engine = new WorkflowEngine<>(processFlowRegistry, processAdapter,
				propertiesMapper.springToEngineProperties(workflowProperties), objectMapper, errorHandlers);
		engine.startProcessConsuming();
		engine.startProcessExecuting();
		return engine;
	}

	@Bean
	public ProcessApi processApi(ProcessAdapter processAdapter) {
		return new CoreProcessApi(processAdapter);
	}

	@Bean
	public ProcessFlowRegistry<T> processFlowRegistry(List<ProcessDescription<T>> processDescriptionList) {
		ConcurrentHashMapProcessFlowRegistry<T> registry = new ConcurrentHashMapProcessFlowRegistry<>();
		for (ProcessDescription<T> processDescription : processDescriptionList) {
			registry.addProcessFlow(processDescription.getFlow());
		}
		return registry;
	}

	@Bean
	public WorkflowProperties workflowProperties() {
		return new WorkflowProperties();
	}

	@Bean
	@ConditionalOnMissingBean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
}
