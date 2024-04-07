package com.freightfox.pds;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class PincodeDistanceServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PincodeDistanceServiceApplication.class, args);
	}

}
