package org.packt.process.core.config;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.xml.bind.JAXBContext;

import org.packt.process.core.model.data.Employee;
import org.packt.process.core.model.data.Permanent;
import org.packt.process.core.processor.RecordProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemPreparedStatementSetter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
public class BatchConfig {
	private static final Logger log = LoggerFactory.getLogger(BatchConfig.class);
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public BatchConfig(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
			
	@Bean
    public ItemReader<Employee> reader(DataSource dataSource) {
        JdbcCursorItemReader<Employee> reader = new JdbcCursorItemReader<Employee>();
        reader.setSql("select * from employee");
        reader.setDataSource(dataSource);
        reader.setRowMapper(
                (ResultSet resultSet, int rowNum) -> {
                    log.info("Retrieving item resultset: {}", resultSet);
                    if (!(resultSet.isAfterLast()) && !(resultSet.isBeforeFirst())) {
                    	Employee emp = new Employee();
                        emp.setId(resultSet.getInt("id"));
                        emp.setEmpid(resultSet.getInt("empId"));
                        emp.setDeptid(resultSet.getInt("deptid"));
                        emp.setFirstname(resultSet.getString("firstname"));
                        emp.setLastname(resultSet.getString("lastname"));
                        emp.setAge(resultSet.getInt("age"));
                        emp.setEmail(resultSet.getString("email"));
                        emp.setBirthday(resultSet.getDate("birthday"));
                        log.info("Item : {}", emp);
                        return emp;
                    } else {
                        log.info("Returning null item");
                        return null;
                    }
                });
        return reader;
    }
	
	 @Bean("writer1")
     public ItemWriter<Permanent> writer() {
	        JdbcBatchItemWriter<Permanent> writer = new JdbcBatchItemWriter<>();
	        writer.setItemPreparedStatementSetter(setter());
	        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Permanent>());
	        writer.setDataSource(dataSource);
	        writer.setSql("insert into permanent (id, name, deptid) values (?,?,?)");
	        return writer;
	  }
	 
	 @Bean
	 public ItemPreparedStatementSetter<Permanent> setter() {
	        return (item, ps) -> {
	            ps.setInt(1, item.getId());
	            ps.setString(2, item.getName());
	            ps.setInt(3, item.getDeptid());
	        };
	  }
	 
	 @Bean("writer2")
	 public ItemWriter<Permanent> xmlWriter() {
	        StaxEventItemWriter<Permanent> xmlFileWriter = new StaxEventItemWriter<>();
	 
	        String exportFilePath = "./src/main/resources/emps.xml";
	        xmlFileWriter.setResource(new FileSystemResource(exportFilePath));
	        xmlFileWriter.setRootTagName("employees");
	 
	        Jaxb2Marshaller empMarshaller = new Jaxb2Marshaller();
	        empMarshaller.setClassesToBeBound(Permanent.class);
	        xmlFileWriter.setMarshaller(empMarshaller);
	        System.out.println("marshalling");;
	        return xmlFileWriter;
	    }
	 
	 @Bean
	 public ItemProcessor<Employee, Permanent> processor() {
	        return new RecordProcessor();
	    }
	
	 @Bean
	 public Job importUserJob(JobBuilderFactory jobs, Step step1, Step step2, JobExecutionListener listener) {
	        return jobs.get("importUserJob")
	                .incrementer(new RunIdIncrementer())
	                .listener(listener)
	                .flow(step1)
	                .next(step2)
	                .end()
	                .build();
	  }

	  @Bean("step1")
	  public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Employee> reader,
	                       ItemProcessor<Employee, Permanent> processor) {
	        return stepBuilderFactory.get("step1")
	                .<Employee, Permanent>chunk(5)
	                .reader(reader)
	                .processor(processor)
	                .writer(writer())
	                .build();
	  }
	    
	  @Bean("step2")
	  public Step step2(StepBuilderFactory stepBuilderFactory, ItemReader<Employee> reader,
	                     ItemProcessor<Employee, Permanent> processor) {
	        return stepBuilderFactory.get("step2")
	                .<Employee, Permanent>chunk(2)
	                .reader(reader)
	                .processor(processor)
	                .writer(xmlWriter())
	                .build();
	  }
}
