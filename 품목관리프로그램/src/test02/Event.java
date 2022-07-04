package test02;

public abstract class Event{
	
	protected String title;
	protected int date;
	protected int ticketPrice;
	protected int numOfSeat;
	protected int contractPrice;
	protected int id=0;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getTicketPrice() {
		return ticketPrice;
	}
	public void setTicketPrice(int ticketPrice) {
		this.ticketPrice = ticketPrice;
	}
	public int getNumOfSeat() {
		return numOfSeat;
	}
	public void setNumOfSeat(int numOfSeat) {
		this.numOfSeat = numOfSeat;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getContractPrice() {
		return contractPrice;
	}


	public void setContractPrice(int contractPrice) {
		this.contractPrice = contractPrice;
	}
}
