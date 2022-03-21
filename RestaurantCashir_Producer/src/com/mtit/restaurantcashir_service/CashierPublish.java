package com.mtit.restaurantcashir_service;

import java.util.List;

import com.mtit.restaurantchef_service.Food;

public interface CashierPublish {

	public List<Food> displayFood(); // return the item list with item details

	public int generateBill(int id, int qty); // calculates the on going bill continuously

	public double displayFinalBillAmount(); // displays the final bill amount

	public String[][] displayBillDetails(); // returns the purchased item list with details

	public void addOrder(String foodID[][], int tableID);//add order deatils to datastore
	
	

}
