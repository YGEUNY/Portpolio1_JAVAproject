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
		return id + "  "+ title+"  ����     "+ date + "  "+contractPrice+"  "+ticketPrice+"  "+numOfSeat+"  ���밨�� : "+stageManager+"   ���ع��� : "+playActor;
	}
	
}