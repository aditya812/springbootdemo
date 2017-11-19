package com.example.dao;

import com.example.model.User;
import com.example.model.UserAuthentication;

public interface UserDAO {

	public User getUserByName(String userName);
	
	public void saveToken(UserAuthentication userAuthentication);
	
	public UserAuthentication getUserAuthentication(String token);
}
