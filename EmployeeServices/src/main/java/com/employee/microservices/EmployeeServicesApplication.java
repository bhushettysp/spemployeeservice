package com.employee.microservices;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EmployeeServicesApplication {

	public EmployeeServicesApplication(){}
	
	public static void main(String[] args) {
		SpringApplication.run(EmployeeServicesApplication.class, args);
	}
}
