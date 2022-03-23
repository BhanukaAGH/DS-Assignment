package restaurantcashir_consumer;

import com.mtit.restaurantcashir_service.CashierPublish;
import com.mtit.restaurantcashir_service.Order;
import com.mtit.restaurantcashir_service.OrderData;
import com.mtit.restaurantchef_service.Food;

import tablemanagerproducer.Table;
import tablemanagerproducer.TableManagerProduce;

import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	ServiceReference tablemanagerReference;//for table manager
	private boolean exit = false;
	Scanner scanner = new Scanner(System.in);

	public void start(BundleContext context) throws Exception {
		System.out.println("============Restuarant cashier consumer started.============");
		serviceReference = context.getServiceReference(CashierPublish.class.getName());
		tablemanagerReference = context.getServiceReference(TableManagerProduce.class.getName());//for table manager
		TableManagerProduce tableproduce = (TableManagerProduce) context.getService(tablemanagerReference);//for table manager
		
		@SuppressWarnings("unchecked")
		CashierPublish cashierPublish = (CashierPublish) context.getService(serviceReference);

		// CashierPublish Instance
		do {
			int selection = 4;
			do {
				System.out.println("----------------------------Welcome to Billing-------------------------------");

				System.out.println("Please Select an option to continue.....");
				System.out.println("Options");
				System.out.println("1.View Food List");
				System.out.println("2.Generate customer bills");
				System.out.println("3.Exit");

				System.out.println("Enter your selection...");
				selection = scanner.nextInt();

				scanner.nextLine();
				if (selection == 3) {// Exits from the cashier consumer program
					exit = true;
				}

				if (selection != 1 && selection != 2 && selection != 3) {
					System.out.println("Please enter a valid selection");
				}
			} while (selection != 1 && selection != 2 && selection != 3);

			String backToHome = null;
			String calculateFinalBill = null;
			int foodCount = -1;
			if (selection == 1) {// Handles the viewing process of item list
				do {

					List<Food> foodsList = cashierPublish.displayFood();// Consumes the CashierService displayItems()
					System.out.println(
							"-----------------------------------Food list--------------------------------------------");
					System.out.println("Food ID:" + "\t" + "Food Name:" + "\t" + "Food Price:" + "\t");

					for (Food tempFood : foodsList) {
						System.out.println(tempFood.getFoodId() + "\t         " + tempFood.getFoodName() + "\t         "
								+ tempFood.getFoodPrice() + "\t" + "\n");

						System.out.println(
								"-----------------------------------------------------------------------------------------");

					}

					System.out.println("Press 0 to navigate back to home or press any other key to continue....");

					backToHome = scanner.nextLine();
				}

				while (!(backToHome.equals("0")));

			}

			else if (selection == 2) {// Handles the billing process
				do {
					System.out.println(
							"---------------------Welcome to next customer's billing --------------------" + "\n");
					do {

						
						System.out.println("Enter the Food id");
						int id = scanner.nextInt();

						System.out.println("Enter the quantity");
						int qty = scanner.nextInt();

						int result = cashierPublish.generateBill(id, qty);// Consumes the CashierService geenrateBill()
						if (result == -1) {
							System.out.println("Please enter a valid food number!!");
						} else {
							foodCount++;
						}
						scanner.nextLine();
						System.out.println(
								"Press y to calclate the totoal bill or any other key to continue the billing....");
						calculateFinalBill = scanner.nextLine();

					} while (!(calculateFinalBill.equals("y")));

					System.out.println(
							"------------------------------------------Receipt----------------------------------------");
					String[][] billDetails = cashierPublish.displayBillDetails();// Consumes the cashierService
																					// displaybillDetails()

					String format = "%-20s";
					System.out.printf(format, "Food ID:");
					System.out.printf(format, "Food Name:");
					System.out.printf(format, "Food Qty:");
					System.out.printf(format, "Total:");
					System.out.println("");
					for (int i = 0; i <= foodCount; i++) {
						for (int j = 0; j < billDetails[i].length; j++) {

							System.out.printf(format, billDetails[i][j]);

						}
						System.out.println("");
					}
					System.out.println("                                                          ----------");
					System.out.println("Subtotal:                                                   "
							+ cashierPublish.displayFinalBillAmount());// Consumes the cashierService
																		// displayFinalBillAmount()
					System.out.println("                                                          ----------");
					System.out.println("                                                          ----------");
					System.out.println(
							"-------------------------------------------------------------------------------------------");
       //Rivindu Changed to Check if table id is valid of tables and accept only tables from database

					//
					int tableID=0;
					while(true) {
						while(true) {
							System.out.print("2) Table ID:");
							String userInput=scanner.next();
							try {
								tableID = Integer.parseInt(userInput);
								if(tableID<1) {
									System.out.println("\n ID cant be less than 1 ");
									continue;
								}
								break;
							}catch (NumberFormatException e) {
							    System.out.println("Table ID can only be integer type Enter an Integer");
							    continue;
							    
							}
						}
						
				if(tableproduce.checkTableAvailability(tableID)) {
						System.out.println("Table with ID "+tableID+" Found");
						cashierPublish.addOrder(billDetails, tableID);
						break;
					}else {
						System.out.println("Table with ID "+tableID+" NOT FOUND");
						System.out.println("\n Enter 0 to exit or any other key to reenter ");
						String back = scanner.next();
						if (back.equals("0")) {
							break;
						}else {
							continue;
							}
					}
				}
				//
					
					
					for(Order order : OrderData.orderlist) {
						System.out.println(order.getOrderID());
						System.out.println(order.getTableID());
						System.out.println(order.getOrderStatus());
						for (int i = 0; i <= foodCount; i++) {
							for (int j = 0; j < order.getFoodDetails()[i].length; j++) {
								System.out.printf(format, order.getFoodDetails()[i][j]);
							}
							System.out.println("");
						}
					}

					foodCount = -1;

					break;

				}

				while (!(backToHome.equals("0")));
			} else if (selection == 3) {
				return;
			}
		} while (!exit);
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("============Supermarket cashier consumer stopeed.============");
		context.ungetService(serviceReference);
	}

}
