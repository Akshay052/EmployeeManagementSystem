package com.app.Dao;

import java.util.List;

import com.app.pojos.Department;
import com.app.pojos.Employee;

public interface DepartmentDao {
	List<Department> getDepartmentList();
	List<Employee> getEmployeeList(long deptid);
	String addDepartment(Department d);
}
