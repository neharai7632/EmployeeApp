package com.demo.employee.service;

import org.springframework.data.repository.CrudRepository;

import com.demo.employee.domain.Employee;

public interface EmployeeRepository extends CrudRepository<Employee,String>{
	
}
