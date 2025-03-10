package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.tapfood.model.Cart;
import com.tapfood.model.CartItem;
import com.tapfood.model.OrderItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/placeOrder")
public class PlaceOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart"); // Retrieve cart from session

        if (cart != null) {
            try (Connection conn = Database.getConnection()) {
                // Iterate over the cart items and save each OrderItem
                for (CartItem item : cart.getItems().values()) {
                    OrderItem orderItem = new OrderItem(conn, 0, item.getItemid(), item.getItemname(), item.getQuantity(), item.getPrice() * item.getQuantity());
                    orderItem.saveOrderItem(); // Save the order item
                }
                // Optionally, clear the cart after placing the order
                cart.clear();
                session.setAttribute("cart", cart);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("orderConfirmation.jsp"); // Redirect to confirmation page
    }
}
