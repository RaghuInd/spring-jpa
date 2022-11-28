package com.employee.app.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;


//@Repository
// class to implement to data access logic with PostGres 
public class EmployeeDaoImpl implements EmployeeDao {

	private Connection connection;

	public EmployeeDaoImpl() {
		super();
		try {
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/demo", "postgres", "admin");
			System.out.println("PostGres connection successful !");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	@Override
	public Boolean addEmployee(Employee newEmployee) throws EmployeeException {

		Boolean isRecordAdded = false;
		String sql = "INSERT INTO employee(id,name,salary) VALUES(?,?,?)";
		// Prepared statement to construct dynamic query
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, newEmployee.getId());
			preparedStatement.setString(2, newEmployee.getName());
			preparedStatement.setDouble(3, newEmployee.getSalary());
			//System.out.println("prepared statement:" + preparedStatement);
			int recordCount = preparedStatement.executeUpdate();
			if (recordCount == 1)
				isRecordAdded = true;

		} catch (SQLException e) {
			
			//e.printStackTrace();
			throw new EmployeeException(e.getMessage());
		}

		return isRecordAdded;
	}

	@Override
	public Employee getEmployee(Integer employeeId) {

		String sql = "SELECT id,name,salary FROM employee WHERE id = ?";
		Employee foundEmployee = null;

		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, employeeId);
			ResultSet result = preparedStatement.executeQuery();
			if (result.next()) {
				foundEmployee = new Employee(result.getInt("id"), result.getString("name"), result.getDouble("salary"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return foundEmployee;
	}

	@Override
	public Boolean updateEmployee(Employee updateEmployee) {

		Boolean isRecodUpdated = false;
		String sql = "UPDATE employee SET name = ? , salary = ? WHERE id = ?";

		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setString(1, updateEmployee.getName());
			preparedStatement.setDouble(2, updateEmployee.getSalary());
			preparedStatement.setInt(3, updateEmployee.getId());
			int count = preparedStatement.executeUpdate();
			if (count == 1)
				isRecodUpdated = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isRecodUpdated;
	}


	@Override
	public Boolean deleteEmployee(Integer employeeId) {
		Boolean isRecodDeleted = false;
		String sql = "DELETE FROM employee WHERE id = ?";

		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

			preparedStatement.setInt(1, employeeId);

			int count = preparedStatement.executeUpdate();
			if (count == 1)
				isRecodDeleted = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return isRecodDeleted;
	}

	@Override
	public List<Employee> getAllEmployees() {

		List<Employee> employees = new ArrayList<>();
		String sql = "SELECT id,name,salary FROM employee";
		try {
			Statement statement = this.connection.createStatement();
			ResultSet result = statement.executeQuery(sql);

			while (result.next()) {
				Employee newEmployee = new Employee(result.getInt("id"), result.getString("name"),
						result.getDouble("salary"));
				employees.add(newEmployee);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return employees;
	}

	@Override
	public Boolean updateEmployeeSalary(Integer employeeId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateEmployeeName(Integer employeeId, String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
