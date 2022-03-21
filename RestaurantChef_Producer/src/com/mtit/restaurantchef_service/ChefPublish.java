package com.mtit.restaurantchef_service;

import java.util.List;

public interface ChefPublish {
	public void addFood(String foodName, double foodPrice);

	public void updateFoodName(int foodId, String foodName);

	public void updateFoodPrice(int foodId, double foodPrice);

	public void removeFoodItem(int foodId);

	public List<Food> findFoods(String foodName);

	public boolean checkFoodByID(int foodId);

	public List<Food> foodList();
}
