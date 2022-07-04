package arraylist.report.ygy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


class modelCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {	
		return p1.getModel().compareTo(p2.getModel());
	}	
}

class modelReverseCompare implements Comparator<Product>{

	public int compare(Product p1, Product p2) {	
		return p2.getModel().compareTo(p1.getModel());
	}
}

class priceCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()<p2.getPrice())	return -1;
		else if(p1.getPrice()>p2.getPrice())	return 1;
		else	return 0;
	}
}

class priceReverseCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()>p2.getPrice())	return -1;
		else if(p1.getPrice()<p2.getPrice())	return 1;
		else	return 0;
	}
}

class stockCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getNumOfStock()<p2.getNumOfStock())	return -1;
		else if(p1.getNumOfStock()>p2.getNumOfStock())	return 1;
		else return 0;
	}
}

class dateCompare implements Comparator<Product>{
		public int compare(Product o1, Product o2) {
		return o1.getDate()-o2.getDate();
	}
}

public class Main {
	ArrayList<Product> list = new ArrayList<Product>();
	Scanner sc = new Scanner(System.in);
	int num =0;
	int idNum=0;
	Product product = null;

	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void menu() {
		System.out.println("==================================");
		System.out.println("1. 제품 데이터입력\n2. 제품리스트 보기\n3. 개별 제품 재고수량 조정\n4. 모든 제품 가격수량 조절\n5. 가격 범위로 검색(가격순)\n6. 종료");
		System.out.println("==================================");
		System.out.print("입력 >>");
	}
	
