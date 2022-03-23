package tablemanagerproducer;

import java.util.ArrayList;
import java.util.List;

public class TableManagerProducerImpl implements TableManagerProduce {

	@Override
	public void addTable(int NoSeats, String location, int tableID, String Status) {
		
		Table newTable = new Table(NoSeats, location, tableID, Status);//add new table
		TableData.tablelist.add(newTable);	
	}

	@Override
	public boolean updateStatus(int tableID, String Status) {//update table availability
		for(Table table: TableData.tablelist) {
			if(table.getStatus()==Status) {
				return false;
			}
			if(table.getTableID()==tableID) {
				table.setStatus(Status);
				break;
			}
		}
		return true;
		
	}

	@Override
	public void updateNoSeats(int tableID, int NoSeats) {//update number of seats in table
		for(Table table: TableData.tablelist) {
			if(table.getTableID()==tableID) {
				table.setNoSeats(NoSeats);
				break;
			}
		}		
		
	}

	@Override
	public void removeTable(int tableID) {//remove table
		int tableindex=0;
		for(Table table: TableData.tablelist) {//search for table
			if(table.getTableID()==tableID) {
				TableData.tablelist.remove(tableindex);
				break;
			}
			tableindex++;//increment idex untill table found
		}	
		
		
	}

	@Override
	public List<Table> findtablebyID(int tableID) {
		List<Table> tablelists=new ArrayList<>();
		for(Table table:TableData.tablelist) {
			if(table.getTableID()==tableID) {
				tablelists.add(table);
			}
		}
		return tablelists;
	}

	@Override
	public List<Table> findtablesbyNoSeats(int NoSeats) {
		List<Table> tablelists=new ArrayList<>();
		for(Table table:TableData.tablelist) {
			if(table.getNoSeats()==NoSeats) {
				tablelists.add(table);
			}
		}
		return tablelists;
	}

	@Override
	public boolean checkTableAvailability(int tableID) {//check if table is awaialbale
		for(Table table:TableData.tablelist) {
			if(table.getTableID()==tableID) {
				return true;
			}
		}
		return false;
	}
	//write method to check status of table

	@Override
	public List<Table> tableList() {//list all table details
		return TableData.tablelist;
	}}
	
