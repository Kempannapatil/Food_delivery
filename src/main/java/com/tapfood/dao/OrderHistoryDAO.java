package com.tapfood.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.tapfood.model.OrderHistory;

public class OrderHistoryDAO {
    private Connection connection;

    public OrderHistoryDAO(Connection connection) {
        this.connection = connection;
    }

    public void addOrderHistory(OrderHistory orderHistory) throws SQLException {
        String sql = "INSERT INTO orderhistory (userid, orderId, total, orderDate) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderHistory.getUserId());
            stmt.setInt(2, orderHistory.getOrderId());
            stmt.setDouble(3, orderHistory.getTotal());
            stmt.setTimestamp(4, Timestamp.valueOf(orderHistory.getOrderDate()));
            stmt.executeUpdate();
        }
    }

    public List<OrderHistory> getOrderHistoryByUserId(int userId) throws SQLException {
        List<OrderHistory> orderHistories = new ArrayList<>();
        String sql = "SELECT * FROM orderhistory WHERE userid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                OrderHistory oh = new OrderHistory();
                oh.setOhid(rs.getInt("ohid"));
                oh.setUserId(rs.getInt("userid"));
                oh.setOrderId(rs.getInt("orderId"));
                oh.setTotal(rs.getDouble("total"));
                oh.setOrderDate(rs.getTimestamp("orderDate").toLocalDateTime());
                orderHistories.add(oh);
            }
        }
        return orderHistories;
    }
}
