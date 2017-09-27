package org.packt.process.core.config;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.packt.process.core.model.data.Department;
import org.packt.process.core.processor.DeptIDValidProcesor;
import org.packt.process.core.processor.DeptNameProcessor;
import org.packt.process.core.reader.DepartmentItemReader;
import org.packt.process.core.writer.DepartmentItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.integration.async.AsyncItemProcessor;
import org.springframework.batch.integration.async.AsyncItemWriter;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskDecorator;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@Configuration
public class BatchConfig {
	
	    
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
	            .taskExecutor(getAsyncExecutor())
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
	            .taskExecutor(getAsyncExecutor())
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
	
	  @Bean("mvcTaskexecutor")
	  public TaskExecutor getAsyncExecutor() {
			ConcurrentTaskExecutor executor = new ConcurrentTaskExecutor(
                  Executors.newFixedThreadPool(100));
	
			executor.setTaskDecorator(new TaskDecorator() {
            @Override
            public Runnable decorate (Runnable runnable) {
                return () -> {
   
                    long t = System.currentTimeMillis();
                    runnable.run();
                    System.out.printf("Thread %s has a processing time:  %s%n", Thread.currentThread().getName(),       
                                      (System.currentTimeMillis() - t));
                };
            }
        });
			return executor;
	}


}
