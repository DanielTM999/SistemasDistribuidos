package com.isp_server.isp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IspApplication {

	public static void main(String[] args) {
		SpringApplication.run(IspApplication.class, args);
	}

}
