package tablemanagerproducer;

public class Table {
	private int NoSeats;
	private String location;
	private int tableID;
    private String Status;
    
    public Table(int NoSeats,String location,int tableID,String Status ) {
    	super();
    	this.NoSeats=NoSeats;
    	this.location=location;
    	this.tableID=tableID;
    	this.Status=Status;
    }

	public int getNoSeats() {
		return NoSeats;
	}

	public void setNoSeats(int noSeats) {
		NoSeats = noSeats;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getTableID() {
		return tableID;
	}

	public void setTableID(int tableID) {
		this.tableID = tableID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
    
	

}
