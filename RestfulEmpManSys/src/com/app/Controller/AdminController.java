package com.app.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.Dao.AdminDao;
import com.app.Dao.DepartmentDao;
import com.app.Dao.EmployeeDao;
import com.app.pojos.Admin;
import com.app.pojos.Department;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	DepartmentDao d;
	@Autowired
	EmployeeDao e;
	@Autowired
	AdminDao a;

	public AdminController() {
		System.out.println("in Admin controller ctor");
	}

	// REST service method to authenticate Admin
	@GetMapping
	public ResponseEntity<?> authenticateAdmin(@RequestParam String email, @RequestParam String password,HttpSession hs) {
			
		System.out.println("in authenticate admin");
          Admin admin=a.authenticateAdmin(email, password);
		if (admin !=null)
		{
			hs.setAttribute("admin_details", admin);
			return new ResponseEntity<Boolean>(HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/getAllDept")
	public ResponseEntity<?> getAllDept() {
		System.out.println("in getAlldept List");

		List<Department> alldept = d.getDepartmentList();

		if (alldept.size() == 0)
			return new ResponseEntity<String>("No Departments!!!!", HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Department>>(alldept, HttpStatus.OK);
	}
	@PostMapping("/adddept")
	public ResponseEntity<String> AddDepartment(@RequestBody Department dept) {
		System.out.println("in add new dept " + dept);
		try {
			return new ResponseEntity<String>(d.addDepartment(dept), HttpStatus.CREATED);
		} catch (RuntimeException e) {
			return new ResponseEntity<String>("Dept not created : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
