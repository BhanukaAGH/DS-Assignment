package tablemanagerconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import tablemanagerproducer.Table;
import tablemanagerproducer.TableManagerProduce;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TableManagerConsumerActivator implements BundleActivator {
	ServiceReference tablemanagerReference;
	Scanner scanner = new Scanner(System.in);

	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("............Table Manager Consumer started............");
		tablemanagerReference = bundleContext.getServiceReference(TableManagerProduce.class.getName());
		@SuppressWarnings("unchecked")
		TableManagerProduce tableproduce = (TableManagerProduce) bundleContext.getService(tablemanagerReference);
		
		
		String back;
		int stableID;//to get table id
		int tableID;
		
		
		
		
		
		while(true) {
			System.out.println("\n");
			System.out.println("...............Table Manager Tasks Page...............\n");
			System.out.println("Select task continue-------------------");
			System.out.println(" 1. Add Table");
			System.out.println(" 2. Update Table Status");
			System.out.println(" 3. Update Number of Seats");
			System.out.println(" 4. Delete Table");
			System.out.println(" 5. Search Table by ID");
			System.out.println(" 6. Search Table by SeatCount");
			System.out.println(" 7. Check Table Status");
			System.out.println(" 8. View List of Tables");
			System.out.println(" 9. Exit");
			
			
			
			
			//
			int task=0;
			while(true) {//check if user enters correct value
				System.out.println("Enter Task Number:");
				String ttask = scanner.next();
				try {
				
				task = Integer.parseInt(ttask); 
					if(task>=1 && task<=9) {
						break;
					}else {
						System.out.print("Enter Value from 1 to 9");
						continue;
					}
				}catch (NumberFormatException e) {
					System.out.print("INVALID ENTRY Enter Integer from 1 to 9");
					continue;
				}
			
			}
			//
			
			switch(task) {
			case 1:
				System.out.println("\n");
				
				
			while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::Add Table:::::::::::::::");
					

			   int NoSeats = 0;
					
				while(true) {//to ensure user doesnt enter a wrong data type

					System.out.print("\n1) Number of Seats: ");
					String userInput = scanner.next(); //scan the input
					
					
					try {
						NoSeats = Integer.parseInt(userInput); // try to parse the "number" to int
						if(NoSeats<1) {
							System.out.print("\n Number of Seats cant be less than 1 ");
							continue;
						}
						break;
					} catch (NumberFormatException e) {
					    System.out.println("Number of Seats can only be integer type Enter an Integer");
					    continue;
					    
					}
				}
				
					

					
					//check if table with entered ID already exists
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
							System.out.println("Table with ID Already exists!! Reneter");
							continue;
						}else {
							break;
						}
					}
					
				
					
					
					//to enter location
					
					String type1="Outdoor";
					String type2="Indoor";
					String location = type1;
					
					while(true) {
						System.out.println("3) Enter location");
						System.out.println("   *Enter 1 for Outdoor");
						System.out.print("   *Enter 2 for Indoor");
						
						int type=0;
						while(true) {
							System.out.print("\n Enter:");
							String ttype=scanner.next();
							try {
								
								type = Integer.parseInt(ttype);
								break;
							}catch (NumberFormatException e) {
								System.out.println("3) Enter location");
								System.out.println("   *Enter 1 for Outdoor");
								System.out.print("   *Enter 2 for Indoor");
							    System.out.println("Enter only Integer 1 or 2");
							    continue;
							    
							}
						}
						
					if(type==1) {						
						 location=type1;
						 break;
					}else if(type==2) {
						 location=type2;
						 break;
					}else {
						System.out.println("  \n INVALID ENTRY Enter 1 or 2");
						continue;
						
					 }
					}
					
					
					//to enter status of table
					String status1="Available";
					String status2="Reserved";
					String Status = status1;
					
					
					
					
					while(true) {
						System.out.println("4) Status of table");
						System.out.println("    *Enter 1 for Available");
						System.out.print("    *Enter 2 for Reserved");
						int type=0;
						while(true) {
							System.out.print("\n Enter:");
							String ttype=scanner.next();
							try {
								
								type = Integer.parseInt(ttype);
								break;
							}catch (NumberFormatException e) {
								System.out.println("4) Status of table");
								System.out.println("    *Enter 1 for Available");
								System.out.print("    *Enter 2 for Reserved");
							    System.out.println("Enter only Integer 1 or 2");
							    continue;
							    
							}
						}
					if(type==1) {						
						Status=status1;
						 break;
					}else if(type==2) {
						Status=status2;
						 break;
					}else {
						System.out.println(" \n INVALID ENTRY Enter 1 or 2 ");
						continue;
						
					 }
					}
					scanner.nextLine();
					

					tableproduce.addTable( NoSeats,  location,  tableID,  Status);
					
					System.out.println("\n Table added");
					System.out.println("\n");

					// retrieve new table details
						List<Table> tablelist=tableproduce.findtablebyID(tableID);
						
						System.out.println("Details of new table added............. "+tableID);
						System.out.println("-----------------------------------------------------------------------------");
						System.out.printf("%12s %20s %20s %15s", "Table ID", "Table Location", "Number Of Seats", "Status");
						
						
						for(Table findTable:tablelist) {
							/*System.out.println(" Table ID: "+ findTable.getTableID());
							System.out.println(" Table Location: "+ findTable.getLocation());
							System.out.println(" Number of Seats: "+ findTable.getNoSeats());
							System.out.println(" Status of table: "+ findTable.getStatus());*/
							System.out.print("\n");
							System.out.printf("%6s %20s %20s %20s",findTable.getTableID(), findTable.getLocation(), findTable.getNoSeats(), findTable.getStatus());
							
						}
						
					
					//
					System.out.print("\n");
					System.out.println("-----------------------------------------------------------------------------");
					System.out.print("\n");
					
					System.out.println("\n Enter 0 to navigate back to home or press any other key to continue....");
					back = scanner.next();
					
					
					if (back.equals("0")) {
						break;
					} else {
						continue;
					}
				}
				
				  break;
				
			
			case 2://case 2 Update Table Status
				System.out.println("\n");
				
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::Update Table Status:::::::::::::::");
			
					
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n ID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("Table ID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					
					
					if(tableproduce.checkTableAvailability(stableID)) {
						System.out.println("UPDATE TYPE");
						System.out.println("   *Enter 1 if you want to update table to Available");
						System.out.println("   *Enter 2 if you want to update table to Reserved");
						
						/*System.out.print("Enter: ");
						updatetype = scanner.nextInt();
						scanner.nextLine();*/
						//
						int updatetype=0;
						while(true) {
							System.out.print("\nEnter:");
							String ttype=scanner.next();
							try {
								updatetype = Integer.parseInt(ttype);
								if(updatetype==1  || updatetype==2) {//check if user only enter 1 or 2
									break;
								}else {
								System.out.println("Enter only  1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to update table to Available");
								System.out.println("   *Enter 2 if you want to update table to Reserved");
								continue;
								}
								
							}catch (NumberFormatException e) {
								System.out.println("Enter only Integer 1 or 2");
								System.out.println("UPDATE STATUS TO");
								System.out.println("   *Enter 1 if you want to update table to Available");
								System.out.println("   *Enter 2 if you want to update table to Reserved");
							    continue;
							    
							}
						}
						//
						if(updatetype==1) {//code is  check if update type is already what exists.
							boolean x=tableproduce.updateStatus(stableID,"Available");
							if(x==true) {
								System.out.println("UPDATE SUCCESSFULL :)");
								// retrieve updated table details
								List<Table> tablelist=tableproduce.findtablebyID(stableID);
								
								
								for(Table findTable:tablelist) {
									System.out.println("Table "+stableID+" Status Updated to "+ findTable.getStatus());
								}
								
							
							//
							}else {
								System.out.println("Not Updated");
								System.out.println("Table Status is already Available");
							}
							
							
							
						}else {
							boolean x=tableproduce.updateStatus(stableID,"Reserved");
							if(x==true) {
								System.out.println("UPDATE SUCCESSFULL :)");
								// retrieve updated table details
								List<Table> tablelist=tableproduce.findtablebyID(stableID);
								
								
								for(Table findTable:tablelist) {
									System.out.println("Table "+stableID+" Status Updated to "+ findTable.getStatus());
								}
								
							
							//
							}else {
								System.out.println("Not Updated");
								System.out.println("Table Status is already Reserved");
							}
							
						}
		
						
				
						System.out.println("\n");
						
						System.out.print("Press 0 to navigate back to home or press any other key to continue....");
						back = scanner.next();
						
						
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
						
					}else {
						System.out.println("***NO TABLE WITH "+stableID+" ID EXIST***");
						System.out.println("\n");
						System.out.print("DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					
					
					
				}break;
			case 3:
				System.out.println("\n");
				
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::Update Number of Seats in Table :::::::::::::::");
					
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n ID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("Table ID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					 
					int seatcount;
					
					if(tableproduce.checkTableAvailability(stableID)) {
						
						while(true) {
							System.out.print("\nEnter New Number of Seats for table of ID "+stableID+": ");
							String userInput=scanner.next();
							try {
								seatcount = Integer.parseInt(userInput);
								if(seatcount<1) {
									System.out.println("\n Seat Count cant be less than 1 ");
									continue;
								}
								break;
							}catch (NumberFormatException e) {
							    System.out.println("Seat Count can only be integer type Enter an Integer");
							    continue;
							    
							}
						}
						
						
						tableproduce.updateNoSeats(stableID,seatcount);
						System.out.println("Update Successfull :)");
						System.out.println("Seat Count of Table "+stableID+ " Changed to "+seatcount);
						

					
						System.out.print("\n Press 0 to navigate back to home or press any other key to continue....");
						back = scanner.next();
						
						
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
						
					}else {
						System.out.println("***NO TABLE WITH "+stableID+" ID EXIST***");
						System.out.print("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER: ");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					
					
					
				}break;
			case 4:
				System.out.println("\n");
				
				while(true) {
					System.out.println(":::::::::::::::Delete Table :::::::::::::::");
				
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n ID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("Table ID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					
					
					if(tableproduce.checkTableAvailability(stableID)) {
						
						tableproduce.removeTable(stableID);
						
						System.out.println("\n Table " +stableID+" removed");
					
						System.out.print("\n Press 0 to navigate back to home or press any other key to continue....");
						back = scanner.next();
						
						
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
						
					}else {
						System.out.println("***NO TABLE WITH ID "+stableID+" EXIST***");
						System.out.print("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					
				}break;
			case 5:
				System.out.println("\n");
				
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::FIND TABLE BY ID :::::::::::::::");
					//
					while(true) {//check if entered table id is valid
						System.out.print("Enter Table ID: ");
						String userInput=scanner.next();
						try {
							stableID = Integer.parseInt(userInput);
							if(stableID<1) {
								System.out.println("\n ID cant be less than 1 ");
								continue;
							}
							break;
						}catch (NumberFormatException e) {
						    System.out.println("Table ID can only be integer type Enter an Integer");
						    continue;
						    
						}
					}
					//
					
					//
					if(tableproduce.checkTableAvailability(stableID)) {
						
						List<Table> tablelist=tableproduce.findtablebyID(stableID);
						
						System.out.println("Details about table............."+stableID);
						System.out.println("-----------------------------------------------------------------------------");
						System.out.printf("%12s %20s %20s %15s", "Table ID", "Table Location", "Number Of Seats", "Status");
						
						
						for(Table findTable:tablelist) {
							/*System.out.println(" Table Location: "+ findTable.getLocation());
							System.out.println(" Number of Seats: "+ findTable.getNoSeats());
							System.out.println(" Status of table: "+ findTable.getStatus());*/
							System.out.print("\n");
							System.out.printf("%6s %20s %20s %20s",findTable.getTableID(), findTable.getLocation(), findTable.getNoSeats(), findTable.getStatus());
							
						}
						System.out.print("\n");
						System.out.println("-----------------------------------------------------------------------------");
						System.out.print("\n Press 0 to navigate back to home or press any other key to continue....");
						String back5 = scanner.next();
						
						if (back5.equals("0")) {
							break;
						} else {
							continue;
						}
						
					}else {
						System.out.println("***NO TABLE WITH ID "+stableID+" EXIST***");
						System.out.println("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
						back = scanner.next();
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}
					}
					//
					
					
				}break;
			case 6:
				System.out.println("\n");
				while(true) {
					System.out.println("\n");
					System.out.println(":::::::::::::::FIND TABLE BY Number of Seats :::::::::::::::");
					    
					    int Seatcount ;
						//
						while(true) {//check if entered seatcount is valid
							System.out.print("\nEnter Prefered Number of seats: ");
							String userInput=scanner.next();
							try {
								Seatcount = Integer.parseInt(userInput);
								if(Seatcount<1) {
									System.out.println("\n Seat Count cant be less than 1 ");
									continue;
								}
								break;
							}catch (NumberFormatException e) {
							    System.out.println("SeatCount can only be integer type Enter an Integer");
							    continue;
							    
							}
						}
					    
						List<Table> tablelist=tableproduce.findtablesbyNoSeats(Seatcount);
						
						if(tablelist.size()>0) {
							//
							System.out.println("\n-------------------List of Tables with entered seat count "+Seatcount+"------------------");
					        System.out.printf("%12s %20s %20s %15s", "Table ID", "Table Location", "Number Of Seats", "Status");
					        System.out.println();
					        System.out.println("-----------------------------------------------------------------------------");

					        int x=0;
					        int y=1;
					      if(tablelist.size()>0) {
					    	for(Table findTable:tablelist) {
							 
								System.out.print(y+"/");
					            System.out.printf("%6s %20s %20s %20s",findTable.getTableID(), findTable.getLocation(), findTable.getNoSeats(), findTable.getStatus());
					            System.out.print("\n");
					            y++;
					            x++;
								
							}
					        }
							System.out.println("\n");
							System.out.println("                                                    Total Number of Tables "+x);
							
							
							System.out.println("-----------------------------------------------------------------------------");
									
							//
							System.out.print("\n Press 0 to navigate back to home or press any other key to continue....");
							String back6 = scanner.next();
							
							if (back6.equals("0")) {
								break;
							} else {
								continue;
							}
							
						}else {
							System.out.println("***NO TABLE WITH SEAT COUNT "+Seatcount+" ***");
							System.out.print("\n PRESS 0 TO EXIT. TO CONTINUE PRESS ANY OTHER to continue same task");
							back = scanner.next();
							
							if (back.equals("0")) {
								break;
							} else {
								continue;
							}
							
						}
						
								
				}break;		
			case 7:
				System.out.println(":::::::::::::::Check Table Availability :::::::::::::::");
				while(true) {
						//
						while(true) {//check if entered table id is valid
							System.out.print("Enter Table ID: ");
							String userInput=scanner.next();
							try {
								stableID = Integer.parseInt(userInput);
								if(stableID<1) {
									System.out.println("\n ID cant be less than 1 ");
									continue;
								}
								break;
							}catch (NumberFormatException e) {
							    System.out.println("Table ID can only be integer type Enter an Integer");
							    continue;
							    
							}
						}
						//
					    
						List<Table> tablelist=tableproduce.findtablebyID(stableID);
						if(tablelist.size()>0) {
							for(Table findTable:tablelist) {
								System.out.println("Table "+findTable.getTableID()+" is: "+findTable.getStatus());
							}	
							
						}else {
							System.out.println("***NO TABLE WITH THAT ID EXIS***");
							System.out.print("\n DO YOU WANT TO EXIT PRESS 0 TO CONTINUE PRESS ANY OTHER");
							back = scanner.next();
							if (back.equals("0")) {
								break;
							} else {
								continue;
							}
							
						}
						
						System.out.print("\n Press 0 to navigate back to home or press any other key to continue....");
						back = scanner.next();
						
						if (back.equals("0")) {
							break;
						} else {
							continue;
						}					
				}break;
			case 8:
				List<Table> tablelist=tableproduce.tableList();
				//
				System.out.println("\n----------------------List of Tables & Details-------------------------------");
		        System.out.printf("%12s %20s %20s %15s", "Table ID", "Table Location", "Number Of Seats", "Status");
		        System.out.println();
		        System.out.println("-----------------------------------------------------------------------------");

		        int x=0;
		        int y=1;
		        
				for (Table table : tablelist) {
					
					System.out.print(y+"/");
		            System.out.printf("%6s %20s %20s %20s",table.getTableID(), table.getLocation(), table.getNoSeats(), table.getStatus());
		            System.out.print("\n");
		            y++;
		            x++;
					
				}
				System.out.println("\n");
				System.out.println("                                                    Total Number of Tables "+x);
				
				
				System.out.println("-----------------------------------------------------------------------------");
						
				//
				System.out.print("\n Press any key back to home....");
				back = scanner.next();
				break;
			
			case 9:
				System.out.println("-- Exit --");
				return;
				
			default:
				System.out.println("\nPlease enter valid option");
				continue;
				
			}
		}
	}

	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("............Table Manager Closed............");
		bundleContext.ungetService(tablemanagerReference);
	}

}
