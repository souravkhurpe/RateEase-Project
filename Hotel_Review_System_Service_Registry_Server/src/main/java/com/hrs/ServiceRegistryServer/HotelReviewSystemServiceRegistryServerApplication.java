package com.hrs.ServiceRegistryServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HotelReviewSystemServiceRegistryServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelReviewSystemServiceRegistryServerApplication.class, args);
	}

}
