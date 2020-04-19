package com.app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dao.DepartmentDao;
import com.app.pojos.Department;
import com.app.pojos.Employee;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "http://localhost:4200")
public class DepartmentsController {

	@Autowired
	DepartmentDao d;

	public DepartmentsController() {
		System.out.println("in Department controller ctor");
	}

	// REST service method to return all Departments
	@GetMapping
	public ResponseEntity<?> getAllDept() {
		System.out.println("in getAlldept List");

		List<Department> alldept = d.getDepartmentList();

		if (alldept.size() == 0)
			return new ResponseEntity<String>("No Departments!!!!", HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Department>>(alldept, HttpStatus.OK);
	}

	// REST service method to create a new Dept
	@PostMapping
	public ResponseEntity<String> AddDepartment(@RequestBody Department dept) {
		System.out.println("in add new dept " + dept);
		try {
			return new ResponseEntity<String>(d.addDepartment(dept), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Dept not created : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{deptId}")
	public ResponseEntity<?> getEmployeesBydeptId(@PathVariable(value = "deptId") Long deptId) {
		System.out.println("in get emp dtls " + deptId);

		List<Employee> employees = d.getEmployeeList(deptId);
		if (employees != null)
			return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
		// invalid id
		return new ResponseEntity<String>("Employee Not Found : Invalid ID", HttpStatus.NOT_FOUND);

	}
	
	
}
