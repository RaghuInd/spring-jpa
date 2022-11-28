package com.employee.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.employee.app.dto.Employee;
import com.employee.app.exception.EmployeeException;
import com.employee.app.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Read
	// @RequestMapping(value = "employee", method = RequestMethod.GET)
	@GetMapping("employee/{id}")
	public Employee getEmployeeById(@PathVariable("id") Integer id) {
		// System.out.println("Http get ");
	
		Employee foundEmp = null;
		try {
			foundEmp = this.employeeService.getEmployeeByID(id);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return foundEmp;
	}

	// Create
	// @RequestMapping(value = "employee", method = RequestMethod.POST)
	@PostMapping("employee")
	public Employee addNewEmployee(@RequestBody Employee employee) {
		//System.out.println("Http post");
		Employee newEmployee=null;
		try {
			newEmployee=this.employeeService.registerEmployee(employee);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return newEmployee;
	}

	// Update
	// @RequestMapping(value = "employee", method = RequestMethod.PUT)
	@PutMapping("employee")
	public Employee updateEmployee(@RequestBody Employee employee) {
		//System.out.println("Http PUT");
		Employee updateEmployee=null;
		try {
			updateEmployee=this.employeeService.updateEmployee(employee);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return updateEmployee;
	}

	// Partial update
	// @RequestMapping(value = "employee", method = RequestMethod.PATCH)
	@PatchMapping("employee")
	public String updateEmployeeSalary(@RequestBody Employee employee) {
		System.out.println("Http PATCH");
		return "Patch employee";
	}

	// Delete
	// @RequestMapping(value = "employee/{id}", method = RequestMethod.DELETE)
	@DeleteMapping("employee/{id}")
	public Employee deleteEmployeeByID(@PathVariable("id") Integer employeeId) {
	//	System.out.println("Http Delete");
		Employee deleteEmployee=null;
		try {
			deleteEmployee=this.employeeService.deleteEmmployeeById(employeeId);
		} catch (EmployeeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return deleteEmployee;
	}

}
