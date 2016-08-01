package com.employee.microservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.microservices.dao.EmployeeDaoIF;
import com.employee.microservices.exception.EmployeeException;
import com.employee.microservices.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeServiceIF {

	@Autowired
	private EmployeeDaoIF employeeDaoIF;
	
	@Override
	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employeeList = employeeDaoIF.getEmployees();
		return employeeList;
	}

	@Override
	public Employee getEmployee(Long empId) throws EmployeeException {
		Employee employee = employeeDaoIF.getEmployee(empId);
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) throws EmployeeException {
		return employeeDaoIF.saveEmployee(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeException {
		return employeeDaoIF.updateEmployee(employee);
	}

	@Override
	public Employee deleteEmployee(Long empId) throws EmployeeException {
		return employeeDaoIF.deleteEmployee(empId);
	}

}
