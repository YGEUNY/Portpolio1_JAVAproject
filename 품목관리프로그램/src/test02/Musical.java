package test02;

public class Musical extends Event {

	
	private String actor;
	private int numActor;
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public int getNumActor() {
		return numActor;
	}
	public void setNumActor(int numActor) {
		this.numActor = numActor;
	}
	
	
	@Override
	public String toString() {
		return id + "  "+ title+"  ������    "+ date + "  "+contractPrice+"  "+ticketPrice+"  "+numOfSeat+"  �ֿ��� : "+actor+"   ���� ��� �� : "+numActor;
	}
}
