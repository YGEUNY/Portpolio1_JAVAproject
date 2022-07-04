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
			System.out.println("0. 도서 데이터 파일 지정 및 서버 연결\n1. 도서 데이터 입력              2. 도서리스트 보기\n3. 도서 데이터 삭제              4. 도서 정보 검색\n5. 도서 데이터 파일 저장       6. 종료(종료시 파일 저장)");
			System.out.println("===============================");
			System.out.print(">> 입력  ");
			String choice = sc.next();
			if(choice.equals("0")) {
				dataServer();
			}
			else if(choice.equals("1")||choice.equals("2")||choice.equals("3")||choice.equals("4")||choice.equals("5"))
				System.out.println("서버와 연결 후 사용해 주세요.");
			else if(choice.equals("6")){
				sendData(choice);
//					saveData();
				break;
			}
			else System.out.println("잘못된 입력입니다.");
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void dataServer() throws IOException {
		System.out.print("서버 ip 입력 : ");
		inputIP = sc.next();
		System.out.print("포트번호 입력 : ");
		inputPort = sc.nextInt();
		System.out.print("파일이름 입력 : ");
		String fileName = sc.next();
		
		try {
			socket = new Socket(inputIP, inputPort);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			System.out.println("서버 연결 및 파일지정에 성공하였습니다.");
			out.write(fileName);
			out.flush();
			menu();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println(e.getMessage() + "가 오류입니다.");
		}
	}
	
	public void menu() throws IOException {
		while(true) {
			System.out.println("===============================");
			System.out.println("0. 도서 데이터 파일 지정 및 서버 연결\n1. 도서 데이터 입력              2. 도서리스트 보기\n3. 도서 데이터 삭제              4. 도서 정보 검색\n5. 도서 데이터 파일 저장       6. 종료(종료시 파일 저장)");
			System.out.println("===============================");
			System.out.print(">> 입력");

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
		
		System.out.print("제목 입력 : ");
		title=sc.next();
		System.out.print("날짜 입력 : ");
		date=sc.next();
		System.out.print("가격 입력 : ");
		price=sc.nextInt();
		System.out.print("저자 입력 : ");
		writer=sc.next();
		System.out.print("권수 입력 : ");
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
//		System.out.print("제목 입력 : ");
//		title=sc.next();
//		System.out.print("날짜 입력 : ");
//		date=sc.next();
//		System.out.print("가격 입력 : ");
//		price=numException();
//		System.out.print("저자 입력 : ");
//		writer=sc.next();
//		System.out.print("권수 입력 : ");
//		bookCount=numException();
//		
//		String str = "";
//		str = title+" "+date+" "+price+" "+writer+" "+bookCount;
//		out.write(str);
//		out.flush();
		
		
	}
	
	public void printList() throws IOException {
		System.out.println("(1) 가격순(기본)  (2) 가격 역순보기  (3) 제목순 보기");
		System.out.print("선택 : ");
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
		else System.out.println("없는 선택지 입니다.");
	
	}
	
	public void printSame(String choice) throws IOException {
		sendData(choice);
		String str = in.readLine();
		System.out.println(str+"\n");
	}

	public void searchBook() throws IOException {
		String bookList = in.readLine();
		System.out.println(bookList);
		System.out.print("검색할 책 제목 : ");
		String bookTitle = sc.next();
		out.write(bookTitle);
		out.flush();
		String str = in.readLine();
		System.out.println("번호  제목    날짜     가격  저자    권수");
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
				System.out.println("잘못 입력하셨습니다.");
			}
		}
		return number;
	}
	
	public static void main(String[] args) throws IOException {
		new BookClient();
	}
}
