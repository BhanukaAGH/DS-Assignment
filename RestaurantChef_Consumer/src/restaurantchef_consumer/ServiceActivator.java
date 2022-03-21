package restaurantchef_consumer;

import com.mtit.restaurantchef_service.ChefPublish;
import com.mtit.restaurantchef_service.Food;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ServiceActivator implements BundleActivator {

	ServiceReference serviceReference;
	Scanner scanner = new Scanner(System.in);

	public void start(BundleContext context) throws Exception {
		System.out.println("............Restaurant Chef consumer started............");
		serviceReference = context.getServiceReference(ChefPublish.class.getName());
		@SuppressWarnings("unchecked")
		ChefPublish chefPublish = (ChefPublish) context.getService(serviceReference);

		int option;
		String back;
		int foodId;

		while (true) {
			System.out.println("...............Welcome to Menu Management...............\n");

			System.out.println("Please Select an option to continue.....");
			System.out.println("Options");
			System.out.println("1. Add a new food");
			System.out.println("2. Update the food details");
			System.out.println("3. Delete a food");
			System.out.println("4. List food items");
			System.out.println("5. Search foods by name");
			System.out.println("6. Exit");

			System.out.println("Select your option...");
			while (true) { // InputMismatchException Handling
				try {
					option = scanner.nextInt();
					scanner.nextLine();
					break;
				} catch (InputMismatchException e) {
					System.out.println("Please enter valid option...");
				}
				scanner.nextLine();
			}

			switch (option) {
			case 1:
				System.out.println("-- Add a new Item --");

				while (true) {
					System.out.print("Food name : ");
					String foodName = scanner.nextLine();

					double foodPrice;
					while (true) { // InputMismatchException Handling
						try {
							System.out.print("Food price (Rs.) : ");
							foodPrice = scanner.nextDouble();
							scanner.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.println("Please enter valid price...");
						}
						scanner.nextLine();
					}

					chefPublish.addFood(foodName, foodPrice);
					System.out.println("\nSuccessfully added food!");

					System.out.println("Press 0 to navigate back to home or press any other key to continue....");
					back = scanner.nextLine();

					if (back.equals("0")) {
						break;
					} else {
						continue;
					}
				}
				break;

			case 2:
				System.out.println("-- Update the food details --");
				while (true) {
					while (true) { // InputMismatchException Handling
						try {
							System.out.println("Options");
							System.out.println("1. Update Food Name");
							System.out.println("2. Update Food Price");
							System.out.println("3. Back");

							option = scanner.nextInt();
							scanner.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter valid option...");
						}
						scanner.nextLine();
					}

					if (option != 1 && option != 2 && option != 3) {
						System.out.println("!! Please enter valid option");
						continue;
					} else {
						break;
					}
				}

				while (true) {
					if (option == 3) {
						break;
					}

					while (true) { // InputMismatchException Handling
						try {
							System.out.print("Please enter food Id : ");
							foodId = scanner.nextInt();
							scanner.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter valid foodId...");
						}
						scanner.nextLine();
					}

					if (chefPublish.checkFoodByID(foodId)) {
						if (option == 1) {
							System.out.print("\nEnter Food Name : ");
							String foodName = scanner.nextLine();
							chefPublish.updateFoodName(foodId, foodName);
							System.out.println("\nSuccessfully updated food name!");
						} else if (option == 2) {
							while (true) {// InputMismatchException Handling
								try {
									System.out.print("\nEnter Food Price : ");
									double foodPrice = scanner.nextDouble();
									scanner.nextLine();
									chefPublish.updateFoodPrice(foodId, foodPrice);
									System.out.println("\nSuccessfully updated food price!");
									break;
								} catch (InputMismatchException e) {
									System.out.println("Please enter valid price...");
								}
								scanner.nextLine();
							}

						}
						break;
					} else {
						System.out.println("!! No food with this ID, Please enter a valid food ID.\n");
						continue;
					}
				}

				if (option != 3) {
					System.out.println("Press any key back to home....");
					back = scanner.nextLine();
				}
				break;

			case 3:
				System.out.println("-- Delete a food --");

				while (true) {
					while (true) { // InputMismatchException Handling
						try {
							System.out.print("Enter food ID to delete the food : ");
							foodId = scanner.nextInt();
							scanner.nextLine();
							break;
						} catch (InputMismatchException e) {
							System.out.println("\nPlease enter valid foodId...");
						}
						scanner.nextLine();
					}

					while (true) {
						if (chefPublish.checkFoodByID(foodId)) {
							chefPublish.removeFoodItem(foodId);
							System.out.println("\nSuccessfully removed the food!");
							break;
						} else {
							System.out.println(
									"!! No food with this ID, Please enter a valid food ID or Press 0 to back home.\n");

							while (true) { // InputMismatchException Handling
								try {
									foodId = scanner.nextInt();
									scanner.nextLine();
									break;
								} catch (InputMismatchException e) {
									System.out.println("Please enter valid foodId or 0...");
								}
								scanner.nextLine();
							}

							if (foodId == 0) {
								break;
							} else {
								continue;
							}
						}
					}

					if (foodId == 0) {
						break;
					}

					System.out.println("Press 0 to navigate back to home or press any other key to continue....");
					back = scanner.nextLine();
					if (back.equals("0")) {
						break;
					} else {
						continue;
					}
				}
				break;

			case 4:
				List<Food> foodList = chefPublish.foodList();
				System.out.println(
						"\n---------------------------List of foods on the Menu-------------------------------------");
				System.out.println(
						String.format("%10s %3s %30s %20s %20s", "Food ID", "|", "Food Name", "|", "Food Price"));
				System.out.println(String.format("%s",
						"-----------------------------------------------------------------------------------------"));

				for (Food food : foodList) {
					System.out.println(String.format("%10d %3s %30s %20s %20.2f", food.getFoodId(), "|",
							food.getFoodName(), "|", food.getFoodPrice()));
				}

				System.out.println(
						"-----------------------------------------------------------------------------------------\n");
				System.out.println("Press any key back to home....");
				back = scanner.nextLine();
				break;

			case 5:
				System.out.println("-- Search foods by name --");

				while (true) {
					System.out.print("Enter food name : ");
					String foodName = scanner.nextLine();
					List<Food> foodsList = chefPublish.findFoods(foodName);
					if (foodsList.size() > 0) {
						System.out.println("Food Found!\n");
						for (Food findFood : foodsList) {
							System.out.println("Food ID    : " + findFood.getFoodId());
							System.out.println("Food Name  : " + findFood.getFoodName());
							System.out.println("Food Price : " + findFood.getFoodPrice());
							System.out.println();
						}
					} else {
						System.out.println("Food not Found!");
					}

					System.out.println("\nPress 0 to navigate back to home or press any other key to continue....");
					back = scanner.nextLine();
					if (back.equals("0")) {
						break;
					} else {
						continue;
					}
				}
				break;

			case 6:
				System.out.println("-- Exit --");
				return;

			default:
				System.out.println("\nPlease enter valid option");
			}

			System.out.println("\n");

		}
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("............Restaurant Chef consumer stopped............");
		context.ungetService(serviceReference);
	}

}
