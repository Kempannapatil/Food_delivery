package com.tapfood.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderItem {
    private int orderItemId;
    private int orderId;
    private int menuId;
    private String itemName; // Add this field for the item name
    private int quantity;
    private double total;
    private Connection conn;

    // Constructor without Connection for use in retrieval
    public OrderItem(int orderItemId, int orderId, int menuId, String itemName, int quantity, double total) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.menuId = menuId;
        this.itemName = itemName; // Initialize item name
        this.quantity = quantity;
        this.total = total;
    }

    // Constructor with Connection for inserting new items
    public OrderItem(Connection conn, int orderId, int menuId, String itemName, int quantity, double total) {
        this.conn = conn;
        this.orderId = orderId;
        this.menuId = menuId;
        this.itemName = itemName; // Initialize item name
        this.quantity = quantity;
        this.total = total;
    }

    public void saveOrderItem() throws SQLException {
        String query = "INSERT INTO orderitem (orderId, menuId, quantity, itemTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, menuId);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, total);
            stmt.executeUpdate();
        }
    }

    // Method to retrieve OrderItem details from the database
    public static OrderItem getOrderItem(Connection conn, int orderItemId) throws SQLException {
        String query = "SELECT * FROM orderitem WHERE orderItemId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, orderItemId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrderItem(
                        rs.getInt("orderItemId"),
                        rs.getInt("orderId"),
                        rs.getInt("menuId"),
                        rs.getString("itemName"), // Fetch the item name
                        rs.getInt("quantity"),
                        rs.getDouble("itemTotal")
                    );
                }
            }
        }
        return null; // Return null if not found
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getItemName() { // Getter for itemName
        return itemName;
    }

    public void setItemName(String itemName) { // Setter for itemName
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Connection getConn() {
        return conn;
    }

    public void setConn(Connection conn) {
        this.conn = conn;
    }

    @Override
    public String toString() {
        return "OrderItem [orderItemId=" + orderItemId + ", orderId=" + orderId + ", menuId=" + menuId + ", itemName=" + itemName +
               ", quantity=" + quantity + ", total=" + total + "]";
    }
}
