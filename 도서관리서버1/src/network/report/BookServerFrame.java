package network.report;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import javax.swing.*;

public class BookServerFrame extends JFrame{
	private LibraryManager libraryManager = null;
	private JTextArea log = new JTextArea();
	private HashMap<String, String> map = new HashMap<String, String>();
	private HashMap<String, String> idMap = new HashMap<String, String>();
	private ArrayList<String> list = new ArrayList<String>();
	
	public BookServerFrame(){
		super("도서 조회 서버");
		setSize(300,300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.add(new JLabel("점수 조회 서버입니다"));
		c.add(new JScrollPane(log), BorderLayout.CENTER);
		setVisible(true);
		
		libraryManager = new LibraryManager("c:\\dev\\book.txt");
		
		if(libraryManager.isFileRead()) {
			log.setText("book.txt 읽기 완료\n");
			new ServerThread().start();
		}
	}
	
	class ServerThread extends Thread{
		@Override
		public void run() {
			ServerSocket listener = null;
			Socket socket = null;
			try {
				listener = new ServerSocket(9998);
				while(true) {
					socket = listener.accept();
					log.append("클라이언트 연결됨\n");
					new ServiceThread(socket).start();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(listener != null)
					listener.close();
				if(socket != null)
					socket.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
	}
	
	class LibraryManager{
		private boolean fileOn = false;
		public LibraryManager(String fileName) {
			try {
				Scanner reader = new Scanner(new FileReader(fileName));				
				while(reader.hasNext()) {
					String id = reader.next();
					String title = reader.next();
					String bookData = reader.nextLine();
					map.put(title, bookData);
					idMap.put(bookData, id );
				}				
				reader.close();
				fileOn = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fileOn = false;
			} 
			try {
				Scanner reader = new Scanner(new FileReader(fileName));				
				while(reader.hasNext()) {
					list.add(reader.nextLine()+"\n");
				}				
				reader.close();
				fileOn = true;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				fileOn = false;
			} 
		}
		public boolean isFileRead() {
			return fileOn;
		}
		public String get(String title) {
			return map.get(title);
		}
		public String getId(String bookData) {
			return idMap.get(bookData);
		}
	}
	
	class ServiceThread extends Thread{
		private Socket socket = null;
		private BufferedReader in = null;
		private BufferedWriter out = null;
		
		public ServiceThread(Socket socket) {
			this.socket = socket;
			try {
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public void run() {
			while(true) {
				try {
					String title = in.readLine();
					String bookData = libraryManager.get(title);
					String id = libraryManager.getId(bookData);
					String str ="";
					if(bookData==null && !title.equals("List")) {
						out.write("없는 책입니다.\n");
						log.append(title+" 없음\n");}
					else if(title.equals("List")) {
						for(int i=0; i<list.size();i++) {
							str += list.get(i);
						}
						out.write(str+"\n"+"finish\n");
						log.append(str);
				}
					else if(bookData!=null && !title.equals("List")){
						out.write(id+" "+title+" "+bookData+"\n");
						log.append(id+" "+title+" "+bookData+"\n");	}
					out.flush();
				}catch(IOException e) {
					log.append("연결 종료\n");
					System.out.println("연결 종료");
					try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					return;
				}
			}
		}
	}
	public static void main(String[] args) {
		new BookServerFrame();
	}
}