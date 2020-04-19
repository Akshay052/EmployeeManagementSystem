package com.app.Dao;

import java.util.List;

import com.app.pojos.Employee;

public interface EmployeeDao {
      
	List<Employee> getEmployeeList();
	Employee getEmployeeDetails(long id);
	String addEmployee(Employee e);
	String updateEmployee(Employee e);
	boolean deleteEmployee(long id);
	
}
