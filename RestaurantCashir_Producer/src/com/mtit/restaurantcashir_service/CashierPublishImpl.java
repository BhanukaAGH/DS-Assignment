package com.mtit.restaurantcashir_service;

import com.mtit.restaurantchef_service.DataStore;
import com.mtit.restaurantchef_service.Food;

import java.util.List;

public class CashierPublishImpl implements CashierPublish {

	private List<Food> foodsList = DataStore.foodsList; // Item list details in the supermarket
	private double bill; // to store the bill value
	private String[][] billDetails = new String[1000][4]; // To store the purchased item's details ,Assumption:only 1000
															// different items are allowed for an order
	private int count = -1; // to store the item count [starts from 0]

	@Override
	public List<Food> displayFood() {
		return DataStore.foodsList;
	}

	@Override
	public int generateBill(int id, int qty) {

		boolean valid = false;
		Food currentFood = null;
		for (Food tempFood : foodsList) {
			if (id == tempFood.getFoodId()) {
				currentFood = tempFood;
				valid = true;
				count++;
				break;
			}
		}

		if (valid) {
			this.bill = this.bill + (currentFood.getFoodPrice() * qty);

			billDetails[count][0] = Integer.toString(id);
			billDetails[count][1] = currentFood.getFoodName();

			billDetails[count][2] = Integer.toString(qty);
			billDetails[count][3] = Double.toString((currentFood.getFoodPrice() * qty));

			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public double displayFinalBillAmount() {

		double finalBill = this.bill;
		newBill();

		return finalBill;
	}

	@Override
	public String[][] displayBillDetails() {

		return billDetails;
	}

	public void newBill() {// To reset all relevant fields to default values
		this.bill = 0;
		this.count = -1;
	}

	public void addOrder(String foodDetails[][], int tableID) {
		Order order = new Order(OrderData.orderlist.size() + 1, "Cooking", tableID, foodDetails);
		OrderData.orderlist.add(order);
	}

	
	

}
