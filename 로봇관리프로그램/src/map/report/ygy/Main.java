package map.report.ygy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.TreeMap;


class nameCompare implements Comparator<Robot>{
	public int compare(Robot r1, Robot r2) {
		return r1.getName().compareTo(r2.getName());
	}
}

class priceCompare implements Comparator<Robot>{
	public int compare(Robot r1, Robot r2) {
		if(r1.getPrice()<r2.getPrice()) return -1;
		else if(r1.getPrice()>r2.getPrice()) return 1;
		else return 0;
	}
}

class idCompare implements Comparator<Robot>{
	public int compare(Robot r1, Robot r2) {
		if(r1.getId()<r2.getId()) return -1;
		else if(r1.getId()>r2.getId()) return 1;
		else return 0;
	}
}

class kindCompare implements Comparator<Robot>{
	public int compare(Robot r1, Robot r2) {
		return r1.getrKind().compareTo(r2.getrKind());
	}
}


public class Main {

	ArrayList<Robot> list = new ArrayList<Robot>();
	Scanner sc= new Scanner(System.in);
	HashMap<String, Robot> rnameMap = new HashMap<String, Robot>();
	TreeMap<Integer, Robot> ridMap = new TreeMap<Integer, Robot>();
	Robot robot = null;
	int count=0;
	int numCount=1;
	
	public static void main(String[] args) {
		Main m = new Main();
		m.menu();
	}
	
	public void menu() {
		while(true) {
		System.out.println("==============================");
		System.out.println("1. �κ� ������ �Է�\n2. �κ� ����Ʈ ����\n3. ���� Robot �̵�\n4. �κ� ����\n5. �κ� ���� ������ �˻�\n6. �κ� �˻�\n7. ����");
		System.out.println("==============================");
		System.out.print("�Է�>>");
		
		int choice;
		
		choice = numException();
		if(choice==1) inputData();
		else if(choice==2) printList();
		else if(choice==3) moveRobot();
		else if(choice==4) deletData();
		else if(choice==5) searchPrice();
		else if(choice==6) searchRobot();
		else if(choice==7) break;
		else	System.out.println("1~7 ������ ��ȣ�� �Է��� �ּ���.");
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	
	public void inputData() {
		System.out.println("����� ���� �����ּ���.");
		String kind = null;
		while(true) {
			System.out.print("��ǰ ������?(CleaningRobot : c, DogRobot : d)>>");
			kind = sc.next();
			if(!kind.equals("c") && !kind.equals("C") && !kind.equals("d") && !kind.equals("D"))
				System.out.println("��Ȯ�� ��ǰ ������ �Է��� �ּ���.");
			else break;
		}
		if(kind.equals("c") || kind.equals("C")) {
			inputCleaning();
			robot.setrKind("CleaningRobot");
		}
		else if(kind.equals("d") || kind.equals("D")) {
			inputDog();
			robot.setrKind("DogRobot");
		}
		
		
		list.add(robot);
		rnameMap.put(robot.getName(),robot);
		ridMap.put(robot.getId(),robot);
		count++;
		numCount++;
	}
	
	public void inputCleaning() {
		
		robot = new CleaningRobot();
		inputSame();
		System.out.print("cleaningPower �Է� : ");
		((CleaningRobot)robot).setCleaningPower(numException());		
	}
	
	public void inputDog() {
		robot = new DogRobot();
		inputSame();
		System.out.print("barkPower �Է�: ");
		((DogRobot)robot).setBarkPower(numException());
	}
	
	public void inputSame() {	
		robot.setNum(numCount);
		robot.setId(sameId());
		System.out.print("�κ��� �Է� : ");
		robot.setName(sc.next());
		System.out.print("���� �Է� : ");
		robot.setPrice(numException());
	}

	
	public int sameId() {
		int inputId=0;
		while(true) {
			System.out.print("id �Է� : ");
			inputId=numException();
			
			Robot r = ridMap.get(inputId);
			if(r==null)	return inputId;
			else System.out.println("�ߺ��� id�Դϴ�.");
		}	
	}
	
	public void printList() {
		int choice;
		System.out.println("1) �κ���� ����   2) ���ݼ� ����  3) id�� ����");
		System.out.print("�Է� : ");
		choice=numException();
		
		nameCompare nameSort = new nameCompare();
		priceCompare priceSort = new priceCompare();
		idCompare idSort = new idCompare();
		
		if(choice==1)	Collections.sort(list,nameSort);
		else if(choice==2) Collections.sort(list,priceSort);
		else if(choice==3)	Collections.sort(list, idSort);
		else	System.out.println("�������� �ʴ� �����Դϴ�.");
		
		System.out.println("< ���� �κ� "+count+"�� >");
		System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			System.out.println(r.toString());
		}
	}
	
	public void moveRobot() {
		kindCompare kindSort = new kindCompare();
		Collections.sort(list,kindSort);
		System.out.println("< ���� �κ� "+count+"�� >");
		System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		for(Robot r:list) {
			System.out.println(r.toString());
		}
		int choiceId=0, choice=0;
		
		while(true) {
			System.out.print("�κ� id ���� : ");
			choiceId = numException();
			
			Robot r = ridMap.get(choiceId);
			if(r==null)	System.out.println("���� id�Դϴ�.");
			else break;
		}
		
		System.out.print("�̵�(��:1, ��:2, ��:3, ��:4): ");
		choice = numException();
		
		for(Robot r:list) {
			if(r.getId()==choiceId) {
				if(r.getrKind().equals("CleaningRobot")) {
					System.out.print(r.getrKind()+"  " +r.getName()+"�� "+r.getX()+"  "+r.getY()+"  ��ġ����   " );
					((CleaningRobot)r).move(choice);
					System.out.println(r.getX() +"  "+r.getY()+"  �� �̵��Ͽ����ϴ�.");
				}
				else if(r.getrKind().equals("DogRobot")) {
					System.out.print(r.getrKind()+"  " +r.getName()+"�� "+r.getX()+"  "+r.getY()+"  ��ġ����   ");
					((DogRobot)r).move(choice);
					System.out.println(r.getX() +"  "+r.getY()+"  �� �̵��Ͽ����ϴ�.");
				}
			}
		}
		
		
		

	}
	
	public void deletData() {
		idCompare idSort = new idCompare();
		Collections.sort(list,idSort);
		System.out.println("< ���� �κ� "+count+"�� >");
		System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			System.out.println(r.toString());
		}
		
		System.out.print("������ robot�� id��?");
		int choice = numException();
//		for(Robot r:list) {
//			if(r.getId()==choice) {
//				list.remove(r);
//				rnameMap.remove(r.getName());
//				ridMap.remove(r.getId());
//				break;
//			}
//		}
		
		Robot r = ridMap.get(choice);
		if(r!=null) {
			list.remove(r);
			rnameMap.remove(r.getName());
			ridMap.remove(r.getId());
			System.out.println("id "+choice+"�� �κ��� �����Ͽ����ϴ�.");
		}
		else System.out.println("���� id�Դϴ�.");
		
	}
	
