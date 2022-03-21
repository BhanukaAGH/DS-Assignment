package restaurantcook_consumer;

import com.mtit.restaurantcashir_service.Order;
import com.mtit.restaurantcashir_service.OrderData;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import restaurantcook_producer.CookPublish;

public class ServiceActivator implements BundleActivator {

	private static BundleContext context;
	
	ServiceReference serviceReference;
	Scanner scanner = new Scanner(System.in);

	static BundleContext getContext() {
		return context;
	
	}

	public void start(BundleContext context) throws Exception {
		System.out.println("............Restaurant Cook Consumer Started............");
		serviceReference = context.getServiceReference(CookPublish.class.getName());
		
		CookPublish cookPublish = (CookPublish) context.getService(serviceReference);
		
		int menuOption;
		String orderState;
		String backMenu;
		int orderId;
		
		while(true) {
			System.out.println("...............Welcome to Menu Management...............\n");

			System.out.println("Please Select an option to continue.....");
			System.out.println("Options");
			System.out.println("1. View Order");
			System.out.println("2. Update Order State");
			System.out.println("3. Check Order Status a order");
			System.out.println("4. Delete a order");
			System.out.println("5. Exit");
						

			System.out.println("Select your option...");
			menuOption = scanner.nextInt();
			scanner.nextLine();
			
			
			switch(menuOption) {
			
			
			case 1:
				for(Order order:OrderData.orderlist) {
					System.out.println( "order id : " +  order.getOrderID() );
					for (int i = 0; i < 2; i++) {
						    System.out.println("==============================================");
							System.out.println( "food name : " +  order.getFoodDetails()[i][1] );
							System.out.println( "food quntity : " +  order.getFoodDetails()[i][2] );
							System.out.println( "==============================================");
							
						System.out.println("");
					}
					
				}
				System.out.println("0. Go back to the Main Options");
				String back = scanner.nextLine();
				if(back.equals("0")) {
					break;
				}
											
			case 2:
				while(true) {
					System.out.println("Please enter bellow keyword to change order state");
					System.out.println("Enter 'wait' to process the order");
					System.out.println("Enter 'complete' to complete the order");
					System.out.println("6. Go back to the Main Options");
					orderState = scanner.nextLine();
					if(orderState.equals("6")) {
						break;
					}
					else {
						cookPublish.checkOrderStatus(orderState);
						System.out.println("Successfully Change the order status");
						System.out.println("0. Go back to the Main Options or press any key to continued");
						backMenu = scanner.nextLine();
						if (backMenu.equals("0")) {
							break;
						}
						else {
							continue;
						}
						
					}
				}
				break;
				
				
			case 3:
					System.out.println("Oder Status is " + cookPublish.getStatus());
					System.out.println("===Thank You===");
					System.out.println("0. Go back to the Main Options");
					String backStatus = scanner.nextLine();
					if(backStatus.equals("0")) {
						break;
					
				}
										
				break;
				
				
			case 4:
				while(true) {
					boolean isComplete =false;
					System.out.println("Please enter order id: ");
					orderId = scanner.nextInt();
					scanner.nextLine();
					
					Iterator it = OrderData.orderlist.iterator();
					while(it.hasNext()) {
						Order org = (Order) it.next();
						if(org.getOrderID()==orderId) {
							it.remove();
							isComplete=true;
							System.out.println("Order removed Successfully");
				            break;
						}
											
					}
					
					if(isComplete) {
						System.out.println("0. Go back to the Main Options or press any key to continued");
						String a = scanner.nextLine();
						scanner.nextLine();
						if (a.equals("0")) {
							break;
						}
						else {
							continue;
						}
					}
					else {
						System.out.println("please enter valid id");
						System.out.println("0. Go back to the Main Options or press any key to continued");
						String a = scanner.nextLine();
						scanner.nextLine();
						if (a.equals("0")) {
							break;
						}
						else {
							continue;
						}
					}				
					
				}
				break;
				
			
			
			case 5:
				System.out.println("Exit From the menu");
				System.out.println("-- Thank You --");
				return;
			  
			}
			
		}
			
			
		
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("............Restaurant Cook Consumer Stopped............");
		context.ungetService(serviceReference);
	}

}
