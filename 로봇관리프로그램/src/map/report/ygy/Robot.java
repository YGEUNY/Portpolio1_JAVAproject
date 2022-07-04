package map.report.ygy;

public abstract class Robot {

	protected int id, price, distance, move, num;
	protected String name;
	protected String rKind;
	protected int x, y;
	
	
	
	public String getrKind() {
		return rKind;
	}
	public void setrKind(String rKind) {
		this.rKind = rKind;
	}
	public int getMove() {
		return move;
	}
	public void setMove(int move) {
		this.move = move;
	}
	
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract int move(int direction);
}
