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
		System.out.println("1. 로봇 데이터 입력\n2. 로봇 리스트 보기\n3. 개별 Robot 이동\n4. 로봇 삭제\n5. 로봇 가격 범위로 검색\n6. 로봇 검색\n7. 종료");
		System.out.println("==============================");
		System.out.print("입력>>");
		
		int choice;
		
		choice = numException();
		if(choice==1) inputData();
		else if(choice==2) printList();
		else if(choice==3) moveRobot();
		else if(choice==4) deletData();
		else if(choice==5) searchPrice();
		else if(choice==6) searchRobot();
		else if(choice==7) break;
		else	System.out.println("1~7 사이의 번호를 입력해 주세요.");
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	
	public void inputData() {
		System.out.println("띄어쓰기는 하지 말아주세요.");
		String kind = null;
		while(true) {
			System.out.print("제품 종류는?(CleaningRobot : c, DogRobot : d)>>");
			kind = sc.next();
			if(!kind.equals("c") && !kind.equals("C") && !kind.equals("d") && !kind.equals("D"))
				System.out.println("정확한 제품 종류를 입력해 주세요.");
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
		System.out.print("cleaningPower 입력 : ");
		((CleaningRobot)robot).setCleaningPower(numException());		
	}
	
	public void inputDog() {
		robot = new DogRobot();
		inputSame();
		System.out.print("barkPower 입력: ");
		((DogRobot)robot).setBarkPower(numException());
	}
	
	public void inputSame() {	
		robot.setNum(numCount);
		robot.setId(sameId());
		System.out.print("로봇명 입력 : ");
		robot.setName(sc.next());
		System.out.print("가격 입력 : ");
		robot.setPrice(numException());
	}

	
	public int sameId() {
		int inputId=0;
		while(true) {
			System.out.print("id 입력 : ");
			inputId=numException();
			
			Robot r = ridMap.get(inputId);
			if(r==null)	return inputId;
			else System.out.println("중복된 id입니다.");
		}	
	}
	
	public void printList() {
		int choice;
		System.out.println("1) 로봇명순 정렬   2) 가격순 정렬  3) id순 정렬");
		System.out.print("입력 : ");
		choice=numException();
		
		nameCompare nameSort = new nameCompare();
		priceCompare priceSort = new priceCompare();
		idCompare idSort = new idCompare();
		
		if(choice==1)	Collections.sort(list,nameSort);
		else if(choice==2) Collections.sort(list,priceSort);
		else if(choice==3)	Collections.sort(list, idSort);
		else	System.out.println("존재하지 않는 보기입니다.");
		
		System.out.println("< 현재 로봇 "+count+"대 >");
		System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			System.out.println(r.toString());
		}
	}
	
	public void moveRobot() {
		kindCompare kindSort = new kindCompare();
		Collections.sort(list,kindSort);
		System.out.println("< 현재 로봇 "+count+"대 >");
		System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		for(Robot r:list) {
			System.out.println(r.toString());
		}
		int choiceId=0, choice=0;
		
		while(true) {
			System.out.print("로봇 id 선택 : ");
			choiceId = numException();
			
			Robot r = ridMap.get(choiceId);
			if(r==null)	System.out.println("없는 id입니다.");
			else break;
		}
		
		System.out.print("이동(상:1, 하:2, 좌:3, 우:4): ");
		choice = numException();
		
		for(Robot r:list) {
			if(r.getId()==choiceId) {
				if(r.getrKind().equals("CleaningRobot")) {
					System.out.print(r.getrKind()+"  " +r.getName()+"이 "+r.getX()+"  "+r.getY()+"  위치에서   " );
					((CleaningRobot)r).move(choice);
					System.out.println(r.getX() +"  "+r.getY()+"  로 이동하였습니다.");
				}
				else if(r.getrKind().equals("DogRobot")) {
					System.out.print(r.getrKind()+"  " +r.getName()+"이 "+r.getX()+"  "+r.getY()+"  위치에서   ");
					((DogRobot)r).move(choice);
					System.out.println(r.getX() +"  "+r.getY()+"  로 이동하였습니다.");
				}
			}
		}
		
		
		

	}
	
	public void deletData() {
		idCompare idSort = new idCompare();
		Collections.sort(list,idSort);
		System.out.println("< 현재 로봇 "+count+"대 >");
		System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			System.out.println(r.toString());
		}
		
		System.out.print("삭제할 robot의 id는?");
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
			System.out.println("id "+choice+"번 로봇을 삭제하였습니다.");
		}
		else System.out.println("없는 id입니다.");
		
	}
	
	public void searchPrice() {
		
		int min=0, max=0;
		System.out.println("가격 범위 입력(min~max)");
		System.out.print("min 입력 : ");
		min=numException();
		System.out.print("max 입력 : ");
		max=numException();
		
		System.out.println("< 현재 로봇 "+count+"대 >");
		System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
		System.out.println("----------------------------------------------------------------");
		
		for(Robot r:list) {
			if(min<=r.price && max>=r.price) {
				 System.out.println(r.toString());
			}
		}	
	}
	
	public void searchRobot() {
		System.out.println("1) 이름으로 검색          2) id로 검색");
		System.out.print("입력: ");
		int choice=numException();
		if(choice==1) {
			System.out.print("검색할 이름 입력 : ");
			String sName=sc.next();
			Robot r = rnameMap.get(sName);
			if(r==null) System.out.println("존재하지 않는 로봇입니다.");
			else {
				System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
				System.out.println("----------------------------------------------------------------");
				System.out.println(rnameMap.get(sName));
			}
		}
		else if(choice==2) {
			System.out.print("검색할 id 입력: ");
			int sId =numException();
			Robot r = ridMap.get(sId);
			if(r==null) System.out.println("존재하지 않는 로봇입니다.");
			else {
				System.out.println("번호  Robot id     종류                  Robot명      x     y    price  distance  etc");
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
				System.out.print("올바를 숫자를 입력하세요>>");
			}
		}
		return number;
	}
}