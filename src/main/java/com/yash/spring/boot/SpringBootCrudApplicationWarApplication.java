package com.yash.spring.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yash.spring.boot.model.EmployeeLoginAuthentication;

@SpringBootApplication
public class SpringBootCrudApplicationWarApplication {

	@Bean
	public EmployeeLoginAuthentication getEmployeeLoginAuthenticationObject(){
		return new EmployeeLoginAuthentication(); 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBootCrudApplicationWarApplication.class, args);
	}
}

