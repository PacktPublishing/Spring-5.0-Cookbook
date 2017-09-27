package org.packt.process.core.listener;

import java.util.List;

import javax.sql.DataSource;

import org.packt.process.core.model.data.Permanent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class OnCompleteJobExecListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(OnCompleteJobExecListener.class);

    private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	public OnCompleteJobExecListener(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("Short-lived Job Done...");

            List<Permanent> results = jdbcTemplate.query("select * from permanent", (rs, row) -> {
            	Permanent permanent = new Permanent();
            	permanent.setId(rs.getInt("id"));
            	permanent.setDeptid(rs.getInt("deptid"));
            	permanent.setName(rs.getString("name"));
                return permanent;
            });

            for (Permanent permanent : results) {
                log.info("Data is: " + permanent + " in the database.");
            }
        }
    }
}
