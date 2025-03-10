package com.tapfood.model;

import java.time.LocalDateTime;

public class OrderHistory {
    private int ohid;
    private int userId;
    private int orderId;
    private double total;
    private LocalDateTime orderDate;

    // Getters and Setters
    public int getOhid() {
        return ohid;
    }

    public void setOhid(int ohid) {
        this.ohid = ohid;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
