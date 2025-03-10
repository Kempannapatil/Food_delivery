package com.tapfood.model;

public class Menu {

	private int menuId;
	private int restaurantId;
	private String itemName;
	private double price;
	private boolean isAvailable;
	public Menu() {
		
	}
	public Menu(int restaurantId, String itemName, double price, boolean isAvailable) {
		super();
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.price = price;
		this.isAvailable = isAvailable;
	}
	public Menu(int menuId, int restaurantId, String itemName, double price, boolean isAvailable) {
		super();
		this.menuId = menuId;
		this.restaurantId = restaurantId;
		this.itemName = itemName;
		this.price = price;
		this.isAvailable = isAvailable;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getRestaurantId() {
		return restaurantId;
	}
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isAvailable() {
		return isAvailable;
	}
	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}
	
	
	@Override
	public String toString() {
		return "Menu [menuId=" + menuId + ", restaurantId=" + restaurantId + ", itemName=" + itemName + ", price="
				+ price + ", isAvailable=" + isAvailable + "]";
	}
	
	
}
