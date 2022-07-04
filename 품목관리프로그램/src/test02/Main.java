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
				else System.out.println("�����͸� 10�� �̻� �Է��� �� �����ϴ�.");
			}
			else if(choice==2)	printList();
			else if(choice==3)	changeNumOfSeat();
			else if(choice==4)	changeAllTicketPrice();
			else if(choice==5)	searchNumOfSeat();
			else if(choice==6)	break;
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	public void menu() {
		
		System.out.println("===================================================");
		System.out.println("1. ���������� �Է�\n2. ��������Ʈ ����\n3. ���� �¼��� ����\n4. ��� ǥ�ݾ� �ϰ� ����\n5. �¼��� ������ �˻�(�¼��� ����)\n6. ����");
		System.out.println("===================================================");
		
		System.out.print("�Է� >>");
		
	}
	
	public int numException() {
		int number;
		while(true) {			
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.print("�ٽ� �Է��ϼ��� >>");
			}
		}
		return number;
	}
	
	public void inputData() {
		String choice=null;
		while(true) {
			System.out.println("���� ������ ? (m:������  c:��ȭ  p:����");
			System.out.print("�Է�>>");
			choice = sc.next();
			if(!choice.equals("c") || !choice.equals("C") || !choice.equals("m")  || !choice.equals("M")  || !choice.equals("p")  ||!choice.equals("P") )
				System.out.println("c, m, p�߿��� �Է��ϼ���.");
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
		System.out.print("�ֿ��� �Է�>>");
		((Musical)arr[num]).setActor(sc.next());
		System.out.print("������� �� �Է�>>");
		((Musical)arr[num]).setNumActor(numException());
	}
	
	public void inputMovie() {
		arr[num] = new Movie();
		inputSame();
		System.out.print("���� �� �Է�>>");
		((Movie)arr[num]).setDirector(sc.next());
		System.out.print("�⿬��� �Է�>>");
		((Movie)arr[num]).setActor(sc.next());
	}
	
	public void inputPlay() {
		arr[num]= new Play();
		inputSame();
		System.out.print("���ع��� �Է�>>");
		((Play)arr[num]).setPlayActor(sc.next());
		System.out.print("���밨��>>");
		((Play)arr[num]).setStageManager(sc.next());
	}
	
	public void inputSame() {
		arr[num].setId(numId+1);
		System.out.print("�������� �Է�>>");
		arr[num].setTitle(sc.next());
		System.out.print("���ݾ� �Է�>>");
		arr[num].setContractPrice(numException());
		System.out.print("������¥ �Է�( �� : 20170401)>>");
		arr[num].setDate(inputDate());
		System.out.print("ǥ�ݾ� �Է�>>");
		arr[num].setTicketPrice(numException());
		System.out.print("�¼��� �Է�>>");
		arr[num].setNumOfSeat(numException());
	}
	
	
	public int inputDate() {
		int mdate=0;
		while(true) {
			mdate = numException();
			if(((int)Math.log10(mdate)+1) != 8) {
				System.out.println("�ùٸ� �ڸ����� �Է��ϼ���.");
				System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
			}
			else {
				if(mdate%10000/100 < 1 || mdate%10000/100 > 12) {
					System.out.println("�ùٸ� ������ �Է��ϼ���.");
					System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
				}
				else {
					if(mdate%100 < 1 || mdate%100>31) {
						System.out.println("�ùٸ� ��¥�� �Է��ϼ���.");
						System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
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
			System.out.println(" (1) ��¥��(�⺻)  (2) ������   (3) ������ ����   (4) ǥ�ݾ׼�   (5) ǥ�ݾ� ���� ");
			System.out.print("�Է� >> ");
			choice = numException();
			if(choice!=1&&choice!=2&&choice!=3&&choice!=4&&choice!=5) System.out.println("1~5 �߿��� �Է��ϼ���.");
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
			System.out.print("���� ��ȣ ����>>");
			changeSeat = numException();
			if(changeSeat<1 || changeSeat >num+1)	System.out.println("�������� �ʴ� ����Ʈ�Դϴ�.");
			else break;
		}
		for(int i=0;i<num;i++) {
			if(changeSeat== arr[i].id) {
				System.out.print("���� �¼����� " +arr[i].numOfSeat+" �Դϴ�. ���ο� �¼�����? ");
				arr[i].setNumOfSeat(numException());
			}
		}
	}
	
	public void changeAllTicketPrice() {
		int choice =0;
		while(true) {
			System.out.println(" 1.ǥ�ݾ� �ϰ� �ø�  2.ǥ�ݾ� �ϰ� ����  3.���"); 
			System.out.print("���� : ");
			choice = numException();
			if(choice==1)	 changeAllTicket_one();
			else if(choice==2)	 changeAllTicket_two();
			else if(choice==3) 	break;
		}
	}
	
	
	public void changeAllTicket_one() {
		System.out.println("�ϰ� �ø� ǥ�ݾ��� ?");
		changeAllSeat = numException();
		for(int i=0;i<num;i++) {
			arr[i].setNumOfSeat(arr[i].numOfSeat+changeAllSeat);
		}
	}
	
	public void changeAllTicket_two() {
		System.out.println("�ϰ� ���� ǥ�ݾ��� ?");
		changeAllSeat = numException();
		for(int i=0;i<num;i++) {
			if(arr[i].numOfSeat<=changeAllSeat)	arr[i].setNumOfSeat(0);
			else	arr[i].setNumOfSeat(arr[i].numOfSeat-changeAllSeat);
		}
	}
	
	public void searchNumOfSeat() {
		int min=0, max=0;
		 System.out.println("�˻� �� �¼� ���� �ּ� �ִ� ������ �Է��ϼ���(����1~����2 ���� �˻�).");
		 System.out.print("����1 �Է�>>");
		 min = numException();
		 System.out.print("����2 �Է�>>");
		 max =numException();
		 System.out.println("��ȣ   ������   ��������   ��¥   ���ݾ�(õ��)  ǥ�ݾ�(õ��)   �¼���   ��Ÿ����  ");
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
