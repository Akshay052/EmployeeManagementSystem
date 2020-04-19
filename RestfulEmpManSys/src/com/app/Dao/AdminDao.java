package com.app.Dao;

import com.app.pojos.Admin;

public interface AdminDao {

	Admin authenticateAdmin(String email,String pass);
	
}
