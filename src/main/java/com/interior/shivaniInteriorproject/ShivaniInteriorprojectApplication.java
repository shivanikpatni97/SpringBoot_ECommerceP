package com.interior.shivaniInteriorproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude =  { SecurityAutoConfiguration.class })
public class ShivaniInteriorprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShivaniInteriorprojectApplication.class, args);
	}

}
