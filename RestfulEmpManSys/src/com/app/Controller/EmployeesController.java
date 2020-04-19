package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dao.EmployeeDao;
import com.app.pojos.Employee;

@RestController
@RequestMapping("/Employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeesController {

	@Autowired
	EmployeeDao e;
	

	public EmployeesController() {
		System.out.println("in employees controller ctor");
	}

	// REST service method to return all Employees
	@GetMapping
	public ResponseEntity<?> getAllEmp() {
		System.out.println("in getAllemployees List");

		List<Employee> allEmp = e.getEmployeeList();

		if (allEmp.size() == 0)
			return new ResponseEntity<String>("No employees!!!!", HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Employee>>(allEmp, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getEmployeeById(@PathVariable(value = "id") long empId) {
		System.out.println("in get emp dtls " + empId);

		Employee employee = e.getEmployeeDetails((empId));
		if (e != null)
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		// invalid id
		return new ResponseEntity<String>("Employee Not Found : Invalid ID", HttpStatus.NOT_FOUND);

	}

	// REST service method to create a new Employee
	@PostMapping
	public ResponseEntity<String> AddEmployee(@RequestBody Employee emp) {
		System.out.println("in add new employee " + emp);
		try {
			return new ResponseEntity<String>(e.addEmployee(emp), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Employee not created : " + e.getMessage(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// REST service method to delete a resource
	@DeleteMapping("/{empId}")
	public ResponseEntity<Boolean> deleteEmployee(@PathVariable int empId) {
		System.out.println("in del Employee " + empId);
		return new ResponseEntity<Boolean>(e.deleteEmployee(empId), HttpStatus.OK);
	}

	// REST service method to update existing stock
	@PutMapping("/{empId}")
	public ResponseEntity<String> updateEmployee(@PathVariable long empId, @RequestBody Employee emp) {
		System.out.println("in update Employee " + empId + " " + emp);
		// chk if employee exists
		Employee ExistEmployee = e.getEmployeeDetails(empId);
		if (ExistEmployee != null) {
			// assign empid to updated employee
			emp.setEmpId(empId);
			try {
				return new ResponseEntity<String>(e.updateEmployee(ExistEmployee), HttpStatus.OK);
			} catch (RuntimeException e) {
				return new ResponseEntity<String>("Employee not updated : " + e.getMessage(),
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<String>("Employee not Found", HttpStatus.NOT_FOUND);

	}

}
