package com.tap.controller;

import java.io.IOException;

import com.tapfood.model.Cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/updateCart")
public class UpdateCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        
        if (cart != null) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            // Update the item in the cart
            cart.updateItem(itemId, quantity);
            session.setAttribute("cart", cart);
        }
        
        // Redirect back to the cart page
        response.sendRedirect("cart.jsp");
    }
}
