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
			System.out.println("0. 로그인\n1. 신규회원가입\n2. 회원정보 수정\n3. 회원 탈퇴\n4. 회원정보 보기\n5. 종료");
			System.out.println("=================================");
			System.out.print("입력>>");
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
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void firstAdmin() throws IOException {
		user = new Administrator();
		((Administrator)user).setId("root");
		((Administrator)user).setPw("root");
		((Administrator)user).setAdress("   ");
		((Administrator)user).setGrade("관리자");
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
			if(u==null)	System.out.println("없는 id입니다.");
			else break;
		}
		while(true) {
			System.out.print("Password : ");
			String pw = sc.next();
			
			User u = pwMap.get(pw);
			if(u==null)	System.out.println("없는 pw입니다.");
			else break;
		}
		System.out.println("로그인이 완료되었습니다.");
	}
	

	
	public void join() throws IOException {
		int choice;
		while(true) {
			System.out.println("1.일반유저   2. 관리자");
			System.out.print("선택>>");
			choice = numException();
			if(choice!=1 && choice!=2)	System.out.println("1, 2 중에 선택하세요.");
			else break;
		}
		if(choice==1) {
			user = new GeneralMember();
			System.out.print("이름 입력 : ");
			((GeneralMember)user).setName(sc.next());
			((GeneralMember)user).setId(sameId());
			System.out.print("Password 입력 : ");
			((GeneralMember)user).setPw(sc.next());
			System.out.print("주소 입력 : ");
			((GeneralMember)user).setAdress(sc.next());
			((GeneralMember)user).setGrade("일반");
		}
		else if(choice==2) {
			user = new Administrator();
			System.out.print("이름 입력 : ");
			((Administrator)user).setName(sc.next());
			((Administrator)user).setId(sameId());
			System.out.print("Password 입력 : ");
			((Administrator)user).setPw(sc.next());
			System.out.print("주소 입력 : ");
			((Administrator)user).setAdress(sc.next());
			((Administrator)user).setGrade("관리자");
			
		}
		System.out.println("회원가입이 완료되었습니다.");
		list.add(user);
		idMap.put(user.getId(),user);
		pwMap.put(user.getPw(),user);
		saveData();
	}
	
	public String sameId() {
		String inputId;
		while(true) {
			System.out.print("ID 입력 : ");
			inputId = sc.next();
			
			User u = idMap.get(inputId);
			if(u==null)	return inputId;
			else System.out.println("중복된 ID 입니다.");
		}
	}
	
	public void saveData() throws IOException {
		FileOutputStream fout=null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout=null;
		try {
			fout = new FileOutputStream("c:\\dev\\과제\\login.txt");
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(list);
			oout.reset();
	//		System.out.println("회원정보 저장완료 !!");
		} catch (Exception e) {
			System.out.println("c:\\dev\\과제\\login.txt에 저장할 수 없었습니다. 경로명을 확인해 주세요");
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
				if((u.getGrade()).equals("관리자")){
					while(true) {
						System.out.println("1.    id/pw 수정\n2.    이름 수정\n3.    주소 수정");
						System.out.print("선택>>");
						sel = numException();
						if(sel!=1 && sel!=2 && sel!=3)	System.out.println("1~3중에서 입력.");
						else break;
					}
					if(sel==1) {
						System.out.print("수정할 id 입력>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								if(r.getName().equals("root"))	System.out.println("기본관리자는 수정할 수 없습니다.");
								else {
									r.setId(sameId());
									System.out.print("Password 입력 : ");
									r.setPw(sc.next());
									idMap.put(r.getId(),user);
									pwMap.put(r.getPw(),user);
								}
								System.out.println("수정을 완료하였습니다. ");
							}
						}
					}
					else if(sel==2) {
						System.out.print("수정할 이름 입력>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								if(r.getName().equals("Admin"))	System.out.println("기본관리자는 수정할 수 없습니다.");
								else {
									System.out.print("이름 입력 : ");
									r.setName(sc.next());
									idMap.put(r.getId(),user);
									pwMap.put(r.getPw(),user);
								}
								System.out.println("수정을 완료하였습니다. ");
							}
						}
					}
					else if(sel==3) {
						System.out.print("수정할 이름 입력>>");
						choice = sc.next();
						
						for(User r:list) {
							if(r.getName().equals(choice)) {
								System.out.print("주소 입력 : ");
								u.setAdress(sc.next());
								System.out.println("수정을 완료하였습니다. ");
								idMap.put(r.getId(),user);
								pwMap.put(r.getPw(),user);
							}
						}
					}
				}
				else if((u.getGrade()).equals("일반")){
					while(true) {
						System.out.println("1.    id/pw 수정\n2.    이름 수정\n3.    주소 수정");
						System.out.print("선택>>");
						sel = numException();
						if(sel!=1 && sel!=2 && sel!=3)	System.out.println("1~3중에서 입력.");
						else break;
					}
					if(sel==1) {
						u.setId(sameId());
						System.out.print("Password 입력 : ");
						u.setPw(sc.next());
					}
					else if(sel==2) {
						System.out.print("이름 입력 : ");
						u.setName(sc.next());
					}
					else if(sel==3) {
						System.out.print("주소 입력 : ");
						u.setAdress(sc.next());
					}
					System.out.println("수정을 완료하였습니다. ");
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
		if((find.getGrade()).equals("관리자")) removeAdmin();
		else if((find.getGrade()).equals("일반")) removeGeneral();
		idMap.put(user.getId(),user);
		pwMap.put(user.getPw(),user);
	}
	
	public void removeAdmin() throws IOException {
		System.out.println("탈퇴시킬 id 입력");
		choiceID = sc.next();
		
		User u = idMap.get(choiceID);
		
		if (u != null) {
			if ((u.getId()).equals("root"))
				System.out.println("기본관리자는 삭제할 수 없습니다.");
			else {
					list.remove(u);
					idMap.remove(u.getId());
					pwMap.remove(u.getPw());
					saveData();
				System.out.println(u.getName() + " 회원님을 탈퇴시켰습니다. ");
			}
		} else
			System.out.println("없는 id입니다.");
	}
	
	public void removeGeneral() throws IOException {
		while(true) {
			System.out.println("탈퇴하시겠습니까?? (1.예   2.아니오)");
			System.out.print("선택>>");
			select = numException();
			if(select!=1&&select!=2) System.out.println("다시 입력하세요.");
			else break;
		}
		if(select==1) {
			User u = idMap.get(user.getId());
			list.remove(u);
			idMap.remove(u.getId());
			pwMap.remove(u.getPw());
			saveData();
			System.out.println("탈퇴하셨습니다.");
		}
		else if(select==2) {
			System.out.println("메뉴로 돌아갑니다.");
		}
	}
	
	public void printData() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		try {
			fin = new FileInputStream("c:\\dev\\과제\\login.txt");
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(fin);
			list = (ArrayList<User>)oin.readObject();
			System.out.println("이름      ID   주소          등급");
			System.out.println("-----------------------");
			for(User u:list) {
				System.out.println(u.toString());
			}
		} catch (Exception e) {
			System.out.println("파일을 불러올 수 없습니다.");
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
				System.out.print("숫자를 입력하세요 >>");
			}
		}
		return number;
	}
}