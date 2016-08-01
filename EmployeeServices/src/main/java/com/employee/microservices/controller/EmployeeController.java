package com.employee.microservices.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.microservices.model.Employee;
import com.employee.microservices.result.Result;
import com.employee.microservices.service.EmployeeServiceIF;
import com.employee.microservices.constant.ApplicationConstants;
import com.employee.microservices.message.ApplicationMessages;

@RestController
@RequestMapping(path = "employee/v1")
public class EmployeeController {

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private EmployeeServiceIF employeeServiceIF;

	@RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployees() {

		HttpStatus statusCode = null;
		Result result = new Result();
		List<Employee> employeeList = null;
		try {
			employeeList = employeeServiceIF.getEmployees();
			if (employeeList.isEmpty()) {
				result.setMessage(ApplicationMessages.EMPLOYEES_LIST_EMPTY);
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setObject(employeeList);
			} else {
				result.setObject(employeeList);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.EMPLOYEES_LIST_NOT_EMPTY);
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.EMPLOYEE_METHED_FIND_ALL);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employeeList);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.EMPLOYEE_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> getEmployee(@PathVariable("employeeId") String employeeId) {

		HttpStatus statusCode = null;
		Employee employee = null;
		Result result = new Result();
		try {
			employee = employeeServiceIF.getEmployee(Long.parseLong(employeeId));

			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.EMPLOYEE_DOES_NOT_EXIST);
			} else {
				result.setObject(employee);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.EMPLOYEE_EXIST);
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.EMPLOYEE_METHED_FIND_ONE);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.EMPLOYEE_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> saveEmployee(@RequestBody Employee employee) {

		HttpStatus statusCode = null;
		Result result = new Result();
		try {

			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.EMPLOYEE_DOES_NOT_EXIST);
			} else {
				logger.info("employee.getEmpId() else: "+employee.getId());
				employeeServiceIF.saveEmployee(employee);
				result.setObject(employee);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.EMPLOYEE_STATE_SAVED);
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.EMPLOYEE_METHED_STORED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.EMPLOYEE_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> updateEmployee(@RequestBody Employee employee) {

		HttpStatus statusCode = null;
		Result result = new Result();
		Employee employee1 = null;
		try {

			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.NO_EMPLOYEE_DATA);
			} else {
				employee1 = employeeServiceIF.updateEmployee(employee);
				if (employee1 == null) {
					result.setObject(employee1);
					statusCode = HttpStatus.NO_CONTENT;
					result.setStatus(ApplicationConstants.FAILED);
					result.setMessage(ApplicationMessages.EMPLOYEE_DOES_NOT_EXIST);
				} else {
					result.setObject(employee1);
					statusCode = HttpStatus.OK;
					result.setStatus(ApplicationConstants.SUCCESS);
					result.setMessage(ApplicationMessages.EMPLOYEE_STATE_UPDATED);
				}
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.EMPLOYEE_METHED_UPDATED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.EMPLOYEE_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}

	@RequestMapping(value = "/{employeeId}", method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> deleteCustomer(@PathVariable("employeeId") String employeeId) {
		HttpStatus statusCode = null;
		Result result = new Result();
		Employee employee = null;
		try {
			employee = employeeServiceIF.deleteEmployee(Long.parseLong(employeeId));
			if (employee == null) {
				result.setObject(employee);
				statusCode = HttpStatus.NO_CONTENT;
				result.setStatus(ApplicationConstants.FAILED);
				result.setMessage(ApplicationMessages.EMPLOYEE_DOES_NOT_EXIST);
			} else {
				result.setObject(employee);
				statusCode = HttpStatus.OK;
				result.setStatus(ApplicationConstants.SUCCESS);
				result.setMessage(ApplicationMessages.EMPLOYEE_STATE_DELETED);
			}
		} catch (Exception e) {
			logger.error(ApplicationMessages.EMPLOYEE_METHED_DELETED);
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			result.setObject(employee);
			result.setStatus(ApplicationConstants.FAILED);
			result.setMessage(e.getLocalizedMessage());
			// throw new
			// CustomerException(ApplicationConstants.EMPLOYEE_NOT_FOUND,e);
			return new ResponseEntity<Result>(result, statusCode);
		}

		return new ResponseEntity<Result>(result, statusCode);
	}
}
