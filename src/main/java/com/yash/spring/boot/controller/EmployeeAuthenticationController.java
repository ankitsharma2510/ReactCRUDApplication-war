package com.yash.spring.boot.controller;

import java.util.List;
import javax.inject.Inject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.spring.boot.model.Employee;
import com.yash.spring.boot.model.EmployeeLoginAuthentication;
import com.yash.spring.boot.service.EmployeeAuthenticationService;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class EmployeeAuthenticationController {

	@Inject
	private EmployeeAuthenticationService employeeAuthenticationService;

	@GetMapping(value = "/")
	public String say() {
		return "Hello..!! ";
	}

	@PostMapping(value = "/saveEmployee")
	public Employee saveEmployee(@RequestBody Employee employee) {
		return employeeAuthenticationService.saveEmployeeData(employee);
	}

	@GetMapping(value = "/getAllEmployees")
	public List<Employee> showEmployeeData() {
		return employeeAuthenticationService.showEmployeeData();
	}

	@PutMapping(value = "/updateEmployee")
	public boolean updateEmployee(@RequestBody Employee employee) {
		return employeeAuthenticationService.updateEmployeeData(employee);
	}

	@DeleteMapping(value = "/deleteEmployee")
	public boolean deleteEmployee(@RequestBody Employee employee) {
		return employeeAuthenticationService.deleteEmployeeData(employee);
	}

	@PostMapping(value = "/authenticate")
	public boolean authenticateEmployee(@RequestBody EmployeeLoginAuthentication employeeLoginAuthentication) {
		return employeeAuthenticationService.authenticateEmployee(employeeLoginAuthentication);
	}

}
