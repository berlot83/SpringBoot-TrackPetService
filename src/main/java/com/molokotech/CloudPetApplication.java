package com.molokotech;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/* Axel A Berlot 2018 */

@Controller
//@EnableAutoConfiguration
@SpringBootApplication
//@ComponentScan
public class CloudPetApplication {

	public static void main(String[] args) {

		SpringApplication.run(CloudPetApplication.class, args);
	}
	
	  @RequestMapping(value = "/")
	  String index() {
	    return "index";
	  }

}
