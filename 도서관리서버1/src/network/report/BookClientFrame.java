package network.report;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class BookClientFrame extends JFrame{

	private JTextField nameTf = new JTextField(10);
	private JTextArea dataTa = new JTextArea(35,25);
	private Socket socket = null;
	private BufferedReader in = null;
	private BufferedWriter out = null;
	private JButton searchBtn = new JButton("검색");
	private JButton searchAllBtn = new JButton("전체보기");
	
	public BookClientFrame() {
		super("도서 조회 클라이언트");
		setSize(300,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JLabel("책 제목 입력 "));
		c.add(nameTf);
		c.add(searchBtn);
		c.add(searchAllBtn);
		c.add(new JScrollPane(dataTa));	
		setVisible(true);		
		setupConnection();
		searchBtn.addActionListener(new MyActionListener());
		searchAllBtn.addActionListener(new MyActionListener2());   }
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				if(nameTf.getText().length()==0)
					return;						
				out.write(nameTf.getText() + "\n");
				out.flush();
				String bookData = in.readLine();
				dataTa.append("<<<<<<<<< 책 제목 검색 >>>>>>>>>\n id  책제목     저자   가격\n==============================\n");
				dataTa.append(" " + bookData + "\n\n");
			} catch (IOException e1) {
				System.out.println("클라이언트 : 서버로부터 연결 종료");
				return;
			}
		}
	}
	
	class MyActionListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try {	
				out.write("List"+"\n");
				out.flush();
				String bookData="";
				dataTa.append("<<<<<<<<< 전체보기 >>>>>>>>>\nid  책제목     저자   가격\n==============================\n");
				
				bookData= in.readLine();
				while(!bookData.equals("finish")) {
					dataTa.append( bookData+"\n");
					bookData= in.readLine();}
				dataTa.append("\n");
			} catch (IOException e2) {
				System.out.println("클라이언트 : 서버로부터 연결 종료");
				return;
			}	
		}
	}
	
	private void setupConnection(){
		try {
			socket = new Socket("localhost", 9998);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public static void main(String[] args) {
		new BookClientFrame();
	}
}
