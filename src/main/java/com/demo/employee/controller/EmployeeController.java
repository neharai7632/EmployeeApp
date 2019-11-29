package com.demo.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.employee.domain.Employee;
import com.demo.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployes();
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/register")
	public Employee registerEmployee(@RequestBody Employee employee) {
		return  employeeService.createEmployee(employee);
	}
}
