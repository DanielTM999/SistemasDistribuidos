package com.dfs.dfs_c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DfsCApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfsCApplication.class, args);
	}

}
