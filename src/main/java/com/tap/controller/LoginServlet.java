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
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		
		UserDAO userDAO = new UserDAOImpl();
		User user = userDAO.getUser(email);
		

		if(user!=null&&password.equals(user.getPassword())) {
			HttpSession session = req.getSession();
			
			session.setAttribute("user", user);
			
			
			resp.sendRedirect("home");
		}
		else {
			req.setAttribute("errorMessage", "Invalid Email or Password");
			req.getRequestDispatcher("loginfail.jsp").forward(req, resp);
			
			
		}
		
		
	}
	
	
	

}
