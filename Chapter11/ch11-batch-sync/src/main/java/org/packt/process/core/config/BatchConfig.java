package org.packt.process.core.config;

import java.util.Arrays;

import org.packt.process.core.model.data.Department;
import org.packt.process.core.processor.DeptNameProcessor;
import org.packt.process.core.processor.DeptIDValidProcesor;
import org.packt.process.core.reader.DepartmentItemReader;
import org.packt.process.core.writer.DepartmentItemWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class BatchConfig {
	
	    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);
	    
	    @Autowired
	    private JobBuilderFactory jobCreators;

	    @Autowired
	    private StepBuilderFactory stepCreators;
	    
	    @Autowired
	    private JobLauncher jobLauncher;
	     
	   // Jobs and Steps Configurations
		
	    @Bean
	    public Job deptBatchJob() {
	        return jobCreators.get("deptBatchJob")
	           .start(taskletStep())
	           .next(chunkStep())
	            .build();
	    }

	    @Bean
	    public Step taskletStep() {
	        return stepCreators.get("taskletStep")
	            .tasklet(tasklet())
	            .build();
	    }

	   
	    @Bean
	    public Tasklet tasklet() {
	        return (contrib, chunkCtx) -> {
	            return RepeatStatus.FINISHED;
	        };
	    }
	    
	    @Bean
	    public Step chunkStep() {
	        return stepCreators.get("chunkStep")
	            .<Department, Department>chunk(5)
	            .reader(reader())
	            .processor(processor())
	            .writer(writer())
	            .build();
	    }
	    
	    // Reader, Writer and Processors
	    
	    @StepScope
	    @Bean
	    public ItemReader<Department> reader() {
	        return new DepartmentItemReader("depts.xml");
	    }
	    
	   // Option B for ItemReader implementation that does not explicit @StepScope
	   /*
	    @Bean
	    ItemReader<Department> reader() {
	        StaxEventItemReader<Department> xmlFileReader = new StaxEventItemReader<>();
	        xmlFileReader.setResource(new ClassPathResource("depts.xml"));
	        xmlFileReader.setFragmentRootElementName("department");
	 
	        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
	        studentMarshaller.setClassesToBeBound(Department.class);
	        xmlFileReader.setUnmarshaller(studentMarshaller);
	 
	        return xmlFileReader;
	    }
	    
	    */
	    
	    @StepScope
	    @Bean
	    public ItemProcessor<Department, Department> processor() {
	        CompositeItemProcessor<Department, Department> processor = new CompositeItemProcessor<>();
	        processor.setDelegates(Arrays.asList(new DeptNameProcessor(), new DeptIDValidProcesor()));
	        return processor;
	    }
	    
	    @StepScope
	    @Bean
	    public ItemWriter<Department> writer() {
	        return new DepartmentItemWriter();
	    }
	    
	    
		 
		@Scheduled(fixedRate = 5000)
		public void startJob() throws Exception {
		    JobExecution execution = jobLauncher.run(
		        deptBatchJob(),
		        new JobParametersBuilder().addLong("procId", System.nanoTime()).toJobParameters()
		    );
		}
	
}
