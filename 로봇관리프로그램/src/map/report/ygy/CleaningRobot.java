package map.report.ygy;

public class CleaningRobot extends Robot{
	
	private int cleaningPower;

	public int getCleaningPower() {
		return cleaningPower;
	}

	public void setCleaningPower(int cleaningPower) {
		this.cleaningPower = cleaningPower;
	}
	

	@Override
	public String toString() {
		return num +"     "+id+"     CleaningRobot    "+name+"   "+x+"   "+y+"    "+price+"  "+distance+"  cleaningPower = "+cleaningPower;
	}
	
	public void cleaningStart(){
		System.out.println("ClaningPower "+cleaningPower+"으로 청소를 시작했습니다.");
	}
	
	public void cleaningStop() {
		System.out.println("ClaningPower "+cleaningPower+"으로 청소를 멈추었습니다.");
	}
	
	public CleaningRobot() {
		this.cleaningPower=10;
		this.x=(int)(Math.random()*200+1);
		this.y=(int)(Math.random()*200+1);
		this.distance=3;
	}
	
	@Override
	public int move(int direction) {
		// 1.상 2.하 3.좌 4.우
		if(direction==1) {
			if(this.getY()-this.distance>=0)
				this.setY(this.getY()-this.distance);
			else System.out.println("이동 불가.(범위 0~200 초과)");
		}
		else if(direction==2) {
			if(this.getY()+this.distance<=200)
				this.setY(this.getY()+this.distance);
			else System.out.println("이동 불가.(범위 0~200 초과)");
		}
		else if(direction==3) {
			if(this.getX()-this.distance>=0)
				this.setX(this.getX()-this.distance);
			else System.out.println("이동 불가.(범위 0~200 초과)");
		}
		else if(direction==4) {
			if(this.getX()+this.distance<=200)
				this.setX(this.getX()+this.distance);
			else System.out.println("이동 불가.(범위 0~200 초과)");
		}
		else	System.out.println("올바른 방향을 입력하세요.");
		return direction;
	}
	
}
