package com.employee.microservices.service;

import java.util.List;

import com.employee.microservices.exception.EmployeeException;
import com.employee.microservices.model.Employee;

public interface EmployeeServiceIF {
	public List<Employee> getEmployees() throws EmployeeException;
	public Employee getEmployee(Long empId) throws EmployeeException;
	public Employee saveEmployee(Employee employee) throws EmployeeException;
	public Employee updateEmployee(Employee employee) throws EmployeeException;
	public Employee deleteEmployee(Long empId) throws EmployeeException;
}
