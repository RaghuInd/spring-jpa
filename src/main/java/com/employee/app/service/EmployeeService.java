package com.employee.app.service;


import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;

public interface EmployeeService {

	Employee registerEmployee(Employee employee)throws EmployeeException;
	Employee getEmployeeByID(Integer id) throws EmployeeException;
	Employee updateEmployee(Employee updateEmployee)throws EmployeeException ;
	Employee deleteEmmployeeById(Integer id)throws EmployeeException;
	
}
