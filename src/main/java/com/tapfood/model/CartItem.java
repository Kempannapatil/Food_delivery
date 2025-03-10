package com.tapfood.model;

public class CartItem {
    private int itemid;
    private int restaurantid;
    private String itemname;
    private int quantity;
    private double price;

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    // Method to calculate the total price for this item
    public double getTotalPrice() {
        return price * quantity;
    }

    public CartItem(int itemid, int restaurantid, String itemname, int quantity, double price) {
        this.itemid = itemid;
        this.restaurantid = restaurantid;
        this.itemname = itemname;
        this.quantity = quantity;
        this.price = price;
    }

    @Override
    public String toString() {
        return "CartItem [itemid=" + itemid + ", restaurantid=" + restaurantid + ", itemname=" + itemname
                + ", quantity=" + quantity + ", price=" + price + "]";
    }
}
