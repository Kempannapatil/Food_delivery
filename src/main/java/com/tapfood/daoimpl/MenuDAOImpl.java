package com.tapfood.daoimpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapfood.dao.MenuDAO;
import com.tapfood.model.Menu;
import com.tapfood.model.User;

public class MenuDAOImpl implements MenuDAO {
	
	ResultSet res ;
	
	private final String ADD_ON_MENU="INSERT INTO menu(restaurantId, itemName, price, isAvailable) VALUES(?,?,?,?)";
	private final String UPDATE_ON_MENUID="update menu set itemname=? price=? isavailable=? where menuid=?";
	private final String GET_ON_ID="select * from menu where restaurantid=?";
	private final String DELETE_ON_MENUID="delete from menu where menuid=?";
	int status;
	PreparedStatement pstmt;
	Connection connection;
	List<Menu> menulist =new ArrayList<Menu>();
	public MenuDAOImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tapfood", "root", "root");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Menu> getAll(int resid) {
		
		try {
			pstmt = connection.prepareStatement(GET_ON_ID);
			pstmt.setInt(1, resid);
			
			res = pstmt.executeQuery();
			
			menulist=extractMenu(res);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return menulist;
	
	
	}
	List<Menu> extractMenu(ResultSet res) throws SQLException {
		
		 while (res.next()) {
             menulist.add(new Menu(
                 res.getInt("menuId"),
                 res.getInt("restaurantId"),
                 res.getString("itemName"),
                 res.getDouble("price"),
                 res.getBoolean("isAvailable")
             ));
			
		 }
		return menulist;
		
		
		
	}

	@Override
	public int deleteMenu(int menuid) {
		
	try {
		pstmt = connection.prepareStatement(DELETE_ON_MENUID);
		
		status = pstmt.executeUpdate();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return status;
		
	}

	@Override
	public int updateMenu(Menu menu) {
		
		try {
			pstmt = connection.prepareStatement(UPDATE_ON_MENUID);
			pstmt.setString(1, menu.getItemName());
			pstmt.setDouble(2, menu.getPrice());
			pstmt.setBoolean(3, menu.isAvailable());
			pstmt.setInt(4, menu.getMenuId());
			
			status = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public int addMenu(Menu menu) {
		
		try {
			pstmt = connection.prepareStatement(ADD_ON_MENU);
			pstmt.setInt(1, menu.getMenuId());
			pstmt.setString(2, menu.getItemName());
			pstmt.setDouble(3, menu.getPrice());
			pstmt.setBoolean(4, menu.isAvailable());
			status = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
	}
	
	
	
	
	
	
	

}
