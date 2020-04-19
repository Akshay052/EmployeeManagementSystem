package com.app.Dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Department;
import com.app.pojos.Employee;

@Repository
@Transactional
public class DepartmentDaoImpl implements DepartmentDao{

	@Autowired 
	SessionFactory sf;

	@Override
	public List<Department> getDepartmentList() {
		List<Department> departments=null;
	      String jpql="select d from Department d ";
	      try {
	    	  departments=sf.getCurrentSession().createQuery(jpql,Department.class).getResultList();
	      }
	      catch (Exception ex) {
	             throw ex;
	      }
	      return departments;
	}

	@Override
	public List<Employee> getEmployeeList(long deptId) {
		List<Employee> emloyees=null;
		String jpql="select e from Employee e where e.dept.deptId=:id";
	      try {
	    	  emloyees=sf.getCurrentSession().createQuery(jpql,Employee.class).setParameter("id", deptId).getResultList();

	      }
	      catch (Exception ex) {
	             throw ex;
	      }
	      return emloyees;
	}

	@Override
	public String addDepartment(Department d) {
		String str="Department not added";
		try {
			sf.getCurrentSession().persist(d);
			str="Department added";
			
		}
		catch (Exception e) {
			throw e;
		}

		
		return str;
	}
	
}
