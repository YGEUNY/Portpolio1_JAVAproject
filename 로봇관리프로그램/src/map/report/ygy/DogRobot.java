package map.report.ygy;

public class DogRobot extends Robot{

	private int barkPower;

	public int getBarkPower() {
		return barkPower;
	}

	public void setBarkPower(int barkPower) {
		this.barkPower = barkPower;
	}
	
	@Override	
	public String toString() {
		return num +"     "+id+"       DogRobot      "+name+"    "+x+"   "+y+"    "+price+"  "+distance+"  barkPower = "+barkPower;
	}
	
	public void cleaningStart(){
		System.out.println("BarkPower "+barkPower+"���� û�Ҹ� �����߽��ϴ�.");
	}
	
	public void cleaningStop() {
		System.out.println("BarkPower "+barkPower+"���� û�Ҹ� ���߾����ϴ�.");
	}
	
	public DogRobot(){
		this.barkPower=3;
		this.distance=10;
		this.x=(int)(Math.random()*200+1);
		this.y=(int)(Math.random()*200+1);
	}
	
	
	@Override
	public int move(int direction) {
		// 1.�� 2.�� 3.�� 4.��
		if(direction==1) {
			if(this.getY()-this.distance>=0)
				this.setY(this.getY()-this.distance);
			else System.out.println("�̵� �Ұ�.(���� 0~200 �ʰ�)");
		}
		else if(direction==2) {
			if(this.getY()+this.distance<=200)
				this.setY(this.getY()+this.distance);
			else System.out.println("�̵� �Ұ�.(���� 0~200 �ʰ�)");
		}
		else if(direction==3) {
			if(this.getX()-this.distance>=0)
				this.setX(this.getX()-this.distance);
			else System.out.println("�̵� �Ұ�.(���� 0~200 �ʰ�)");
		}
		else if(direction==4) {
			if(this.getX()+this.distance<=200)
				this.setX(this.getX()+this.distance);
			else System.out.println("�̵� �Ұ�.(���� 0~200 �ʰ�)");
		}
		else	System.out.println("�ùٸ� ������ �Է��ϼ���.");
		return direction;
	}
	
}
