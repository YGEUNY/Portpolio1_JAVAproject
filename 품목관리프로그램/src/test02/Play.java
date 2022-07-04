package test02;

public class Play extends Event {
	
	
	private String playActor;
	private String stageManager;
	
	
	
	
	public String getPlayActor() {
		return playActor;
	}




	public void setPlayActor(String playActor) {
		this.playActor = playActor;
	}




	public String getStageManager() {
		return stageManager;
	}




	public void setStageManager(String stageManager) {
		this.stageManager = stageManager;
	}




	@Override
	public String toString() {
		return id + "  "+ title+"  연극     "+ date + "  "+contractPrice+"  "+ticketPrice+"  "+numOfSeat+"  무대감독 : "+stageManager+"   연극배우명 : "+playActor;
	}
	
}