package report.ygy;

import java.io.*;
import java.net.*;
import java.util.*;



public class BookServer {
	private BookManager bookManager = new BookManager();
	private Scanner sc = new Scanner(System.in);
	private ArrayList<Book> list = new ArrayList<Book>();
	private Book book = null;
	private String filePath = "c:\\dev";
	private String file="";
	private String fileName = "";
	private String bookList="";
	Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	
	public BookServer(){
		System.out.println("�������� �����Դϴ�.");
		
		new ServerThread().start();
	}


	
	private class ServerThread extends Thread{
		

		@Override
		public void run() {
			ServerSocket listener = null;
			
			try {
				listener = new ServerSocket(5555);
				System.out.println("���� ��� ��...");
				while(true){
					socket = listener.accept();
					System.out.println("���� �����");
					in = new BufferedReader(
							new InputStreamReader(socket.getInputStream()));
					out = new BufferedWriter(
							new OutputStreamWriter(socket.getOutputStream()));
					ServiceThread th = new ServiceThread(socket);
					th.start();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				if(listener != null)
					listener.close();
				if(socket != null)
					socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	class BookManager {

		private HashMap<String, Book> map = new HashMap<String, Book>();
		private HashMap<Integer, Book> numMap = new HashMap<Integer, Book>();
		public BookManager() {
			
		}
		public void addBook() {
			String readBook ="";
			Scanner reader = new Scanner(readBook);
			while(reader.hasNext()) {
				book = new Book();
				book.setTitle(reader.next());
				book.setDate(reader.next());
				book.setPrice(reader.nextInt());
				book.setWriter(reader.next());
				book.setBookCount(reader.nextInt());
				list.add(book);
				map.put(book.getTitle(), book);
			}
			reader.close();
			System.out.println("������ �Է¿� �����Ͽ����ϴ�.");
		}
		
		public void printBook(String str) {
			int num=1;
			if(str.equals("1")) {
				PriceComparater priceSort = new PriceComparater();
				Collections.sort(list, priceSort);
				for(Book b:list) {
					b.setNum(num);
					bookList += b.toString();
					num++;
				}
			}
			else if(str.equals("2")) {
				PriceReverseComparator priceReverse = new PriceReverseComparator();
				Collections.sort(list, priceReverse);
				for(Book b:list) {
					b.setNum(num);
					bookList += b.toString();
					num++;
				}
			}
			else if(str.equals("3")) {
				TitleComparator titleSort = new TitleComparator();
				Collections.sort(list, titleSort);
				for(Book b:list) {
					b.setNum(num);
					bookList += b.toString();
					num++;
				}
			}
			System.out.println("��ȣ  ����    ��¥     ����  ����    �Ǽ�");
			System.out.println("---------------------------------");
			System.out.println(bookList);
		}
		
		public String removeData(String str) {
			System.out.println("��ȣ  ����    ��¥     ����  ����    �Ǽ�");
			System.out.println("---------------------------------");
			System.out.println(bookList);
			return str;
		//	for()
			
		}
		
		public void saveData() {
	//		file = 
		}
		public String get(String title) {
			String str ="";
			for(Book b : list) {
				if(map.get(title).equals(b.toString())) {
					str = b.toString();
				}
			}
	
			return str;
		}
	}
	
	class ServiceThread extends Thread{	// ����-Ŭ���̾�Ʈ�� ����Ǹ� ����!!
//		private Socket socket = null;
//		private BufferedReader in = null;
//		private BufferedWriter out = null;
		
		public ServiceThread(Socket socket) {
//			this.socket = socket;
//			try {
//				in = new BufferedReader(
//						new InputStreamReader(socket.getInputStream()));
//				out = new BufferedWriter(
//						new OutputStreamWriter(socket.getOutputStream()));
//				
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		
		@Override
		public void run() {
			
			while(true) {
			try {
					fileName = in.readLine();	//���� �̸� �б�
					String inputMessage = in.readLine(); //Ŭ���̾�Ʈ �� �� �б�
					String readBook="";
					if(inputMessage.equals("1")) {
						try {
							readBook = in.readLine();
						}catch(SocketException e) {
							e.getMessage();
						}
						bookManager.addBook();
		                 
						
					}
					else if(inputMessage.equals("2")){
						String str = in.readLine();
						bookManager.printBook(str);	
						System.out.println(bookList+"\n");
						out.write(bookList+"\n");
						out.flush();
					}
					else if(inputMessage.equals("3")) {
						out.write(bookList);
						out.flush();
						String str = in.readLine();
						bookManager.removeData(str);
					}
					else if(inputMessage.equals("4")) {
						out.write(bookList);
						out.flush();
						String str = in.readLine();						
						String search = bookManager.get(str);
						System.out.println(search);
						out.write(search);
						out.flush();
					}
					else if(inputMessage.equals("5")) {
						
					}
					else if(inputMessage.equals("6")){
						break;	//6 �� ������ ���� ����.
					}
			//		out.flush();//out�� ��Ʈ�� ���ۿ� �ִ� ��� ���ڿ� ����
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				System.out.println("���� ����");
				out.write("���α׷��� �����Ͽ����ϴ�.");
				out.flush();

			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				return;
			}
		}
	}
	
	public static void main(String[] args) {
		new BookServer();
	}
	
	
	
	
}