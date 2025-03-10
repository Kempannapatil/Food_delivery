package com.tapfood.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderTable {
    private int orderId;
    private int userId;
    private int restaurantId;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private String paymentMode;
    private Connection conn;

    public OrderTable(Connection conn) {
        this.conn = conn;
    }

    // Constructor without Connection for use in retrieval
    public OrderTable(int orderId, int userId, int restaurantId, Date orderDate, double totalAmount, String status, String paymentMode) {
        this.orderId = orderId;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    // Constructor with Connection for inserting new orders
    public OrderTable(Connection conn, int userId, int restaurantId, Date orderDate, double totalAmount, String status, String paymentMode) {
        this.conn = conn;
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
        this.paymentMode = paymentMode;
    }

    public void saveOrder() throws SQLException {
        String query = "INSERT INTO ordertable (userId, restaurantId, orderDate, totalAmount, status, paymentMode) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, restaurantId);
            stmt.setDate(3, orderDate);
            stmt.setDouble(4, totalAmount);
            stmt.setString(5, status);
            stmt.setString(6, paymentMode);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }
        }
    }

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String toString() {
		return "OrderTable [orderId=" + orderId + ", userId=" + userId + ", restaurantId=" + restaurantId
				+ ", orderDate=" + orderDate + ", totalAmount=" + totalAmount + ", status=" + status + ", paymentMode="
				+ paymentMode + ", conn=" + conn + "]";
	}

    
}
