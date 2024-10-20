package com.caspex.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.caspex")
@EnableAerospikeRepositories(basePackages = "com.caspex.repository")
@EnableJpaRepositories(basePackages = "com.caspex.repository.mysql")
public class CaspexDeviceMatchingAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaspexDeviceMatchingAppApplication.class, args);
		
	}

}
