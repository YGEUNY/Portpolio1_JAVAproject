package test02;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main implements Comparator<Event> {
	
	Scanner sc = new Scanner(System.in);
	int num=0;
	int numId=0;
	Event arr[] = new Event[10];
	int changeAllSeat =0;
	
	Comparator<Event> dateCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			return e1.getDate()-e2.getDate();
		}
	};

	Comparator<Event> titleCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			return e1.getTitle().compareTo(e2.getTitle());
		}
	};
	
	Comparator<Event> titleReverseCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			return e2.getTitle().compareTo(e1.getTitle());
		}
	};
	
	Comparator<Event> ticketCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			if(e1.getTicketPrice()<e2.getTicketPrice()) return -1;
			else if(e1.getTicketPrice()>e2.getTicketPrice())	return 1;
			else return 0;
		}
	};
	
	
	Comparator<Event> ticketReverseCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			if(e1.getTicketPrice()>e2.getTicketPrice()) return -1;
			else if(e1.getTicketPrice()<e2.getTicketPrice())	return 1;
			else return 0;
		}
	};
	
	Comparator<Event> seatCompare = new Comparator<Event>() {
		@Override
		public int compare(Event e1, Event e2) {
			if(e1.getNumOfSeat()<e2.getNumOfSeat())	return -1;
			else if(e1.getNumOfSeat()>e2.getNumOfSeat())	return 1;
			else return 0;
		}
	};
	
	public static void main(String[] args) {
		Main m = new Main();
		m.run();
	}
	
	public void run() {
		int choice = 0;
		while(true) {
			menu();
			choice = numException();
			if(choice==1) {
				if(num<10)	inputData();
				else System.out.println("데이터를 10개 이상 입력할 수 없습니다.");
			}
			else if(choice==2)	printList();
			else if(choice==3)	changeNumOfSeat();
			else if(choice==4)	changeAllTicketPrice();
			else if(choice==5)	searchNumOfSeat();
			else if(choice==6)	break;
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void menu() {
		
		System.out.println("===================================================");
		System.out.println("1. 공연데이터 입력\n2. 공연리스트 보기\n3. 개별 좌석수 조정\n4. 모든 표금액 일괄 조정\n5. 좌석수 범위로 검색(좌석수 정렬)\n6. 종료");
		System.out.println("===================================================");
		
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
				System.out.print("다시 입력하세요 >>");
			}
		}
		return number;
	}
	
	public void inputData() {
		String choice=null;
		while(true) {
			System.out.println("공연 종류는 ? (m:뮤지컬  c:영화  p:연극");
			System.out.print("입력>>");
			choice = sc.next();
			if(!choice.equals("c") || !choice.equals("C") || !choice.equals("m")  || !choice.equals("M")  || !choice.equals("p")  ||!choice.equals("P") )
				System.out.println("c, m, p중에서 입력하세요.");
			else break;
			}
		if(choice.equals("C")||choice.equals("c"))	inputMovie();
		else if(choice.equals("m")||choice.equals("M"))	inputMusical();
		else if(choice.equals("p")||choice.equals("P"))	inputPlay();
		
		num++;
		numId++;
	}
	
	public void inputMusical() {
		arr[num] = new Musical();
		inputSame();
		System.out.print("주연명 입력>>");
		((Musical)arr[num]).setActor(sc.next());
		System.out.print("참여배우 수 입력>>");
		((Musical)arr[num]).setNumActor(numException());
	}
	
	public void inputMovie() {
		arr[num] = new Movie();
		inputSame();
		System.out.print("감독 수 입력>>");
		((Movie)arr[num]).setDirector(sc.next());
		System.out.print("출연배우 입력>>");
		((Movie)arr[num]).setActor(sc.next());
	}
	
	public void inputPlay() {
		arr[num]= new Play();
		inputSame();
		System.out.print("연극배우명 입력>>");
		((Play)arr[num]).setPlayActor(sc.next());
		System.out.print("무대감독>>");
		((Play)arr[num]).setStageManager(sc.next());
	}
	
	public void inputSame() {
		arr[num].setId(numId+1);
		System.out.print("공연제목 입력>>");
		arr[num].setTitle(sc.next());
		System.out.print("계약금액 입력>>");
		arr[num].setContractPrice(numException());
		System.out.print("공연날짜 입력( 예 : 20170401)>>");
		arr[num].setDate(inputDate());
		System.out.print("표금액 입력>>");
		arr[num].setTicketPrice(numException());
		System.out.print("좌석수 입력>>");
		arr[num].setNumOfSeat(numException());
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
	
	public void printList() {
		int choice =0;
		while(true) {
			System.out.println(" (1) 날짜순(기본)  (2) 공연명   (3) 공연명 역순   (4) 표금액순   (5) 표금액 역순 ");
			System.out.print("입력 >> ");
			choice = numException();
			if(choice!=1&&choice!=2&&choice!=3&&choice!=4&&choice!=5) System.out.println("1~5 중에서 입력하세요.");
			else break;
		}
		if(choice==1)	sortDate();
		else if(choice==2)	sortTitle();
		else if(choice==3)	reverseSortTitle();
		else if(choice==4)	sortTicketPrice();
		else if(choice==5)	reverseSortTicketPrice();
	}
	
	public void sortDate() {
		ArrayList<Event> list = new ArrayList<Event>();
		for(int i=0;i<num;i++) {
			list.add(arr[i]);
		}
		Collections.sort(list,dateCompare);
		for(int i=0;i<num;i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public void sortTitle() {
		ArrayList<Event> titleList = new ArrayList<Event>();
		for(int i=0;i<num;i++) {
			titleList.add(arr[i]);
		}
		Collections.sort(titleList,dateCompare);
		for(int i=0;i<num;i++) {
			System.out.println(titleList.get(i).toString());
		}
	}
	
	public void reverseSortTitle() {
		ArrayList<Event> reverseTitleList = new ArrayList<Event>();
		for(int i=0;i<num;i++) {
			reverseTitleList.add(arr[i]);
		}
		Collections.sort(reverseTitleList,dateCompare);
		for(int i=0;i<num;i++) {
			System.out.println(reverseTitleList.get(i).toString());
		}
	}
	
	public void sortTicketPrice() {
		ArrayList<Event> ticketList = new ArrayList<Event>();
		for(int i=0;i<num;i++) {
			ticketList.add(arr[i]);
		}
		Collections.sort(ticketList,dateCompare);
		for(int i=0;i<num;i++) {
			System.out.println(ticketList.get(i).toString());
		}
	}
	
	public void reverseSortTicketPrice() {
		ArrayList<Event> reverseTicketList = new ArrayList<Event>();
		for(int i=0;i<num;i++) {
			reverseTicketList.add(arr[i]);
		}
		Collections.sort(reverseTicketList,dateCompare);
		for(int i=0;i<num;i++) {
			System.out.println(reverseTicketList.get(i).toString());
		}
	}
	
	
	public void changeNumOfSeat() {
		sortDate();
		int changeSeat =0;
		while(true) {
			System.out.print("공연 번호 선택>>");
			changeSeat = numException();
			if(changeSeat<1 || changeSeat >num+1)	System.out.println("존재하지 않는 리스트입니다.");
			else break;
		}
		for(int i=0;i<num;i++) {
			if(changeSeat== arr[i].id) {
				System.out.print("현재 좌석수는 " +arr[i].numOfSeat+" 입니다. 새로운 좌석수는? ");
				arr[i].setNumOfSeat(numException());
			}
		}
	}
	
	public void changeAllTicketPrice() {
		int choice =0;
		while(true) {
			System.out.println(" 1.표금액 일괄 올림  2.표금액 일괄 내림  3.취소"); 
			System.out.print("선택 : ");
			choice = numException();
			if(choice==1)	 changeAllTicket_one();
			else if(choice==2)	 changeAllTicket_two();
			else if(choice==3) 	break;
		}
	}
	
	
	public void changeAllTicket_one() {
		System.out.println("일괄 올릴 표금액은 ?");
		changeAllSeat = numException();
		for(int i=0;i<num;i++) {
			arr[i].setNumOfSeat(arr[i].numOfSeat+changeAllSeat);
		}
	}
	
	public void changeAllTicket_two() {
		System.out.println("일괄 내릴 표금액은 ?");
		changeAllSeat = numException();
		for(int i=0;i<num;i++) {
			if(arr[i].numOfSeat<=changeAllSeat)	arr[i].setNumOfSeat(0);
			else	arr[i].setNumOfSeat(arr[i].numOfSeat-changeAllSeat);
		}
	}
	
	public void searchNumOfSeat() {
		int min=0, max=0;
		 System.out.println("검색 할 좌석 수의 최소 최대 범위를 입력하세요(범위1~범위2 사이 검색).");
		 System.out.print("범위1 입력>>");
		 min = numException();
		 System.out.print("범위2 입력>>");
		 max =numException();
		 System.out.println("번호   공연명   공연형태   날짜   계약금액(천원)  표금액(천원)   좌석수   기타정보  ");
		 ArrayList<Event> searchList = new ArrayList<Event>();
		 for(int i=0;i<num;i++) {
				if(min<=arr[i].numOfSeat && max>=arr[i].numOfSeat) {
					searchList.add(arr[i]);
				}
				else ;
			}	
		 Collections.sort(searchList,seatCompare);
			for(Event e:searchList) {
				System.out.println(e.toString());
			}
	}
	
	

	@Override
	public int compare(Event o1, Event o2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
