package com.mtit.restaurantchef_service;

public class Food {
	private int foodId;
	private String foodName;
	private double foodPrice;

	public Food(int foodId, String foodName, double foodPrice) {
		super();
		this.foodId = foodId;
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

}