	public void searchPrice() {
		
		int min=0, max=0;
		System.out.println("���� ���� �Է�(min~max)");
		System.out.print("min �Է� : ");
		min=numException();
		System.out.print("max �Է� : ");
		max=numException();
		
		System.out.println("< ���� �κ� "+count+"�� >");
		System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			if(min<=r.price && max>=r.price) {
				 System.out.println(r.toString());
			}
		}	
	}
	
	public void searchRobot() {
		System.out.println("1) �̸����� �˻�          2) id�� �˻�");
		System.out.print("�Է�: ");
		int choice=numException();
		if(choice==1) {
			System.out.print("�˻��� �̸� �Է� : ");
			String sName=sc.next();
			Robot r = rnameMap.get(sName);
			if(r==null) System.out.println("�������� �ʴ� �κ��Դϴ�.");
			else {
				System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
				System.out.println("----------------------------------------------------------------");
				System.out.println(rnameMap.get(sName));
			}
		}
		else if(choice==2) {
			System.out.print("�˻��� id �Է�: ");
			int sId =numException();
			Robot r = ridMap.get(sId);
			if(r==null) System.out.println("�������� �ʴ� �κ��Դϴ�.");
			else {
				System.out.println("��ȣ  Robot id     ����                  Robot��      x     y    price  distance  etc");
				System.out.println("----------------------------------------------------------------");
				System.out.println(ridMap.get(sId));
			}
		}
		
	}	
	
	public int numException() {
		int number;
		while(true) {
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.print("�ùٸ� ���ڸ� �Է��ϼ���>>");
			}
		}
		return number;
	}
}