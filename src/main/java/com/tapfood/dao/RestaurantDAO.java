package com.tapfood.dao;

import java.util.ArrayList;

import com.tapfood.model.Restaurant;

public interface RestaurantDAO {

	void addrestaurant(Restaurant restaurant);
	
	Restaurant getrestaurant(int restaurantId);
	
	void updateRestaurant(Restaurant restaurant);
	
	void deleterestaurant(int restaurantId);
	
	ArrayList<Restaurant> getallrestaurant();
		
		
	
	
}
