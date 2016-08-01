package com.employee.microservices.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.microservices.config.DatabaseConfig;
import com.employee.microservices.exception.EmployeeException;
import com.employee.microservices.model.Employee;
import com.employee.microservices.repository.EmployeeRepository;

@Repository
public class EmployeeDaoImpl implements EmployeeDaoIF {

	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private DatabaseConfig databaseConfig;
	
	@Override
	public List<Employee> getEmployees() throws EmployeeException {
		List<Employee> employeeList = employeeRepository.findAll();
		return employeeList;
	}

	@Override
	public Employee getEmployee(Long empId) throws EmployeeException {
		Employee employee = employeeRepository.findOne(empId);
		return employee;
	}

	@Override
	public Employee saveEmployee(Employee employee) throws EmployeeException {
		employeeRepository.save(employee);
		return getEmployee(employee.getId());
	}

	@Override
	public Employee updateEmployee(Employee employee) throws EmployeeException {
		if (getEmployee(employee.getId()) == null)
			return null;
		employeeRepository.save(employee);
		return getEmployee(employee.getId());
	}

	@Override
	public Employee deleteEmployee(Long empId) throws EmployeeException {
		Employee employee;
		if (getEmployee(empId) == null)
			return null;
		employee = getEmployee(empId);
		employeeRepository.delete(empId);
		return employee;
	}

	@Override
	public Employee getEmployeeByName(String empName) throws EmployeeException {
		return employeeRepository.findByName(empName);
	}

}
