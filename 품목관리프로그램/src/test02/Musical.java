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
		return id + "  "+ title+"  뮤지컬    "+ date + "  "+contractPrice+"  "+ticketPrice+"  "+numOfSeat+"  주연명 : "+actor+"   참여 배우 수 : "+numActor;
	}
}
