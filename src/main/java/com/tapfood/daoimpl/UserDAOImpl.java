package com.tapfood.daoimpl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.tapfood.dao.UserDAO;
import com.tapfood.dbutils.DBUtils;
import com.tapfood.model.User;

public class UserDAOImpl implements UserDAO{
	int status;
	User user;
	private Statement stmt;
	private Connection connection;
	private ResultSet resultset ;
	private PreparedStatement pstmt;
	private final String ADD_USER="insert into user(`username`,`email`,`phonenumber`,`password`,`address`) values(?,?,?,?,?)";
	private final String SELECT_ALL="select * from `user`";
	private final String SELECT_ON_EMAIL="select * from user where email=?";
	private final String UPDATE_ON_EMAIL="update `user` set `username`=?,`phonenumber`=?,`password`=?,`address`=? where `email`=?";
	private final String DELETE_ON_EMAIL="delete from `user` where` email=?";

	ArrayList<User> userlist =new ArrayList<User>();


	public UserDAOImpl() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfood", "root", "root");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public int addUser(User u) {

		try {
			PreparedStatement pstmt = connection.prepareStatement(ADD_USER);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getEmail());
			pstmt.setString(3, u.getPhonenumber());
			pstmt.setString(4, u.getPassword());
			pstmt.setString(5, u.getAddress());
			status = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}

	@Override
	
	public ArrayList<User> getAllUsers() {

		try {
			stmt = connection.createStatement();
			resultset = stmt.executeQuery(SELECT_ALL);
			userlist = extractUserFromResultSet(resultset);

		}

		catch(Exception e) {
			e.printStackTrace();
		}

		return userlist;
	}

	@Override
	public User getUser(String email) {
		try {
			pstmt = connection.prepareStatement(SELECT_ON_EMAIL);
			pstmt.setString(1, email);
			resultset = pstmt.executeQuery();
			userlist = extractUserFromResultSet(resultset);
			user = userlist.get(0);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public int updateUser(User u) {
		try {
			pstmt = connection.prepareStatement(UPDATE_ON_EMAIL);
			pstmt.setString(1, u.getUsername());
			pstmt.setString(2, u.getPhonenumber());
			pstmt.setString(3, u.getPassword());
			pstmt.setString(4, u.getAddress());
			pstmt.setString(5, u.getEmail());

			status = pstmt.executeUpdate();

		}

		catch(Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	@Override
	public int deleteUser(String email) {
		try {
			pstmt = connection.prepareStatement(DELETE_ON_EMAIL);	
			pstmt.setString(1, email);	

			status = pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		return status;
	}

	public ArrayList<User> extractUserFromResultSet(ResultSet resultset) {
		System.out.println();
		try {
			while(resultset.next()) {
				userlist.add(new User(resultset.getInt("userid"),
						resultset.getString("username"),
						resultset.getString("email"),
						resultset.getString("phonenumber"),
						resultset.getString("password"),
						resultset.getString("address")));
			}
		} 
		catch (Exception e) {

			e.printStackTrace();
		}
		return userlist;
	}
}
