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
	private JButton searchBtn = new JButton("�˻�");
	private JButton searchAllBtn = new JButton("��ü����");
	
	public BookClientFrame() {
		super("���� ��ȸ Ŭ���̾�Ʈ");
		setSize(300,800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(new JLabel("å ���� �Է� "));
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
				dataTa.append("<<<<<<<<< å ���� �˻� >>>>>>>>>\n id  å����     ����   ����\n==============================\n");
				dataTa.append(" " + bookData + "\n\n");
			} catch (IOException e1) {
				System.out.println("Ŭ���̾�Ʈ : �����κ��� ���� ����");
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
				dataTa.append("<<<<<<<<< ��ü���� >>>>>>>>>\nid  å����     ����   ����\n==============================\n");
				
				bookData= in.readLine();
				while(!bookData.equals("finish")) {
					dataTa.append( bookData+"\n");
					bookData= in.readLine();}
				dataTa.append("\n");
			} catch (IOException e2) {
				System.out.println("Ŭ���̾�Ʈ : �����κ��� ���� ����");
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
