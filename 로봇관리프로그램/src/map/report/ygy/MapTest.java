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
			System.out.println("1. �Է�\n2. ����\n3. ��Ϻ���\n4. �˻�\n5. ����");
			System.out.print("���� :");
			int choice = numException();
			if(choice==1)	inputData();
			else if(choice==2)	deletData();
			else if(choice==3)	printData();
			else if(choice==4)	searchData();
			else if(choice==5)	break;
			else	System.out.println("�ٽ� �Է��ϼ���.");
		}
		System.out.println("�����մϴ�.");
	}

	
	int id;
	String name;
	String tel;
	
	public void inputData() {
		System.out.print("id�Է�>>");
		id = numException();
		System.out.print("�̸� �Է� >>");
		name = sc.next();
		System.out.println("��ȭ��ȣ �Է�>>");
		tel = sc.next();
		
		Student s = new Student(id, name, tel);
		map.put(name, s);
		
	}

	

	public void deletData() {
		
		

	}

	

	public void printData() {
		System.out.println("<<Id ��������>>");
		sorted_map.putAll(map);
		System.out.println(sorted_map);
	}

	

	public void searchData() {

		System.out.print("�˻��� �̸�?");
		String name = sc.next();

		Student student = map.get(name);

		if(student ==null)

			System.out.println(name+"�� ���� ����Դϴ�.");

		else

			System.out.println("id: "+student.getId()+", �̸�: "+student.getName()+", ��ȭ: "+student.getTel());

	}

	

	public int numException() {

		int num;

		while(true) {

			try {

				num = sc.nextInt();

				break;

			} catch (InputMismatchException e) {

				sc = new Scanner(System.in);

				System.out.println("���ڸ� �Է��ϼ���.");

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

