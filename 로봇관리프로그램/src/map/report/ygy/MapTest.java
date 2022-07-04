package map.report.ygy;

import java.util.Collections;

import java.util.InputMismatchException;

import java.util.Scanner;

import java.util.TreeMap;


public class MapTest {

	Scanner sc = new Scanner(System.in);
	TreeMap<String, Student> map = new TreeMap<String, Student>();
	TreeMap<Integer,Student> sorted_map = new TreeMap<Integer, Student>(Collections.reverseOrder());
	
	public static void main(String[] args) {
		MapTest run = new MapTest();
		run.menu();
	}

	public void menu() {
		while(true) {
			System.out.println("1. 입력\n2. 삭제\n3. 목록보기\n4. 검색\n5. 종료");
			System.out.print("선택 :");
			int choice = numException();
			if(choice==1)	inputData();
			else if(choice==2)	deletData();
			else if(choice==3)	printData();
			else if(choice==4)	searchData();
			else if(choice==5)	break;
			else	System.out.println("다시 입력하세요.");
		}
		System.out.println("종료합니다.");
	}

	
	int id;
	String name;
	String tel;
	
	public void inputData() {
		System.out.print("id입력>>");
		id = numException();
		System.out.print("이름 입력 >>");
		name = sc.next();
		System.out.println("전화번호 입력>>");
		tel = sc.next();
		
		Student s = new Student(id, name, tel);
		map.put(name, s);
		
	}

	

	public void deletData() {
		
		

	}

	

	public void printData() {
		System.out.println("<<Id 역순정렬>>");
		sorted_map.putAll(map);
		System.out.println(sorted_map);
	}

	

	public void searchData() {

		System.out.print("검색할 이름?");
		String name = sc.next();

		Student student = map.get(name);

		if(student ==null)

			System.out.println(name+"은 없는 사람입니다.");

		else

			System.out.println("id: "+student.getId()+", 이름: "+student.getName()+", 전화: "+student.getTel());

	}

	

	public int numException() {

		int num;

		while(true) {

			try {

				num = sc.nextInt();

				break;

			} catch (InputMismatchException e) {

				sc = new Scanner(System.in);

				System.out.println("숫자를 입력하세요.");

			}

		}

		return num;

	}
}

 

class Student{

	int id;
	String name;
	String tel;

	public Student(int id, String name, String tel) {
		this.id = id;
		this.name = name;
		this.tel = tel;
	}

	public int getId() {

		return id;

	}

	public void setId(int id) {

		this.id = id;

	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public String getTel() {

		return tel;

	}

	public void setTel(String tel) {

		this.tel = tel;

	}

	

}

