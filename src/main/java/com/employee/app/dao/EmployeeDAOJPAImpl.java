package com.employee.app.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class EmployeeDAOJPAImpl implements EmployeeDao{

	//@Autowired
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Boolean addEmployee(Employee newEmployee) throws EmployeeException {
	//	this.entityManager.getTransaction().begin();
		try {
		this.entityManager.persist(newEmployee);
		}
		catch(EntityExistsException  e) {
			throw new EmployeeException("Employee already exists!");
		}
		//this.entityManager.getTransaction().commit();
		
		return true;
	}

	@Override
	public Employee getEmployee(Integer employeeId) {
		
		return this.entityManager.find(Employee.class, employeeId);
	}

	@Override
	public Boolean updateEmployee(Employee updateEmployee) {
	//	this.entityManager.getTransaction().begin();
		this.entityManager.merge(updateEmployee);
	//	this.entityManager.getTransaction().commit();
		return null;
	}

	@Override
	public Boolean updateEmployeeName(Integer employeeId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean deleteEmployee(Integer employeeId) {
		//this.entityManager.getTransaction().begin();
		
		Employee delEmp = this.entityManager.find(Employee.class, employeeId);
		
		this.entityManager.remove(delEmp);
		//this.entityManager.getTransaction().commit();
		return true;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateEmployeeSalary(Integer employeeId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
