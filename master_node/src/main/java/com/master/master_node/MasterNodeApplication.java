package com.master.master_node;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MasterNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterNodeApplication.class, args);
	}

}
