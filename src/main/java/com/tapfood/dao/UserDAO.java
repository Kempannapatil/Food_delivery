package com.tapfood.dao;

import java.util.ArrayList;

import com.tapfood.model.User;

public interface UserDAO {

	int addUser(User u);
	
	ArrayList<User> getAllUsers();

	User getUser(String email);
	
	int updateUser(User u);
	
	int deleteUser(String email);
	
}
