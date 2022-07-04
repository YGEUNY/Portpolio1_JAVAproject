package arraylist.report.ygy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;


class modelCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {	
		return p1.getModel().compareTo(p2.getModel());
	}	
}

class modelReverseCompare implements Comparator<Product>{

	public int compare(Product p1, Product p2) {	
		return p2.getModel().compareTo(p1.getModel());
	}
}

class priceCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()<p2.getPrice())	return -1;
		else if(p1.getPrice()>p2.getPrice())	return 1;
		else	return 0;
	}
}

class priceReverseCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getPrice()>p2.getPrice())	return -1;
		else if(p1.getPrice()<p2.getPrice())	return 1;
		else	return 0;
	}
}

class stockCompare implements Comparator<Product>{
	public int compare(Product p1, Product p2) {
		if(p1.getNumOfStock()<p2.getNumOfStock())	return -1;
		else if(p1.getNumOfStock()>p2.getNumOfStock())	return 1;
		else return 0;
	}
}

class dateCompare implements Comparator<Product>{
		public int compare(Product o1, Product o2) {
		return o1.getDate()-o2.getDate();
	}
}

public class Main {
	ArrayList<Product> list = new ArrayList<Product>();
	Scanner sc = new Scanner(System.in);
	int num =0;
	int idNum=0;
	Product product = null;

	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void menu() {
		System.out.println("==================================");
		System.out.println("1. ��ǰ �������Է�\n2. ��ǰ����Ʈ ����\n3. ���� ��ǰ ������ ����\n4. ��� ��ǰ ���ݼ��� ����\n5. ���� ������ �˻�(���ݼ�)\n6. ����");
		System.out.println("==================================");
		System.out.print("�Է� >>");
	}
	
