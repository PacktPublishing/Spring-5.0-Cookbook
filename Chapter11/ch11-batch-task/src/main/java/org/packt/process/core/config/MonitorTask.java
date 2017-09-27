package org.packt.process.core.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MonitorTask implements CommandLineRunner {
	private final Logger log = LoggerFactory.getLogger(MonitorTask.class);

	@Override
	public void run(String... args) throws Exception {
		log.info("running task");
		
	}
}
