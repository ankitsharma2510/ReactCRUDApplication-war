package com.yash.spring.boot.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.yash.spring.boot.model.Employee;
import com.yash.spring.boot.model.EmployeeLoginAuthentication;

@Service
public interface EmployeeAuthenticationService {

	public boolean authenticateEmployee(EmployeeLoginAuthentication employeeLoginAuthentication);

	public List<Employee> showEmployeeData();

	public Employee saveEmployeeData(Employee employee) throws RuntimeException;

	public boolean deleteEmployeeData(Employee employee);

	public boolean updateEmployeeData(Employee employee);
	
	
}
