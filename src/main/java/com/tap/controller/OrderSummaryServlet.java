package com.tap.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tapfood.model.OrderItem;
import com.tap.controller.Database;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/orderSummary")
public class OrderSummaryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId")); // Get the order ID from the request
        List<OrderItem> orderItems = new ArrayList<>();

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT oi.orderItemId, oi.orderId, oi.menuId, m.itemName, oi.quantity, oi.itemTotal " +
                           "FROM orderitem oi JOIN menu m ON oi.menuId = m.menuId WHERE oi.orderId = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, orderId);
                ResultSet rs = stmt.executeQuery();
                
                while (rs.next()) {
                    OrderItem orderItem = new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getString("itemName"), // Fetch the item name
                        rs.getInt("quantity"),
                        rs.getDouble("itemTotal")
                    );
                    orderItems.add(orderItem);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.setAttribute("orderItems", orderItems); // Set the order items as a request attribute
        request.getRequestDispatcher("orderSummary.jsp").forward(request, response); // Forward to JSP
    }
}
