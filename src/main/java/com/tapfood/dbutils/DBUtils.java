package com.tapfood.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;

 public class DBUtils {
	private static Connection connection;
	private static String URL ="jdbc:mysql://localhost:3306/tapfood";
	private static String USER_NAME="root";
	private static String PASSWORD="root";
	public static Connection myConnection() {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	
}
