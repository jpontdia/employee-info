package com.jpworks.datajdbc.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.info.BuildProperties;

@SpringBootApplication
@Slf4j
public class EmployeeApplication implements CommandLineRunner {

	@Autowired
	BuildProperties buildProperties;

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
		}

	@Override
	public void run(String... args) {
		log.info("Application version: {}:{}:{}:{}, {}", buildProperties.getGroup(), buildProperties.getArtifact(), buildProperties.getName(), buildProperties.getVersion(), buildProperties.getTime());
	}
}
