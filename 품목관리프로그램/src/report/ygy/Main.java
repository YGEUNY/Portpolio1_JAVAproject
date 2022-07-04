package report.ygy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	Product arr[] = new Product[10];
	int num =0;
	int idNum=0;
	
	
	
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
		
		num++;
		idNum++;
	}
	
	public void inputCPU() {
		arr[num] = new CPU();
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
		((CPU)arr[num]).setSpeed(inputSpeed);
		System.out.print("핀수 입력>>");
		((CPU)arr[num]).setInch(numException());
	}
	
	public void inputRefrigerator() {
		arr[num] = new Refrigerator();
		same();
		System.out.print("용량 입력>>");
		((Refrigerator)arr[num]).setLiter(numException());
		System.out.print("타입 입력>>");
		((Refrigerator)arr[num]).setType(sc.next());
	}
	
	public void inputAudio() {
		arr[num] = new Audio();
		same();
		System.out.print("앰프출력 입력>>");
		((Audio)arr[num]).setOutPut(numException());
		System.out.print("튜너지원여부 입력( 1.지원 2.지원x )>>");
		((Audio)arr[num]).setTuner(inputTuner());
	}
	
	public void same() {
		arr[num].setId(idNum+1);
		System.out.print("제품명 입력>>");
		arr[num].setModel(sc.next());
		System.out.print("제조사 입력>>");
		arr[num].setCompany(sc.next());
		System.out.print("입고날짜 입력( 예 : 20170401)>>");
		arr[num].setDate(inputDate());
		System.out.print("재고수량 입력>>");
		arr[num].setNumOfStock(numException());
		System.out.print("가격 입력>>");
		arr[num].setPrice(numException());
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
		if(choice==1)	printDateList();
		else if(choice==2)	printModelList();
		else if(choice==3)	printModelReverseList();
		else if(choice==4)	printPriceList();
		else if(choice==5)	printPriceReverseList();
	}

	
	
	public void printDateList() {	//날짜순 정렬
		ArrayList<Product> dateList = new ArrayList<Product>();
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		for(int i=0; i<num;i++) {
			dateList.add(arr[i]);
		}
		Collections.sort(dateList);
		
		for(int i=0;i<num;i++) {
			System.out.println(dateList.get(i).toString());
		}
	}
	
	public void printModelList() {		//모델명 순정렬
		ArrayList<Product> modelList = new ArrayList<Product>();
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		modelCompare modelSort = new modelCompare();
		for(int i=0; i<num;i++) {
			modelList.add(arr[i]);
		}
		Collections.sort(modelList,modelSort);		
		
		for(int i=0;i<num;i++) {
			System.out.println(modelList.get(i).toString());
		}
	}
	
	
	public void printModelReverseList() {	//모델명 역순
		ArrayList<Product> rModelList = new ArrayList<Product>();
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		modelReverseCompare rModelSort = new modelReverseCompare();
		for(int i=0; i<num;i++) {
			rModelList.add(arr[i]);
		}
		Collections.sort(rModelList,rModelSort);
		for(int i=0;i<num;i++) {
			System.out.println(rModelList.get(i).toString());
		}
	}
	
	
	public void printPriceList() {	// 가격 ㄴ내림차순
		ArrayList<Product> priceList = new ArrayList<Product>();
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");		
		
		priceCompare priceSort = new priceCompare();
		for(int i=0; i<num;i++) {
			priceList.add(arr[i]);
		}
		Collections.sort(priceList,priceSort);
		for(int i=0;i<num;i++) {
			System.out.println(priceList.get(i).toString());
		}
	}
	
	
	
	
	public void printPriceReverseList() {	//가격 오름차순		
		ArrayList<Product> rPriceList = new ArrayList<Product>();
		System.out.println("번호   제품명   제조사   입고날짜   재고수량  가격   기타정보  ");
		priceReverseCompare rPriceSort = new priceReverseCompare();
		for(int i=0; i<num;i++) {
			 rPriceList.add(arr[i]);
		}
		Collections.sort( rPriceList, rPriceSort);
		for(int i=0;i<num;i++) {
			System.out.println( rPriceList.get(i).toString());
		}
	}
	
	
	public void printStockList() {
		ArrayList<Product> stockList= new ArrayList<Product>();
		priceReverseCompare stockSort = new priceReverseCompare();
		for(int i=0; i<num;i++) {
			 stockList.add(arr[i]);
		}
		Collections.sort( stockList, stockSort);
		for(int i=0;i<num;i++) {
			System.out.println( stockList.get(i).toString());}
		
	}
	
	
	public void changeOfStock() {
		printStockList();
		int changeStock =0;
		while(true) {
			System.out.print("제품번호 선택>>");
			changeStock = numException();
			if(changeStock<1 || changeStock >num+1)	System.out.println("존재하지 않는 리스트입니다.");
			else break;
		}
		for(int i=0;i<num;i++) {
			if(changeStock == arr[i].id) {
				System.out.print("현재 재고수는 " +arr[i].numOfStock+" 입니다. 새로운 재고수는? ");
				arr[i].setNumOfStock(numException());
			}
		}
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
		System.out.println("일괄 올릴 재고 수량은 ?");
		changeAllPrice = numException();
		for(int i=0;i<num;i++) {
			arr[i].setPrice(arr[i].price+changeAllPrice);
		}
	}
	
	public void changeAllPrice_two() {
		System.out.println("일괄 내릴 가격 수량은 ?");
		changeAllPrice = numException();
		for(int i=0;i<num;i++) {
			if(arr[i].price<=changeAllPrice)	arr[i].setPrice(0);
			else	arr[i].setPrice(arr[i].price-changeAllPrice);
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
		 ArrayList<Product> searchList = new ArrayList<Product>();
		 priceCompare priceSort = new priceCompare();
		 for(int i=0;i<num;i++) {
				if(start<=arr[i].price && end>=arr[i].price) {
					searchList.add(arr[i]);
				}
				else ;
			}	
		 Collections.sort(searchList,priceSort);
			for(Product p:searchList) {
				System.out.println(p.toString());
			}
	}
}