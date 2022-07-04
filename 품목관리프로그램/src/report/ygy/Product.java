package report.ygy;

public abstract class Product implements Comparable<Product>{

	protected String model;
	protected String company;
	protected int date;
	protected int numOfStock;
	protected int price;
	private int id=0;
	
	
	@Override
	public int compareTo(Product p) {
		return ((Integer)this.date).compareTo((Integer)(p.getDate()));
	}
	
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getNumOfStock() {
		return numOfStock;
	}
	public void setNumOfStock(int numOfStock) {
		this.numOfStock = numOfStock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}		
	
	@Override
	public abstract String toString();
}