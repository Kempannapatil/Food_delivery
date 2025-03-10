package com.tap.controller;

import java.io.IOException;
import java.util.List;

import com.tapfood.daoimpl.MenuDAOImpl;
import com.tapfood.model.Menu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/menu")
public class GetMenu extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id=req.getParameter("resId");
		int resid=Integer.parseInt(id);
		
		
		
		MenuDAOImpl menudao = new MenuDAOImpl();
		
		List<Menu> menulist = menudao.getAll(resid);
		
		
		if(menulist!=null) {
		
		HttpSession session = req.getSession();
		
		
		session.setAttribute("menulist", menulist);
		
		resp.sendRedirect("menu.jsp");
		}
		else{
			resp.sendRedirect("home.jsp");	
		}
		
	}
	

}
