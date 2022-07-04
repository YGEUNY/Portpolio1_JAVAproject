package report.ygy;


public class Audio extends Product{
	
	
	
	private int outPut;
	private String tuner;
	
	public int getOutPut() {
		return outPut;
	}

	public void setOutPut(int outPut) {
		this.outPut = outPut;
	}

	public String getTuner() {
		return tuner;
	}

	public void setTuner(String tuner) {
		this.tuner = tuner;
	}

	@Override
	public String toString() {
	
		return id + "  " +model + "  " + company + "  " + date + "  " + numOfStock + "  "+ price + "  "+ outPut +"W  튜너지원여부 : "+tuner;
	}
	
	

	
}
