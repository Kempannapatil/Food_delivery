package com.tap.controller;

import java.io.IOException;

import com.tapfood.dao.UserDAO;
import com.tapfood.daoimpl.UserDAOImpl;
import com.tapfood.model.User;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/registeruser")
public class UserRegisterServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String email = req.getParameter("email");
		String phonenumber = req.getParameter("phonenumber");
		String password = req.getParameter("password");
		String address = req.getParameter("address");
		
		System.out.println(name);
		
		User u=new User(name, email, phonenumber, password, address);
		UserDAO userDAO = new UserDAOImpl();
		int status = userDAO.addUser(u);
		
		if(status==0) {
			resp.sendRedirect("registerfailure.jsp");
		}
		else {
			resp.sendRedirect("registersuccess.jsp");
		}

	}
}
