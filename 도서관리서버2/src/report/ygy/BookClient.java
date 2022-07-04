package report.ygy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;



public class BookClient{
	String portNum;
	int inputPort;
	String inputIP;
	Scanner sc  = new Scanner(System.in);
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	ArrayList<Book> list = new ArrayList<Book>();
	Book book = null;
	int num=0;
	
	public BookClient() throws IOException  {
		
		while(true) {
			System.out.println("===============================");
			System.out.println("0. ���� ������ ���� ���� �� ���� ����\n1. ���� ������ �Է�              2. ��������Ʈ ����\n3. ���� ������ ����              4. ���� ���� �˻�\n5. ���� ������ ���� ����       6. ����(����� ���� ����)");
			System.out.println("===============================");
			System.out.print(">> �Է�  ");
			String choice = sc.next();
			if(choice.equals("0")) {
				dataServer();
			}
			else if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5"))
				System.out.println("������ ���� �� ����� �ּ���.");
			else if(choice.equals("6")){
				sendData(choice);
//					saveData();
				break;
			}
			else System.out.println("�߸��� �Է��Դϴ�.");
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	public void dataServer() throws IOException {
		System.out.print("���� ip �Է� : ");
		inputIP = sc.next();
		System.out.print("��Ʈ��ȣ �Է� : ");
		inputPort = sc.nextInt();
		System.out.print("�����̸� �Է� : ");
		String fileName = sc.next();
		
		try {
			socket = new Socket(inputIP, inputPort);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("���� ���� �� ���������� �����Ͽ����ϴ�.");
			out.write(fileName);
			out.flush();
			menu();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage() + "�� �����Դϴ�.");
		}
	}
	
	public void menu() throws IOException {
		while(true) {
			System.out.println("===============================");
			System.out.println("0. ���� ������ ���� ���� �� ���� ����\n1. ���� ������ �Է�              2. ��������Ʈ ����\n3. ���� ������ ����              4. ���� ���� �˻�\n5. ���� ������ ���� ����       6. ����(����� ���� ����)");
			System.out.println("===============================");
			System.out.print(">> �Է�");

				String choice = sc.next();
				if(choice.equals("1")) {
					sendData(choice);
					inputData();
				}
				else if(choice.equals("2")){
					sendData(choice);
					printList();
				}
				else if(choice.equals("3")){
					sendData(choice);
				//	deleteData();
				}
				else if(choice.equals("4")){
					sendData(choice);
					searchBook();
				
				}
				else if(choice.equals("5")){
					sendData(choice);
		//			String result = in.readLine();
					System.out.println();
				}
				else if(choice.equals("6")) {
//					sendData(choice);
//					saveData();
					break;
				}
		}
		
	}
	
	public void sendData(String choice) throws IOException {
			out.write(choice+"\n");
			out.flush();		
	}
	
	
	
	
	public void inputData() throws IOException {
		String title, date,writer;
		int price,bookcount;
		
		System.out.print("���� �Է� : ");
		title=sc.next();
		System.out.print("��¥ �Է� : ");
		date=sc.next();
		System.out.print("���� �Է� : ");
		price=sc.nextInt();
		System.out.print("���� �Է� : ");
		writer=sc.next();
		System.out.print("�Ǽ� �Է� : ");
		bookcount=sc.nextInt();

		list.add(new Book(title,date,price,writer,bookcount));
		String str="";
		for(Book b:list)
		{
			str+=b.toString();
		}
		out.write(str+"\n");
		out.flush();
		num++;
		
//		String title, date,writer;
//		int price,bookCount;
//		
//		System.out.print("���� �Է� : ");
//		title=sc.next();
//		System.out.print("��¥ �Է� : ");
//		date=sc.next();
//		System.out.print("���� �Է� : ");
//		price=numException();
//		System.out.print("���� �Է� : ");
//		writer=sc.next();
//		System.out.print("�Ǽ� �Է� : ");
//		bookCount=numException();
//		
//		String str = "";
//		str = title+" "+date+" "+price+" "+writer+" "+bookCount;
//		out.write(str);
//		out.flush();
		
		
	}
	
	public void printList() throws IOException {
		System.out.println("(1) ���ݼ�(�⺻)  (2) ���� ��������  (3) ����� ����");
		System.out.print("���� : ");
		String choice = sc.next();
		
		if(choice.equals("1")) {
			sendData(choice);
			String str = in.readLine();
			System.out.println(str+"\n");
		}
		else if(choice.equals("2")) {
			sendData(choice);
			String str = in.readLine();
			System.out.println(str+"\n");
		}
		else if(choice.equals("3")) {
			sendData(choice);
			String str = in.readLine();
			System.out.println(str+"\n");
		}
		else System.out.println("���� ������ �Դϴ�.");
	
	}
	
	public void printSame(String choice) throws IOException {
		sendData(choice);
		String str = in.readLine();
		System.out.println(str+"\n");
	}

	public void searchBook() throws IOException {
		String bookList = in.readLine();
		System.out.println(bookList);
		System.out.print("�˻��� å ���� : ");
		String bookTitle = sc.next();
		out.write(bookTitle);
		out.flush();
		String str = in.readLine();
		System.out.println("��ȣ  ����    ��¥     ����  ����    �Ǽ�");
		System.out.println("---------------------------------");
		System.out.println(str);
	}
	
	public int numException() {
		int number=0;
		while(true) {
			try {
				num = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("�߸� �Է��ϼ̽��ϴ�.");
			}
		}
		return number;
	}
	
	public static void main(String[] args) throws IOException {
		new BookClient();
	}
}
