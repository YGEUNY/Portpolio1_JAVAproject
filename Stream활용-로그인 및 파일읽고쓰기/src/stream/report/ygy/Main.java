package stream.report.ygy;

import java.util.*;
import java.io.*;

public class Main {

	Scanner sc = new Scanner(System.in);
	ArrayList<Student> list = new ArrayList<Student>();
	Student student = null;
	
	public static void main(String[] args) throws IOException {
		Main run = new Main();
		run.menu();
	}
	
	public void menu() throws IOException {
		while(true) {
			System.out.println("===================");
			System.out.println("1. 데이터 입력\n2. 화일로 저장\n3. 리스트 보기\n4. 화일 로딩\n5. 종료");
			System.out.println("===================");
			System.out.print("입력>>");
			
			int choice = numException();
			
			if(choice==1) inputData(); 
			else if(choice==2)	saveData();
			else if(choice==3)	printList();
			else if(choice==4)	loadData();
			else if(choice==5)	break;
		}
		System.out.println("프로그램을 종료합니다.");
	}
	
	public void inputData() {
		student = new Student();
		System.out.print("학생 id 입력>>");
		student.setId(numException());
		System.out.print("학생 이름 입력>>");
		student.setName(sc.next());
		System.out.print("학생 성적 입력>>");
		double inputScore;
		while(true) {
			inputScore = doubleException();
			if(inputScore<0 || inputScore>100)	System.out.println("1~100점 사이 점수 입력.");
			else break;
		}
		student.setScore(inputScore);
		list.add(student);		
	}
	
	public void saveData() throws IOException {
		FileOutputStream fout=null;
		BufferedOutputStream bout = null;
		ObjectOutputStream oout=null;
		try {
			fout = new FileOutputStream("c:\\dev\\과제\\1701173.txt");
			bout = new BufferedOutputStream(fout);
			oout = new ObjectOutputStream(fout);
			oout.writeObject(list);
			oout.reset();
			System.out.println("저장완료 !!");
		} catch (IOException e) {
			System.out.println("c:\\dev\\과제\\1701173.txt에 저장할 수 없었습니다. 경로명을 확인해 주세");
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
	
	public void printList() {
		System.out.println("id  이름      성적");
		System.out.println("------------------");
		for(Student s:list) {
			System.out.println(s.toString());
		}
	}
	
	public void loadData() {
		FileInputStream fin = null;
		BufferedInputStream bin = null;
		ObjectInputStream oin = null;
		
		try {
			fin = new FileInputStream("c:\\dev\\과제\\1701173.txt");
			bin = new BufferedInputStream(fin);
			oin = new ObjectInputStream(fin);
			list = (ArrayList<Student>)oin.readObject();
			System.out.println("id  이름      성적");
			System.out.println("------------------");
			for(Student s:list) {
				System.out.println(s.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			try {
				fin.close();
				bin.close();
				oin.close();
			} catch (IOException e2) {
				System.out.println(e2);
			}
		}
	//	ArrayList<Student> list = (ArrayList)oin.readObject();
	}
	
	
	public int numException() {
		int num;
		while(true) {
			try {
				num=sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc=new Scanner(System.in);
				System.out.print("다시 입력하세요>>");
			}
		}
		return num;
	}
	
	
	public double doubleException() {
		double num;
		while(true) {
			try {
				num=sc.nextDouble();
				break;
			} catch (InputMismatchException e) {
				sc=new Scanner(System.in);
				System.out.print("다시 입력하세요>>");
			}
		}
		return num;
	}
}


