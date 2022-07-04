package report.ygy;


public class Refrigerator extends Product {

	

	private int liter;
	private String type;
	
	

	public int getLiter() {
		return liter;
	}

	public void setLiter(int liter) {
		this.liter = liter;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
	
		return id + "  " +model + "  " + company + "  " + date + "  " + numOfStock + "  "+ price + "  "+ liter +"∏Æ≈Õ  "+ type;
	}

}