package com.app.Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Employee;


@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao  {

	@Autowired 
	SessionFactory sf;
	
	
	public EmployeeDaoImpl() {
		System.out.println("in EmployeeDao Impl ctor");
	}

	@Override
	public List<Employee> getEmployeeList() {
      //this method returns all the employees
      List<Employee> emloyees=null;
      String jpql="select e from Employee e";
      try {
    	  emloyees=sf.getCurrentSession().createQuery(jpql).getResultList();
      }
      catch (Exception ex) {
             throw ex;
      }
      return emloyees;
		
	}

	@Override
	public Employee getEmployeeDetails(long empId) {
		Employee employee=null;
		try {
			employee=sf.getCurrentSession().get(Employee.class, empId);
		}
		catch (Exception e) {
			throw e;
		}
		return employee;
	}

	@Override
	public String addEmployee(Employee emp) {

		String str="employee not added";
		try {
			sf.getCurrentSession().persist(emp);
			str="Employee added";
			
		}
		catch (Exception e) {
			throw e;
		}

		
		return str;
	}

	@Override
	public String updateEmployee(Employee emp) {
                
		String status="employee not updated";
		try {
			sf.getCurrentSession().update(emp);
			status="employee updated";

		}
		catch (Exception e) {
			throw e;
		}

		return status;
	}

	@Override
	public boolean deleteEmployee(long empId) {
		boolean status=false;
		try {
			Session hs=sf.getCurrentSession();
			Employee emp=hs.get(Employee.class,empId);
			hs.delete(emp);  
			status=true;

		}
		catch (Exception e) {
			throw e;
		}
		return status;
	}

}
