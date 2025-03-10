package com.tapfood.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.tapfood.model.Order; // Make sure you have an Order class defined
import com.tapfood.model.OrderItem; // Assuming you have an OrderItem class

public class OrderDao {

    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    // Method to create a new order
    public void createOrder(Order order) throws SQLException {
        String insertOrderSQL = "INSERT INTO ordertable (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, order.getUserId());
            preparedStatement.setInt(2, order.getRestaurantId());
            preparedStatement.setDate(3, order.getOrderDate()); // Ensure your Order class has a getOrderDate method returning java.sql.Date
            preparedStatement.setDouble(4, order.getTotalAmount());
            preparedStatement.setString(5, order.getStatus());
            preparedStatement.setString(6, order.getPaymentMode());
            preparedStatement.executeUpdate();

            // Retrieve the generated order ID
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int orderId = generatedKeys.getInt(1);
                    // Optionally, you can now add order items for the created order
                    for (OrderItem item : order.getOrderItems()) {
                        addOrderItem(orderId, item);
                    }
                }
            }
        }
    }

    // Method to add an order item to an existing order
    private void addOrderItem(int orderId, OrderItem item) throws SQLException {
        String insertOrderItemSQL = "INSERT INTO orderitem (orderId, menuId, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderItemSQL)) {
            preparedStatement.setInt(1, orderId);
            preparedStatement.setInt(2, item.getMenuId()); // Assuming OrderItem has getMenuId method
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setDouble(4, item.getTotal()); // Assuming OrderItem has getTotal method
            preparedStatement.executeUpdate();
        }
    }

    // Method to retrieve an order by ID
    public Order getOrderById(int orderId) throws SQLException {
        String selectOrderSQL = "SELECT * FROM ordertable WHERE orderId = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectOrderSQL)) {
            preparedStatement.setInt(1, orderId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Order order = new Order();
                    order.setOrderId(resultSet.getInt("orderId"));
                    order.setUserId(resultSet.getInt("userId"));
                    order.setRestaurantId(resultSet.getInt("restaurantId"));
                    order.setOrderDate(resultSet.getDate("orderDate"));
                    order.setTotalAmount(resultSet.getDouble("totalAmount"));
                    order.setStatus(resultSet.getString("status"));
                    order.setPaymentMode(resultSet.getString("paymentMode"));
                    return order;
                }
            }
        }
        return null; // Order not found
    }

    // You can add more methods for updating and deleting orders
}
