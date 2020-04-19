package com.app.Dao;

import javax.persistence.NoResultException;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Admin;

@Repository
@Transactional
public class AdminDaoImpl implements AdminDao{

	@Autowired 
	SessionFactory sf;

	
	@Override
	public Admin authenticateAdmin(String email, String pass) {

		Admin admin=null;
		
	      String jpql="select a from Admin a where a.email=:em and a.password=:pass ";
	      try {
	    	  admin=sf.getCurrentSession().createQuery(jpql,Admin.class).setParameter("em", email).setParameter("pass", pass).getSingleResult();
                 if(admin!=null)
                	return admin;
	      }
	      catch(NoResultException ex)
	      {
	    	  System.out.println("No result found");
	    	  
	      }
        return admin;
	}

	
}
