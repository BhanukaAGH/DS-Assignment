package restaurantcook_producer;

public class CookPublishImpl implements CookPublish  {
	
	private static String status;


	public void  checkOrderStatus(String orderStatus) {
		status = orderStatus;
		if(orderStatus.equals("wait")) {
			System.out.println("Still Cooking Please wait!!!");
		}
		
		else if(orderStatus.equals("complete")) {
			
			System.out.println("Your Order Is Suceesfully Completed");
		}
		
		
	}


	public String getStatus() {
		return status;
		
	}
	
}
