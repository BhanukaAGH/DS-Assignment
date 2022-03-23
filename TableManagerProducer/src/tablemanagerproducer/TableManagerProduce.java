package tablemanagerproducer;

import java.util.List;
public interface TableManagerProduce {
	public void addTable(int NoSeats, String location, int tableID, String Status);//status reserved/available
	public boolean updateStatus(int tableID, String Status);//update availability of table
	public void updateNoSeats(int tableID, int NoSeats );//update number of seats of relavant table ID
	public void removeTable(int tableID);//remove tabel
	public List<Table> findtablebyID(int tableID);//find table by entering table ID
	public List<Table> findtablesbyNoSeats(int NoSeats);//find table by entering table ID
	public boolean checkTableAvailability(int tableID );//check availabilty of table
	public List<Table> tableList();
	
	
	
	
	
	

}