	public int numException() {
		int number;
		while(true) {
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("다시 입력하세요>>");
			}
		}
		return number;
	}


	public void run() {
		int choice=0;
		while(true) {
			menu();
			choice = numException();
			if(choice ==1) {
				if(num<10)	inputData();
				else System.out.println("입력은 최대10개 까지 할 수 있습니다.");
			}
			else if(choice ==2)	printList();
			else if(choice ==3) changeOfStock();
			else if(choice ==4)	changeAllOfStock();
			else if(choice ==5)	searchList();
			else if(choice ==6)	break;
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
		
		
	public void inputData() {
		System.out.println("띄어쓰기는 하지 말아주세요.");
		String kind = null;
		while(true) {
			System.out.println("제품종류는? [c: CPU , r: Refrigerator , a: Audio]");
			kind = sc.next();
			if(!kind.equals("c") &&!kind.equals("C") &&!kind.equals("r")&&!kind.equals("R")&&!kind.equals("a")&&!kind.equals("A"))
				System.out.println("c, r, a 중에서 입력하세요>>");
			else break;
		}
		
		if(kind.equals("c") ||kind.equals("C"))	inputCPU();
		else if(kind.equals("r") ||kind.equals("R"))	inputRefrigerator();
		else if(kind.equals("a") ||kind.equals("A"))	inputAudio();
		list.add(product);
		num++;
		idNum++;
	}
	
	public void inputCPU() {
		product = new CPU();
		same();
		System.out.print("속도 입력>>");
		double inputSpeed=0;
		while(true) {
			try {
				inputSpeed = sc.nextDouble();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("올바른 숫자 입력>>");
			}
			
		}
		((CPU)product).setSpeed(inputSpeed);
		System.out.print("핀수 입력>>");
		((CPU)product).setInch(numException());
	}
	
	public void inputRefrigerator() {
		product = new Refrigerator();
		same();
		System.out.print("용량 입력>>");
		((Refrigerator)product).setLiter(numException());
		System.out.print("타입 입력>>");
		((Refrigerator)product).setType(sc.next());
	}
	
	public void inputAudio() {
		product = new Audio();
		same();
		System.out.print("앰프출력 입력>>");
		((Audio)product).setOutPut(numException());
		System.out.print("튜너지원여부 입력( 1.지원 2.지원x )>>");
		((Audio)product).setTuner(inputTuner());
	}
	
	public void same() {
		product.setId(idNum+1);
		System.out.print("제품명 입력>>");
		product.setModel(sc.next());
		System.out.print("제조사 입력>>");
		product.setCompany(sc.next());
		System.out.print("입고날짜 입력( 예 : 20170401)>>");
		product.setDate(inputDate());
		System.out.print("재고수량 입력>>");
		product.setNumOfStock(numException());
		System.out.print("가격 입력>>");
		product.setPrice(numException());
	}
	
	
	
	public int inputDate() {
		int mdate=0;
		while(true) {
			mdate = numException();
			if(((int)Math.log10(mdate)+1) != 8) {
				System.out.println("올바른 자리수를 입력하세요.");
				System.out.print("입고날짜 입력>>( 예 : 20170401)");
			}
			else {
				if(mdate%10000/100 < 1 || mdate%10000/100 > 12) {
					System.out.println("올바른 개월을 입력하세요.");
					System.out.print("입고날짜 입력>>( 예 : 20170401)");
				}
				else {
					if(mdate%100 < 1 || mdate%100>31) {
						System.out.println("올바른 날짜를 입력하세요.");
						System.out.print("입고날짜 입력>>( 예 : 20170401)");
					}
					else break;
				}
			}
		}
		return mdate;
	}
	
	
	public String inputTuner() {
		System.out.println();
		String str = null;
		 int choose=0;
		 while(true) {
			 choose = numException();
			 if(choose !=1 && choose !=2)	System.out.print("1과 2 중에서 입력>>");
			 else break;
		 }
		 if(choose == 1)	str="지원";
		 else if(choose ==2) str ="지원x";
		 return str;
	}
	
	
	public void printList() {
		int choice=0;
		while(true) {
			System.out.println("(1) 날짜순(기본)   (2) 제품명       (3) 제품명 역순       (4) 가격순     (5)가격역순");
			System.out.print("선택 :  ");
			choice = numException();
			if(choice!=1 &&choice!=2 && choice!=3 && choice!=4 && choice!=5)	
				System.out.println("1~5 중에서 입력하세요.");
			else break;			
		}
		choiceList(choice);
	}

	public void choiceList(int choice) {
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		dateCompare dateSort = new dateCompare();
		modelCompare modelSort = new modelCompare();
		modelReverseCompare modelReverseSort = new modelReverseCompare();
		priceCompare priceSort = new priceCompare();
		priceReverseCompare priceReverseSort = new priceReverseCompare();
		if(choice==1)	Collections.sort(list,dateSort);
		else if(choice==2)	Collections.sort(list,modelSort);
		else if(choice==3)	Collections.sort(list,modelReverseSort);
		else if(choice==4)	Collections.sort(list,priceSort);
		else if(choice==5)	Collections.sort(list,priceReverseSort);

		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		for(Product p : list) {
			System.out.println(p.toString());
		}
	}
	
	public void stockList() {
		stockCompare stockSort = new stockCompare();
		Collections.sort(list,stockSort);
		 System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public void changeOfStock() {
		stockList();
		int changeStock;
		while(true) {
			System.out.print("제품번호 선택>>");
			changeStock = numException();
			if(changeStock<1 || changeStock >num+1)	System.out.println("존재하지 않는 리스트입니다.");
			else break;
		}
		for(Product p:list)
		{
			if(p.getId()==changeStock)
			{
				System.out.print("현재 재고수는 " +p.numOfStock+" 입니다. 새로운 재고수는? ");
				p.setNumOfStock(numException());
			}
		}
		
//		for(int i=0;i<num;i++) {
//			if(changeStock == product.getId()) {
//				System.out.print("현재 재고수는 " +product.numOfStock+" 입니다. 새로운 재고수는? ");
//				product.setNumOfStock(numException());
//			}
//			else ;
//		}
	}
	
	public void changeAllOfStock() {
		int choose =0;
		while(true) {
			System.out.println(" 1.제품 가격 일괄 올림  2.제품 가격 일괄 내림  3.취소"); 
			System.out.print("선택 : ");
			choose = numException();
			if(choose==1)	 changeAllPrice_one();
			else if(choose==2)	 changeAllPrice_two();
			else if(choose==3) 	break;
		}
		
	}
	int changeAllPrice =0;
	public void changeAllPrice_one() {
		System.out.println("일괄 올릴 가격 수량은 ?");
		changeAllPrice = numException();
		for(Product p:list) {
			p.setPrice(p.price+changeAllPrice);
		}
	}
	
	public void changeAllPrice_two() {
		System.out.println("일괄 내릴 가격 수량은 ?");
		changeAllPrice = numException();
		for(Product p:list) {
			if(p.price<=changeAllPrice)	p.setPrice(0);
			else	p.setPrice(p.price-changeAllPrice);
		}
	}
	
	public void searchList() {
		int start=0, end=0;
		 System.out.println("검색 할 가격의 최소 최대 범위를 입력하세요(범위1~범위2 사이 검색).");
		 System.out.print("범위1 입력>>");
		 start = numException();
		 System.out.print("범위2 입력>>");
		 end =numException();
		 System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		 priceCompare priceSort = new priceCompare();
		 Collections.sort(list,priceSort);
		 for(Product p:list) {
				if(start<=p.price && end>=p.price) {
					 System.out.println(p);
				}
			}	 
	}
}