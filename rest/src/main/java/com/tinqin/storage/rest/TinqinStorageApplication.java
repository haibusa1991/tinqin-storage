package com.tinqin.storage.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.tinqin.storage")
@EntityScan(basePackages = "com.tinqin.storage.persistence.entity")
@EnableJpaRepositories(basePackages = "com.tinqin.storage.persistence.repository")
@SpringBootApplication
public class TinqinStorageApplication {
	public static void main(String[] args) {
		SpringApplication.run(TinqinStorageApplication.class, args);
	}

}
