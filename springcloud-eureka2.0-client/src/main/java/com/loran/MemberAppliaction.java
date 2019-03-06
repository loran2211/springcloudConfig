package com.loran;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MemberAppliaction {
	public static void main(String[] args) {
		SpringApplication.run(MemberAppliaction.class, args);
	}
}
