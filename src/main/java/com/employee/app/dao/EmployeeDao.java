package com.employee.app.dao;

import java.sql.SQLException;
import java.util.List;

import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;

public interface EmployeeDao {
	
	//CRUD
	
	Boolean addEmployee(Employee newEmployee)throws EmployeeException;
	Employee getEmployee(Integer employeeId);
	Boolean updateEmployee(Employee updateEmployee);// update all attributes except id
	Boolean updateEmployeeName(Integer employeeId,String name);
	Boolean deleteEmployee(Integer employeeId);
	List<Employee> getAllEmployees();
	Boolean updateEmployeeSalary(Integer employeeId,String name);
	
}
