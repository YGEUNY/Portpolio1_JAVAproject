package test02;

public class Movie extends Event{

	private String director;
	private String actor;
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	
	@Override
	public String toString() {
		return id + "  "+ title+"  영화     "+ date + "  "+contractPrice+"  "+ticketPrice+"  "+numOfSeat+"  감독 : "+director+"   출연 배우 : "+actor;
	}
}
