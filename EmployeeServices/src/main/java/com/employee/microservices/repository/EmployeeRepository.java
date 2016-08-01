package com.employee.microservices.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.microservices.model.Employee;

@Transactional
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	public Employee findByName(String name);
	
}
