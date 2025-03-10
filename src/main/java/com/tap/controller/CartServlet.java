package com.tap.controller;

import java.io.IOException;
import com.tapfood.model.Cart;
import com.tapfood.model.CartItem;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get session
        HttpSession session = request.getSession();
        
        // Retrieve the Cart object from the session, or create a new one if it doesn't exist
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }

        try {
            // Retrieve item details from the request
            String restaurantIdStr = request.getParameter("restaurantId");
            String itemName = request.getParameter("itemName");
            String quantityStr = request.getParameter("quantity");
            String priceStr = request.getParameter("price");

            if (restaurantIdStr == null || itemName == null || quantityStr == null || priceStr == null) {
                response.sendRedirect("error.jsp"); // Handle error case
                return;
            }

            int restaurantId = Integer.parseInt(restaurantIdStr);
            int quantity = Integer.parseInt(quantityStr);
            double price = Double.parseDouble(priceStr);
            
            // Create a new CartItem
            CartItem newItem = new CartItem(restaurantId, quantity, itemName, quantity, price);
            
            // Add the new item to the cart
            cart.addItem(newItem);
            
            // Update the session cart
            session.setAttribute("cart", cart);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp"); // Handle parsing error
            return;
        }

        // Redirect to cart.jsp
        response.sendRedirect("cart.jsp");
    }
}
