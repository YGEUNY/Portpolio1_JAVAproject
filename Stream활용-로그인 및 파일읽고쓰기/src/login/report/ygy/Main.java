package login.report.ygy;

import java.util.*;
//import javax.crypto.*;
//import javax.crypto.spec.*;
import java.io.*;


public class Main {

	Scanner sc = new Scanner(System.in);
	ArrayList<User> list = new ArrayList<User>();
	User user = null;
	String id;
	TreeMap<String, User> idMap = new TreeMap<String, User>();
	TreeMap<String, User> pwMap = new TreeMap<String,User>();
	
	public static void main(String[] args) throws IOException {
		Main run = new Main();
		run.menu();
	}
	
	public void menu() throws IOException {
		int choice;
		firstAdmin();
		while(true) {
			System.out.println("=================================");
			System.out.println("0. �α���\n1. �ű�ȸ������\n2. ȸ������ ����\n3. ȸ�� Ż��\n4. ȸ������ ����\n5. ����");
			System.out.println("=================================");
			System.out.print("�Է�>>");
			choice = numException();
			if(choice==0)	login();
			else if(choice==1)	join();
			else if(choice==2)	changeData();
			else if(choice==3)	removeMember();
			else if(choice==4)	printData();
			else if(choice==5)	{
				idMap.put(user.getId(),user);
				pwMap.put(user.getPw(),user);
				saveData();
				break;
			}
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
	
	public void firstAdmin() throws IOException {
		user = new Administrator();
		((Administrator)user).setId("root");
		((Administrator)user).setPw("root");
		((Administrator)user).setAdress("   ");
		((Administrator)user).setGrade("������");
		((Administrator)user).setName("Admin");
		list.add(user);
		idMap.put(user.getId(),user);
		pwMap.put(user.getPw(),user);
	}
	
	public void login() {
		while(true) {
			System.out.print("   id    : ");
			id = sc.next();
			
			User u = idMap.get(id);
			if(u==null)	System.out.println("���� id�Դϴ�.");
			else break;
		}
		while(true) {
			System.out.print("Password : ");
			String pw = sc.next();
			
			User u = pwMap.get(pw);
			if(u==null)	System.out.println("���� pw�Դϴ�.");
			else break;
		}
		System.out.println("�α����� �Ϸ�Ǿ����ϴ�.");
	}
	

	
	public void join() throws IOException {
		int choice;
		while(true) {
			System.out.println("1.�Ϲ�����   2. ������");
			System.out.print("����>>");
			choice = numException();
			if(choice!=1 && choice!=2)	System.out.println("1, 2 �߿� �����ϼ���.");
			else break;
		}
		if(choice==1) {
			user = new GeneralMember();
			System.out.print("�̸� �Է� : ");
			((GeneralMember)user).setName(sc.next());
			((GeneralMember)user).setId(sameId());
			System.out.print("Password �Է� : ");
			((GeneralMember)user).setPw(sc.next());
			System.out.print("�ּ� �Է� : ");
			((GeneralMember)user).setAdress(sc.next());
			((GeneralMember)user).setGrade("�Ϲ�");
		}
		else if(choice==2) {
			user = new Administrator();
			System.out.print("�̸� �Է� : ");
			((Administrator)user).setName(sc.next());
			((Administrator)user).setId(sameId());
			System.out.print("Password �Է� : ");
			((Administrator)user).setPw(sc.next());
			System.out.print("�ּ� �Է� : ");
			((Administrator)user).setAdress(sc.next());
			((Administrator)user).setGrade("������");
			
		}
		System.out.println("ȸ�������� �Ϸ�Ǿ����ϴ�.");
		list.add(user);
		idMap.put(user.getId(),user);
		pwMap.put(user.getPw(),user);
		saveData();
	}
	
	public String sameId() {
		String inputId;
		while(true) {
			System.out.print("ID �Է� : ");
			inputId = sc.next();
			
			User u = idMap.get(inputId);
			if(u==null)	return inputId;
			else System.out.println("�ߺ��� ID �Դϴ�.");
		}
	}
	
	public void saveData() throws IOException {
		FileOutputStream fout=null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout=null;
		try {
			fout = new FileOutputStream("c:\\dev\\����\\login.txt");
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(list);
			oout.reset();
	//		System.out.println("ȸ������ ����Ϸ� !!");
		} catch (Exception e) {
			System.out.println("c:\\dev\\����\\login.txt�� ������ �� �������ϴ�. ��θ��� Ȯ���� �ּ���");
		}finally {
			try {
				fout.close();
				bout.close();
				oout.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}
		}
	}
	
	public void changeData() throws IOException {
		String choice;
		int sel;
	//	int num=0;
		login();
		
		for(User u:list) {
			if((u.getId()).equals(id)) {
				if((u.getGrade()).equals("������")){
					while(true) {
						System.out.println("1.    id/pw ����\n2.    �̸� ����\n3.    �ּ� ����");
						System.out.print("����>>");
						sel = numException();
						if(sel!=1 && sel!=2 && sel!=3)	System.out.println("1~3�߿��� �Է�.");
						else break;
					}
					if(sel==1) {
						System.out.print("������ id �Է�>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								if(r.getName().equals("root"))	System.out.println("�⺻�����ڴ� ������ �� �����ϴ�.");
								else {
									r.setId(sameId());
									System.out.print("Password �Է� : ");
									r.setPw(sc.next());
									idMap.put(r.getId(),user);
									pwMap.put(r.getPw(),user);
								}
								System.out.println("������ �Ϸ��Ͽ����ϴ�. ");
							}
						}
					}
					else if(sel==2) {
						System.out.print("������ �̸� �Է�>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								if(r.getName().equals("Admin"))	System.out.println("�⺻�����ڴ� ������ �� �����ϴ�.");
								else {
									System.out.print("�̸� �Է� : ");
									r.setName(sc.next());
									idMap.put(r.getId(),user);
									pwMap.put(r.getPw(),user);
								}
								System.out.println("������ �Ϸ��Ͽ����ϴ�. ");
							}
						}
					}
					else if(sel==3) {
						System.out.print("������ �̸� �Է�>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								System.out.print("�ּ� �Է� : ");
								u.setAdress(sc.next());
								System.out.println("������ �Ϸ��Ͽ����ϴ�. ");
								idMap.put(r.getId(),user);
								pwMap.put(r.getPw(),user);
							}
						}
					}
				}
				else if((u.getGrade()).equals("�Ϲ�")){
					while(true) {
						System.out.println("1.    id/pw ����\n2.    �̸� ����\n3.    �ּ� ����");
						System.out.print("����>>");
						sel = numException();
						if(sel!=1 && sel!=2 && sel!=3)	System.out.println("1~3�߿��� �Է�.");
						else break;
					}
					if(sel==1) {
						u.setId(sameId());
						System.out.print("Password �Է� : ");
						u.setPw(sc.next());
					}
					else if(sel==2) {
						System.out.print("�̸� �Է� : ");
						u.setName(sc.next());
					}
					else if(sel==3) {
						System.out.print("�ּ� �Է� : ");
						u.setAdress(sc.next());
					}
					System.out.println("������ �Ϸ��Ͽ����ϴ�. ");
					idMap.put(u.getId(),user);
					pwMap.put(u.getPw(),user);
				}
			}
		}
		saveData();
	}
	
	
	String choiceID;
	int select;
	public void removeMember() throws IOException {
		login();
		User find = idMap.get(id);
		if((find.getGrade()).equals("������")) removeAdmin();
		else if((find.getGrade()).equals("�Ϲ�")) removeGeneral();
		idMap.put(user.getId(),user);
		pwMap.put(user.getPw(),user);
	}
	
	public void removeAdmin() throws IOException {
		System.out.println("Ż���ų id �Է�");
		choiceID = sc.next();
		
		User u = idMap.get(choiceID);
		
		if (u != null) {
			if ((u.getId()).equals("root"))
				System.out.println("�⺻�����ڴ� ������ �� �����ϴ�.");
			else {
					list.remove(u);
					idMap.remove(u.getId());
					pwMap.remove(u.getPw());
					saveData();
				System.out.println(u.getName() + " ȸ������ Ż����׽��ϴ�. ");
			}
		} else
			System.out.println("���� id�Դϴ�.");
	}
	
	public void removeGeneral() throws IOException {
		while(true) {
			System.out.println("Ż���Ͻðڽ��ϱ�?? (1.��   2.�ƴϿ�)");
			System.out.print("����>>");
			select = numException();
			if(select!=1&&select!=2) System.out.println("�ٽ� �Է��ϼ���.");
			else break;
		}
		if(select==1) {
			User u = idMap.get(user.getId());
			list.remove(u);
			idMap.remove(u.getId());
			pwMap.remove(u.getPw());
			saveData();
			System.out.println("Ż���ϼ̽��ϴ�.");
		}
		else if(select==2) {
			System.out.println("�޴��� ���ư��ϴ�.");
		}
	}
	
	public void printData() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		try {
			fin = new FileInputStream("c:\\dev\\����\\login.txt");
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(fin);
			list = (ArrayList<User>)oin.readObject();
			System.out.println("�̸�      ID   �ּ�          ���");
			System.out.println("-----------------------");
			for(User u:list) {
				System.out.println(u.toString());
			}
		} catch (Exception e) {
			System.out.println("������ �ҷ��� �� �����ϴ�.");
		}finally {
			try {
				fin.close();
				bin.close();
				oin.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}
		}
	}
	
	public int numException() {
		int number=0;
		while(true) {
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.print("���ڸ� �Է��ϼ��� >>");
			}
		}
		return number;
	}
}