package com.dfs.dfs_b;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DfsBApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfsBApplication.class, args);
	}

}