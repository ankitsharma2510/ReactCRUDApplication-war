package com.yash.spring.boot.repository;

import org.springframework.data.repository.CrudRepository;

import com.yash.spring.boot.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{
	
}
