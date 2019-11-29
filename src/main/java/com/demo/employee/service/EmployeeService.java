package com.demo.employee.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.demo.employee.domain.Employee;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	private List<Employee> employeeList=new ArrayList();
	
	// Get all the employees
	public List<Employee> getAllEmployes() {
		Iterator<Employee> itr=employeeRepository.findAll().iterator();
		while(itr.hasNext()) {
			employeeList.add(itr.next());
		}
		return employeeList;
	}
	
	//create new employee
	public Employee createEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
}
