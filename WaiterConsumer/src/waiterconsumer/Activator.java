package waiterconsumer;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.mtit.restaurantcashir_service.Order;
import com.mtit.restaurantcashir_service.OrderData;

import tablemanagerproducer.Table;
import tablemanagerproducer.TableManagerProduce;
import waiterproducer.waiterproduce;

public class Activator implements BundleActivator {
	
	ServiceReference Reference;
	
	ServiceReference tablemanagerReference;// reference for table
	Scanner scanner = new Scanner(System.in);

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("============Waiter consumer started.============");
		Reference=bundleContext.getServiceReference(waiterproduce.class.getName());
		tablemanagerReference=bundleContext.getServiceReference(TableManagerProduce.class.getName());
		
		@SuppressWarnings("unchecked")
		waiterproduce waiter=(waiterproduce) bundleContext.getService(Reference);//produce reference for waiter
		@SuppressWarnings("unchecked")
		TableManagerProduce tableproduce = (TableManagerProduce) bundleContext.getService(tablemanagerReference);//produce reference for table
		int foodCount = -1;
		String format = "%10s";
		int oorderID = 0;
		int stableID;
		String back;
		while(true) {
			System.out.println("\n");
			System.out.println("...............Waiter Tasks...............\n");
			System.out.println("Select task continue-------------------");
			System.out.println(" 1. View all Orders");
			System.out.println(" 2. Check Order Details & status By OrderID");
			System.out.println(" 3. Check Order Details & status By TableID");
			System.out.println(" 4. Set OrderStatus to Delivered by Entering OrderID");
			System.out.println(" 5. Set OrderStatus to Delivered by Entering TableID");
			System.out.println(" 6. View Delivered/Completed Orders");
			System.out.println(" 7. Exit");
			
			//
			int task=0;
			while(true) {//check if user enters correct value
				System.out.println("Enter Task Number:");
				String ttask = scanner.next();
				try {
				
				task = Integer.parseInt(ttask); 
					if(task>=1 && task<=7) {
						break;
					}else {
						System.out.print("Enter Value from 1 to 7");
						continue;
					}
				}catch (NumberFormatException e) {
					System.out.print("INVALID ENTRY Enter Integer from 1 to 7");
					continue;
				}
			
			}
			//
			
		switch (task) {
			case 1:
				System.out.println("\n----------------------List of Orders & Details----------------------------------------------------------------------------------------------");
				
				System.out.println("\n");
				
		      
				for(Order order : OrderData.orderlist) {
					System.out.println("--------------------------------------------------------");
					System.out.println("Order ID: "+ order.getOrderID());
					System.out.println("Table ID: "+ order.getTableID());
					System.out.println("Order Status: "+ order.getOrderStatus());
					List<Table> tablelist=tableproduce.findtablebyID(order.getTableID());
					
					for(Table findTable:tablelist) {
						System.out.println("Table Location: "+ findTable.getLocation());
					}
					System.out.println("");
				try {
					System.out.printf("%10s %10s %10s %10s ","Food ID","Food Name","Qty","Price");
					System.out.println("");
					boolean x = false;
					  foodCount=20;
					for (int i = 0; i <= foodCount; i++) {
						for (int j = 0; j < order.getFoodDetails()[i].length; j++) {
							x= order.getFoodDetails()[i][j].equals(null);
							if(x==true) {break;}
							System.out.printf("%10s", order.getFoodDetails()[i][j]);
							
						}
						System.out.println("");
						if(x==true) {break;}
						
						
						
					}
				}catch(Exception e) {System.out.println("--------------------------------------------------------");}
				}
				
				
				
				break;
			case 2:
				System.out.println("\n");
			try {	
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::FIND Order BY OrderID :::::::::::::::");
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Order ID: ");
						String userInput=scanner.next();
						try {
							oorderID = Integer.parseInt(userInput);
							if(oorderID<1) {
								System.out.println("\n OrderID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("OrderID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					//
					if(waiter.checkorderID(oorderID)) {//check orderid exists
						List<Order> orderlist=waiter.findOrderbyID(oorderID);
						System.out.println("Details about Order............."+oorderID);
						
						for(Order findorder :orderlist) {
							System.out.println("--------------------------------------------------------");
							System.out.println("Order ID: "+findorder.getOrderID());
							System.out.println("Table ID: "+findorder.getTableID());
							System.out.println("Order Status: "+findorder.getOrderStatus());
							
							List<Table> tablelist=tableproduce.findtablebyID(findorder.getTableID());
							
							for(Table findTable:tablelist) {
								System.out.println("Table Location: "+ findTable.getLocation());
							}
							System.out.printf("%10s %10s %10s %10s ","Food ID","Food Name","Qty","Price");
							System.out.println("");
							boolean x = false;
							  foodCount=20;
							for (int i = 0; i <= foodCount; i++) {
								for (int j = 0; j < findorder.getFoodDetails()[i].length; j++) {
									x= findorder.getFoodDetails()[i][j].equals(null);
									if(x==true) {break;}
									System.out.printf("%10s", findorder.getFoodDetails()[i][j]);
								}
								if(x==true) {break;}
								System.out.println("");
								
								
							}
						}
						
					}else {
						System.out.println("***NO Order WITH ID "+oorderID+" EXIST***");
						System.out.println("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					//
				}
			    }catch(Exception e) {foodCount=-1;System.out.println("--------------------------------------------------------");}
			break;
				
			case 3:
			try {
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::FIND Order BY TableID :::::::::::::::");
					//
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n TableID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("TableID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					
					//
					if(tableproduce.checkTableAvailability(stableID)) {
						
						List<Order> orderlist=waiter.findOrderbyTID(stableID);
						System.out.println("Details about Order with Table ID............."+stableID);
						
						for(Order findorder :orderlist) {
							System.out.println("--------------------------------------------------------");
							System.out.println("Order ID: "+findorder.getOrderID());
							System.out.println("Table ID: "+findorder.getTableID());
							System.out.println("Order Status: "+findorder.getOrderStatus());
							
							List<Table> tablelist=tableproduce.findtablebyID(findorder.getTableID());
							
							for(Table findTable:tablelist) {
								System.out.println("Table Location: "+ findTable.getLocation());
							}
							System.out.printf("%10s %10s %10s %10s ","Food ID","Food Name","Qty","Price");
							System.out.println("");
							boolean x = false;
							  foodCount=20;
							for (int i = 0; i <= foodCount; i++) {
								for (int j = 0; j < findorder.getFoodDetails()[i].length; j++) {
									x= findorder.getFoodDetails()[i][j].equals(null);
									if(x==true) {break;}
									System.out.printf(format, findorder.getFoodDetails()[i][j]);
								}
								if(x==true) {break;}
								System.out.println("");
								
								
							}
							System.out.println("--------------------------------------------------------");
						
						}	
						
					}else {
						System.out.println("***NO Table WITH ID "+stableID+" EXIST***");
						System.out.println("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					//
					
				}
			}catch(Exception e) {foodCount = -1; ;}
				break;
			case 4:
				while(true) {
					System.out.println(":::::::::::::::Set Order Status to Delivered  BY Entering OrderID :::::::::::::::");
					//
					while(true) {//check if entered  id is valid format
						System.out.print("Enter Order ID: ");
						String userInput=scanner.next();
						try {
							oorderID = Integer.parseInt(userInput);
							if(oorderID<1) {
								System.out.println("\n orderID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("OrderID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					if(waiter.checkorderID(oorderID)) {//check if entered id exists
						System.out.println("UPDATE TYPE");
						System.out.println("   *Enter 1 if you want to set status to complete");
						System.out.println("   *Enter 2 if you want to set status to delivered");
						
						int updatetype=0;
						while(true) {
							System.out.print("\n Enter:");
							String ttype=scanner.next();
							try {
								updatetype = Integer.parseInt(ttype);
								if(updatetype==1  || updatetype==2) {//check if user only enter 1 or 2
									break;
								}else {
								System.out.println("Enter only  1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to set status to complete");
								System.out.println("   *Enter 2 if you want to set status to delivered");
								continue;
								}
								
							}catch (NumberFormatException e) {
								System.out.println("Enter only Integer 1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to set status to complete");
								System.out.println("   *Enter 2 if you want to set status to delivered");
							    continue;
							    
							}
						}
						//
						if(updatetype==1) {
						waiter.updateOrderStatus(oorderID, "complete");
						System.out.println("\n Order Status updated to complete ");
						}else {
							waiter.updateOrderStatus(oorderID, "delivered");
							System.out.println("\n Order Status updated to delivered ");
						}
						
						System.out.println("\n Enter 0 to exit or any other key to reenter ");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						}else {
							continue;
							}
						
					}else {
						System.out.println("\n There is No Order with ORDER ID "+oorderID);
						System.out.println("\n Enter 0 to exit or any other key to reenter ");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						}else {
							continue;
							}
						
					}
					
				}break;
			case 5:
				while(true) {
					System.out.println(":::::::::::::::Set Order Status to Delivered  BY TableID :::::::::::::::");
					//
					while(true) {//check if entered  id is valid format
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n TableID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("TableID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					if(tableproduce.checkTableAvailability(stableID)) {
						List<Order> orderlist=waiter.findOrderbyTID(stableID);
						for(Order findorder :orderlist) {
							 oorderID=findorder.getOrderID();
						}
						//
						System.out.println("UPDATE TYPE");
						System.out.println("   *Enter 1 if you want to set status to complete");
						System.out.println("   *Enter 2 if you want to set status to delivered");
						
						int updatetype=0;
						while(true) {
							System.out.print("\n Enter:");
							String ttype=scanner.next();
							try {
								updatetype = Integer.parseInt(ttype);
								if(updatetype==1  || updatetype==2) {//check if user only enter 1 or 2
									break;
								}else {
								System.out.println("Enter only  1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to set status to complete");
								System.out.println("   *Enter 2 if you want to set status to delivered");
								continue;
								}
								
							}catch (NumberFormatException e) {
								System.out.println("Enter only Integer 1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to set status to complete");
								System.out.println("   *Enter 2 if you want to set status to delivered");
							    continue;
							    
							}
						}
						//
						if(updatetype==1) {
						waiter.updateOrderStatus(oorderID, "complete");
						System.out.println("\n Order Status updated to complete ");
						}else {
							waiter.updateOrderStatus(oorderID, "delivered");
							System.out.println("\n Order Status updated to delivered ");
						}
						
						//
						System.out.println("\n Enter 0 to exit or any other key to reenter ");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						}else {
							continue;
							}
						
					}else {
						System.out.println("\n No Order Placed in Table ID "+stableID);
						System.out.println("\n Enter 0 to exit or any other key to reenter ");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						}else {
							continue;
							}
						
					}
					
				}break;
				
			case 6:
				
				System.out.println("\n----------------------List of Completed Orders & Details-------------------------------");
		        System.out.println();
		        System.out.println("-----------------------------------------------------------------------------");
		        String a="complete";
		        String b="delivered";
		        String x=null;
		         //String a="Cooking"; 
		        
				System.out.println("View TYPE");
				System.out.println("   *Enter 1 if you want to see all completed orders");
				System.out.println("   *Enter 2 if you want to see all delivered order");
				
				int updatetype=0;
				while(true) {
					System.out.print("\n Enter:");
					String ttype=scanner.next();
					try {
						updatetype = Integer.parseInt(ttype);
						if(updatetype==1  || updatetype==2) {//check if user only enter 1 or 2
							break;
						}else {
						System.out.println("Enter only  1 or 2");
						System.out.println("View TYPE");
						System.out.println("   *Enter 1 if you want to see all completed orders");
						System.out.println("   *Enter 2 if you want to see all delivered order");
						continue;
						}
						
					}catch (NumberFormatException e) {
						System.out.println("Enter only Integer 1 or 2");
						System.out.println("View TYPE");
						System.out.println("   *Enter 1 if you want to see all completed orders");
						System.out.println("   *Enter 2 if you want to see all delivered order");
					    continue;
					    
					}
				}
				//
				if(updatetype==1) {
					x=a;
				}else{
					x=b;
					}
		        
				List<Order> completedOrders=waiter.CompletedOrder(x);
				System.out.println("--------------------------------------------------------");
				for(Order orders:completedOrders) {
					System.out.println("Order ID: "+orders.getOrderID());
					System.out.println("Table ID: "+orders.getTableID());
					System.out.println("Order Status: "+orders.getOrderStatus());
					
				  List<Table> tablelist=tableproduce.findtablebyID(orders.getTableID());
					
					for(Table findTable:tablelist) {
						System.out.println("Table Location: "+ findTable.getLocation());
					}
					System.out.printf("%10s %10s %10s %10s ","Food ID","Food Name","Qty","Price");
					for (int i = 0; i <= foodCount; i++) {
						for (int j = 0; j < orders.getFoodDetails()[i].length; j++) {
							System.out.printf(format, orders.getFoodDetails()[i][j]);
						}
						System.out.println("");
					}
					System.out.println("");
					System.out.println("--------------------------------------------------------");
					
				}
				foodCount = -1;
				
				break;
			
			case 7:
				System.out.println("-- Exit --");
				return;
				
			default:
				System.out.println("\nPlease enter valid option");
				continue;
				
				

			}
			
		}
		
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("............Waiter Consumer Closed............");
		bundleContext.ungetService(Reference);

	}

}
