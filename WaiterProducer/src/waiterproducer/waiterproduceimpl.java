package waiterproducer;

import java.util.ArrayList;
import java.util.List;
import com.mtit.restaurantcashir_service.Order;
import com.mtit.restaurantcashir_service.OrderData;

import tablemanagerproducer.TableManagerProducerImpl;

public class waiterproduceimpl implements waiterproduce {

	@Override
	public void updateOrderStatus(int orderID, String Status) {
		for(Order order: OrderData.orderlist) {
			if(order.getOrderID()==orderID) {
				order.setOrderStatus(Status);
				break;
			}
		
	 }
	}

	@Override
	public List<Order> orderList() {
		// TODO Auto-generated method stub
		return OrderData.orderlist;
	}

	@Override
	public List<Order> findOrderbyID(int orderID) {
		List<Order> orderList=new ArrayList<>();
		for(Order order: OrderData.orderlist) {
			if(order.getOrderID()==orderID) {
				orderList.add(order);
			}
			
		}
		return orderList;
	}

	@Override
	public boolean checkorderID(int orderID) {//method check if order ID exists
		for(Order order:OrderData.orderlist) {
			if(order.getOrderID()==orderID) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public List<Order> findOrderbyTID(int tableID) {
		List<Order> orderList=new ArrayList<>();
		for(Order order: OrderData.orderlist) {
			if(order.getTableID()==tableID) {
				orderList.add(order);
			}
		}
		return orderList;
	}

	@Override
	public List<Order> CompletedOrder(String status) {
		List<Order> orderList=new ArrayList<>();
		for(Order order: OrderData.orderlist) {
			if(order.getOrderStatus()==status) {
				orderList.add(order);
			}
			
		}
		
		return orderList;
	}
	

}
