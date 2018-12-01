package com.molokotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/* Axel A Berlot 2018 */

@Controller
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan
@Component
public class CloudPetApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudPetApplication.class, args);
	}
	

}
