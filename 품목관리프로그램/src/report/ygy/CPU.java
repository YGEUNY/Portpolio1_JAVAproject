package report.ygy;



public class CPU extends Product {
	private double speed;
	private int inch;
	
	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getInch() {
		return inch;
	}

	public void setInch(int inch) {
		this.inch = inch;
	}
	@Override
	public String toString() {
	
		return getId() + "  " +model + "  " + company + "  " + date + "  " + numOfStock + "  "+ price + "  "+ speed +"GHz  "+ inch +"ÀÎÄ¡";
	}

}
