package com.tap.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.tapfood.dao.RestaurantDAO;
import com.tapfood.daoimpl.RestaurantDAOImpl;
import com.tapfood.model.Restaurant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/home")
public class Home extends HttpServlet {
	RestaurantDAO restaurantDAO ;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 restaurantDAO = new RestaurantDAOImpl();
	 ArrayList<Restaurant> restaurantlist = restaurantDAO.getallrestaurant();
	
	 
	 HttpSession session = req.getSession();
	 
	 session.setAttribute("restaurantlist", restaurantlist);
	 
	 resp.sendRedirect("home.jsp");
	}

}
