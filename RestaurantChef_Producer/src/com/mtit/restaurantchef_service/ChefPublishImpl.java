package com.mtit.restaurantchef_service;

import java.util.ArrayList;
import java.util.List;

public class ChefPublishImpl implements ChefPublish {

	@Override
	public void addFood(String foodName, double foodPrice) {
		Food newFood = new Food(DataStore.foodsList.size() + 1, foodName.trim(), foodPrice);
		DataStore.foodsList.add(newFood);
	}

	@Override
	public void updateFoodName(int foodId, String foodName) {
		for (Food food : DataStore.foodsList) {
			if (food.getFoodId() == foodId) {
				food.setFoodName(foodName.trim());
				break;
			}
		}
	}

	@Override
	public void updateFoodPrice(int foodId, double foodPrice) {
		for (Food food : DataStore.foodsList) {
			if (food.getFoodId() == foodId) {
				food.setFoodPrice(foodPrice);
				break;
			}
		}
	}

	@Override
	public void removeFoodItem(int foodId) {
		int foodIndex = 0;
		for (Food food : DataStore.foodsList) {
			if (food.getFoodId() == foodId) {
				DataStore.foodsList.remove(foodIndex);
				break;
			}
			foodIndex++;
		}
	}

	@Override
	public List<Food> findFoods(String foodName) {
		List<Food> foodList = new ArrayList<>();
		for (Food food : DataStore.foodsList) {
			if (food.getFoodName().toLowerCase().contains(foodName.trim().toLowerCase())) {
				foodList.add(food);
			}
		}
		return foodList;
	}

	@Override
	public boolean checkFoodByID(int foodId) {
		for (Food food : DataStore.foodsList) {
			if (food.getFoodId() == foodId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public List<Food> foodList() {
		return DataStore.foodsList;
	}

}
