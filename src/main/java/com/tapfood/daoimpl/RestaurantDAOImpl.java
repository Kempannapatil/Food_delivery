package com.tapfood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.tapfood.dao.RestaurantDAO;
import com.tapfood.model.Restaurant;

public class RestaurantDAOImpl implements RestaurantDAO{
	
	private final String add_restaurant="insert into restaurant(`restaurantname`,`cuisivetype`,`deliverytime`,`address`,`rating`,`isactive`) values(?,?,?,?,?,?)";
	 Connection connection;
	 PreparedStatement pstmt;
	 ResultSet resultset;
	 
	 
	 
	 
	public RestaurantDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfood", "root", "root");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addrestaurant(Restaurant restaurant) {
		try {
			pstmt = connection.prepareStatement(add_restaurant);
			pstmt.setString(1, restaurant.getRestaurantName());
			pstmt.setString(2, restaurant.getCuisiveType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Restaurant getrestaurant(int restaurantId) {
		String querry="select * from restaurant where restaurantid=?";
		Restaurant restaurant =null;
		try {
			pstmt = connection.prepareStatement(querry);
			pstmt.setInt(1, restaurantId);
			resultset = pstmt.executeQuery();
			
			if(resultset.next()) {
				restaurant= extractRestaurant(resultset);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return restaurant;
		
	}

	
	Restaurant extractRestaurant(ResultSet resultset) throws SQLException{
		
		Restaurant restaurant = new Restaurant();
		restaurant.setRestaurantId(resultset.getInt("restaurantid"));
		restaurant.setRestaurantName(resultset.getString("restaurantname"));
		restaurant.setCuisiveType(resultset.getString("cuisivetype"));
		restaurant.setDeliveryTime(resultset.getInt("deliverytime"));
		restaurant.setAddress(resultset.getString("address"));
		restaurant.setAdminUserId(resultset.getInt("adminuserid"));
		restaurant.setRating(resultset.getDouble("rating"));
		restaurant.setActive(resultset.getBoolean("isactive"));
		restaurant.setImagePath(resultset.getString("imagepath"));
		
		return restaurant;
	}
	
	
	
	
	
	@Override
	public void updateRestaurant(Restaurant restaurant) {
		String sql="update restaurant set name=?,cuisivetype=?,deliverytime=?,address=?,rating=?,isactive=? where restaurantid=?";
		
		try {
			pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, restaurant.getRestaurantName());
			pstmt.setString(2, restaurant.getCuisiveType());
			pstmt.setInt(3, restaurant.getDeliveryTime());
			pstmt.setString(4, restaurant.getAddress());
			pstmt.setDouble(5, restaurant.getRating());
			pstmt.setBoolean(6, restaurant.isActive());
			pstmt.setInt(7, restaurant.getRestaurantId());
			
			pstmt.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deleterestaurant(int restaurantId) {
		
		String sql="delete from restaurant where restaurantid=?";
		
	try {
		pstmt = connection.prepareStatement(sql);
		pstmt.setInt(1, restaurantId);
		
		pstmt.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
		
	}

	@Override
	public ArrayList<Restaurant> getallrestaurant() {
		Restaurant restaurant=null;
		ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
		
		String sql="select * from restaurant";
		try {
		pstmt = connection.prepareStatement(sql);
		 resultset = pstmt.executeQuery();
		while(resultset.next()) {
			restaurant= extractRestaurant(resultset);
			restaurants.add(restaurant);
		}
		 
		 
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return restaurants;
		
	}

	
	 
	
	
}
