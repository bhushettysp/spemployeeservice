package com.employee.microservices.dao;

import java.util.List;

import com.employee.microservices.exception.EmployeeException;
import com.employee.microservices.model.Employee;

public interface EmployeeDaoIF {
	public List<Employee> getEmployees() throws EmployeeException;
	public Employee getEmployeeByName(String empName) throws EmployeeException;
	public Employee getEmployee(Long empId) throws EmployeeException;
	public Employee saveEmployee(Employee employee) throws EmployeeException;
	public Employee updateEmployee(Employee employee) throws EmployeeException;
	public Employee deleteEmployee(Long empId) throws EmployeeException;
}