	public int numException() {
		int number;
		while(true) {
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("�ٽ� �Է��ϼ���>>");
			}
		}
		return number;
	}


	public void run() {
		int choice=0;
		while(true) {
			menu();
			choice = numException();
			if(choice ==1) {
				if(num<10)	inputData();
				else System.out.println("�Է��� �ִ�10�� ���� �� �� �ֽ��ϴ�.");
			}
			else if(choice ==2)	printList();
			else if(choice ==3) changeOfStock();
			else if(choice ==4)	changeAllOfStock();
			else if(choice ==5)	searchList();
			else if(choice ==6)	break;
		}
		System.out.println("���α׷��� �����մϴ�.");
	}
	
		
		
	public void inputData() {
		System.out.println("����� ���� �����ּ���.");
		String kind = null;
		while(true) {
			System.out.println("��ǰ������? [c: CPU , r: Refrigerator , a: Audio]");
			kind = sc.next();
			if(!kind.equals("c") &&!kind.equals("C") &&!kind.equals("r")&&!kind.equals("R")&&!kind.equals("a")&&!kind.equals("A"))
				System.out.println("c, r, a �߿��� �Է��ϼ���>>");
			else break;
		}
		
		if(kind.equals("c") ||kind.equals("C"))	inputCPU();
		else if(kind.equals("r") ||kind.equals("R"))	inputRefrigerator();
		else if(kind.equals("a") ||kind.equals("A"))	inputAudio();
		list.add(product);
		num++;
		idNum++;
	}
	
	public void inputCPU() {
		product = new CPU();
		same();
		System.out.print("�ӵ� �Է�>>");
		double inputSpeed=0;
		while(true) {
			try {
				inputSpeed = sc.nextDouble();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.println("�ùٸ� ���� �Է�>>");
			}
			
		}
		((CPU)product).setSpeed(inputSpeed);
		System.out.print("�ɼ� �Է�>>");
		((CPU)product).setInch(numException());
	}
	
	public void inputRefrigerator() {
		product = new Refrigerator();
		same();
		System.out.print("�뷮 �Է�>>");
		((Refrigerator)product).setLiter(numException());
		System.out.print("Ÿ�� �Է�>>");
		((Refrigerator)product).setType(sc.next());
	}
	
	public void inputAudio() {
		product = new Audio();
		same();
		System.out.print("������� �Է�>>");
		((Audio)product).setOutPut(numException());
		System.out.print("Ʃ���������� �Է�( 1.���� 2.����x )>>");
		((Audio)product).setTuner(inputTuner());
	}
	
	public void same() {
		product.setId(idNum+1);
		System.out.print("��ǰ�� �Է�>>");
		product.setModel(sc.next());
		System.out.print("������ �Է�>>");
		product.setCompany(sc.next());
		System.out.print("�԰�¥ �Է�( �� : 20170401)>>");
		product.setDate(inputDate());
		System.out.print("������ �Է�>>");
		product.setNumOfStock(numException());
		System.out.print("���� �Է�>>");
		product.setPrice(numException());
	}
	
	
	
	public int inputDate() {
		int mdate=0;
		while(true) {
			mdate = numException();
			if(((int)Math.log10(mdate)+1) != 8) {
				System.out.println("�ùٸ� �ڸ����� �Է��ϼ���.");
				System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
			}
			else {
				if(mdate%10000/100 < 1 || mdate%10000/100 > 12) {
					System.out.println("�ùٸ� ������ �Է��ϼ���.");
					System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
				}
				else {
					if(mdate%100 < 1 || mdate%100>31) {
						System.out.println("�ùٸ� ��¥�� �Է��ϼ���.");
						System.out.print("�԰�¥ �Է�>>( �� : 20170401)");
					}
					else break;
				}
			}
		}
		return mdate;
	}
	
	
	public String inputTuner() {
		System.out.println();
		String str = null;
		 int choose=0;
		 while(true) {
			 choose = numException();
			 if(choose !=1 && choose !=2)	System.out.print("1�� 2 �߿��� �Է�>>");
			 else break;
		 }
		 if(choose == 1)	str="����";
		 else if(choose ==2) str ="����x";
		 return str;
	}
	
	
	public void printList() {
		int choice=0;
		while(true) {
			System.out.println("(1) ��¥��(�⺻)   (2) ��ǰ��       (3) ��ǰ�� ����       (4) ���ݼ�     (5)���ݿ���");
			System.out.print("���� :  ");
			choice = numException();
			if(choice!=1 &&choice!=2 && choice!=3 && choice!=4 && choice!=5)	
				System.out.println("1~5 �߿��� �Է��ϼ���.");
			else break;			
		}
		choiceList(choice);
	}

	public void choiceList(int choice) {
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		dateCompare dateSort = new dateCompare();
		modelCompare modelSort = new modelCompare();
		modelReverseCompare modelReverseSort = new modelReverseCompare();
		priceCompare priceSort = new priceCompare();
		priceReverseCompare priceReverseSort = new priceReverseCompare();
		if(choice==1)	Collections.sort(list,dateSort);
		else if(choice==2)	Collections.sort(list,modelSort);
		else if(choice==3)	Collections.sort(list,modelReverseSort);
		else if(choice==4)	Collections.sort(list,priceSort);
		else if(choice==5)	Collections.sort(list,priceReverseSort);

		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
		
		for(Product p : list) {
			System.out.println(p.toString());
		}
	}
	
	public void stockList() {
		stockCompare stockSort = new stockCompare();
		Collections.sort(list,stockSort);
		 System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i).toString());
		}
	}
	
	public void changeOfStock() {
		stockList();
		int changeStock;
		while(true) {
			System.out.print("��ǰ��ȣ ����>>");
			changeStock = numException();
			if(changeStock<1 || changeStock >num+1)	System.out.println("�������� �ʴ� ����Ʈ�Դϴ�.");
			else break;
		}
		for(Product p:list)
		{
			if(p.getId()==changeStock)
			{
				System.out.print("���� ������ " +p.numOfStock+" �Դϴ�. ���ο� ������? ");
				p.setNumOfStock(numException());
			}
		}
		
//		for(int i=0;i<num;i++) {
//			if(changeStock == product.getId()) {
//				System.out.print("���� ������ " +product.numOfStock+" �Դϴ�. ���ο� ������? ");
//				product.setNumOfStock(numException());
//			}
//			else ;
//		}
	}
	
	public void changeAllOfStock() {
		int choose =0;
		while(true) {
			System.out.println(" 1.��ǰ ���� �ϰ� �ø�  2.��ǰ ���� �ϰ� ����  3.���"); 
			System.out.print("���� : ");
			choose = numException();
			if(choose==1)	 changeAllPrice_one();
			else if(choose==2)	 changeAllPrice_two();
			else if(choose==3) 	break;
		}
		
	}
	int changeAllPrice =0;
	public void changeAllPrice_one() {
		System.out.println("�ϰ� �ø� ���� ������ ?");
		changeAllPrice = numException();
		for(Product p:list) {
			p.setPrice(p.price+changeAllPrice);
		}
	}
	
	public void changeAllPrice_two() {
		System.out.println("�ϰ� ���� ���� ������ ?");
		changeAllPrice = numException();
		for(Product p:list) {
			if(p.price<=changeAllPrice)	p.setPrice(0);
			else	p.setPrice(p.price-changeAllPrice);
		}
	}
	
	public void searchList() {
		int start=0, end=0;
		 System.out.println("�˻� �� ������ �ּ� �ִ� ������ �Է��ϼ���(����1~����2 ���� �˻�).");
		 System.out.print("����1 �Է�>>");
		 start = numException();
		 System.out.print("����2 �Է�>>");
		 end =numException();
		 System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		 priceCompare priceSort = new priceCompare();
		 Collections.sort(list,priceSort);
		 for(Product p:list) {
				if(start<=p.price && end>=p.price) {
					 System.out.println(p);
				}
			}	 
	}
}