package waiterproducer;

import java.util.List;

import com.mtit.restaurantcashir_service.Order;

public interface waiterproduce {
	public void updateOrderStatus(int orderID,String Status);
	public List<Order> orderList();
	public List<Order>findOrderbyID(int orderID);//get order details by orderID
	public List<Order>findOrderbyTID(int tableID);//get order details by TableID
	public boolean checkorderID(int orderID);//check if order id is valid
	public List<Order>CompletedOrder(String status);//return completed orders
	
}
