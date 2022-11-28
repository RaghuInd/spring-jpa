package com.employee.app.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.app.dao.EmployeeDao;
import com.employee.app.dao.EmployeeDaoImpl;
import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDao employeeRespository;// = new EmployeeDaoImpl();

	@Override
	public Employee registerEmployee(Employee employee) throws EmployeeException {

		try {
			this.employeeRespository.addEmployee(employee);
		} catch (EmployeeException e) {
			throw new EmployeeException("Employee could not be added." + e.getMessage());
		}

		return this.employeeRespository.getEmployee(employee.getId());
	}

	@Override
	public Employee getEmployeeByID(Integer id) throws EmployeeException {

		Employee foundEmp =this.employeeRespository.getEmployee(id);
		if(foundEmp == null)
			throw new EmployeeException("Employee not found.");
		return foundEmp;
	}

	@Override
	public Employee updateEmployee(Employee updateEmployee) throws EmployeeException {

		Employee emp = this.employeeRespository.getEmployee(updateEmployee.getId());
		if (emp == null)
			throw new EmployeeException("Employee id not found to update");

		this.employeeRespository.updateEmployee(updateEmployee);

		return this.employeeRespository.getEmployee(updateEmployee.getId());
	}

	@Override
	public Employee deleteEmmployeeById(Integer id) throws EmployeeException {
		Employee foundEmp = employeeRespository.getEmployee(id);
		if (foundEmp == null)
			throw new EmployeeException("Employee id not found to delete.");

		employeeRespository.deleteEmployee(id);

		employeeRespository.deleteEmployee(id);
		return foundEmp;
	}

}
