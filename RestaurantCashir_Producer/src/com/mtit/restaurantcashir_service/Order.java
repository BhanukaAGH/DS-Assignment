package com.mtit.restaurantcashir_service;

import java.util.HashMap;

import com.mtit.restaurantchef_service.Food;

public class Order {
	private int orderID;
	private String orderStatus;
	private int tableID;
	private String[][] orderDetails;

	public Order(int orderID, String orderStatus, int tableID, String[][] orderDetails) {
		super();
		this.orderID = orderID;
		this.orderStatus = orderStatus;
		this.tableID = tableID;
		this.orderDetails = orderDetails;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String[][] getFoodDetails() {
		return orderDetails;
	}

	public void setFoodDetails(String[][] foodDetails) {
		this.orderDetails = foodDetails;
	}

}
