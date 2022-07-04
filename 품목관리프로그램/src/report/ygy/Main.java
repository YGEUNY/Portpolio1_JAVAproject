package report.ygy;


import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	Scanner sc = new Scanner(System.in);
	Product arr[] = new Product[10];
	int num =0;
	int idNum=0;
	
	
	
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
		
		num++;
		idNum++;
	}
	
	public void inputCPU() {
		arr[num] = new CPU();
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
		((CPU)arr[num]).setSpeed(inputSpeed);
		System.out.print("�ɼ� �Է�>>");
		((CPU)arr[num]).setInch(numException());
	}
	
	public void inputRefrigerator() {
		arr[num] = new Refrigerator();
		same();
		System.out.print("�뷮 �Է�>>");
		((Refrigerator)arr[num]).setLiter(numException());
		System.out.print("Ÿ�� �Է�>>");
		((Refrigerator)arr[num]).setType(sc.next());
	}
	
	public void inputAudio() {
		arr[num] = new Audio();
		same();
		System.out.print("������� �Է�>>");
		((Audio)arr[num]).setOutPut(numException());
		System.out.print("Ʃ���������� �Է�( 1.���� 2.����x )>>");
		((Audio)arr[num]).setTuner(inputTuner());
	}
	
	public void same() {
		arr[num].setId(idNum+1);
		System.out.print("��ǰ�� �Է�>>");
		arr[num].setModel(sc.next());
		System.out.print("������ �Է�>>");
		arr[num].setCompany(sc.next());
		System.out.print("�԰�¥ �Է�( �� : 20170401)>>");
		arr[num].setDate(inputDate());
		System.out.print("������ �Է�>>");
		arr[num].setNumOfStock(numException());
		System.out.print("���� �Է�>>");
		arr[num].setPrice(numException());
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
		if(choice==1)	printDateList();
		else if(choice==2)	printModelList();
		else if(choice==3)	printModelReverseList();
		else if(choice==4)	printPriceList();
		else if(choice==5)	printPriceReverseList();
	}

	
	
	public void printDateList() {	//��¥�� ����
		ArrayList<Product> dateList = new ArrayList<Product>();
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		for(int i=0; i<num;i++) {
			dateList.add(arr[i]);
		}
		Collections.sort(dateList);
		
		for(int i=0;i<num;i++) {
			System.out.println(dateList.get(i).toString());
		}
	}
	
	public void printModelList() {		//�𵨸� ������
		ArrayList<Product> modelList = new ArrayList<Product>();
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		modelCompare modelSort = new modelCompare();
		for(int i=0; i<num;i++) {
			modelList.add(arr[i]);
		}
		Collections.sort(modelList,modelSort);		
		
		for(int i=0;i<num;i++) {
			System.out.println(modelList.get(i).toString());
		}
	}
	
	
	public void printModelReverseList() {	//�𵨸� ����
		ArrayList<Product> rModelList = new ArrayList<Product>();
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		modelReverseCompare rModelSort = new modelReverseCompare();
		for(int i=0; i<num;i++) {
			rModelList.add(arr[i]);
		}
		Collections.sort(rModelList,rModelSort);
		for(int i=0;i<num;i++) {
			System.out.println(rModelList.get(i).toString());
		}
	}
	
	
	public void printPriceList() {	// ���� ����������
		ArrayList<Product> priceList = new ArrayList<Product>();
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");		
		
		priceCompare priceSort = new priceCompare();
		for(int i=0; i<num;i++) {
			priceList.add(arr[i]);
		}
		Collections.sort(priceList,priceSort);
		for(int i=0;i<num;i++) {
			System.out.println(priceList.get(i).toString());
		}
	}
	
	
	
	
	public void printPriceReverseList() {	//���� ��������		
		ArrayList<Product> rPriceList = new ArrayList<Product>();
		System.out.println("��ȣ   ��ǰ��   ������   �԰�¥   ������  ����   ��Ÿ����  ");
		priceReverseCompare rPriceSort = new priceReverseCompare();
		for(int i=0; i<num;i++) {
			 rPriceList.add(arr[i]);
		}
		Collections.sort( rPriceList, rPriceSort);
		for(int i=0;i<num;i++) {
			System.out.println( rPriceList.get(i).toString());
		}
	}
	
	
	public void printStockList() {
		ArrayList<Product> stockList= new ArrayList<Product>();
		priceReverseCompare stockSort = new priceReverseCompare();
		for(int i=0; i<num;i++) {
			 stockList.add(arr[i]);
		}
		Collections.sort( stockList, stockSort);
		for(int i=0;i<num;i++) {
			System.out.println( stockList.get(i).toString());}
		
	}
	
	
	public void changeOfStock() {
		printStockList();
		int changeStock =0;
		while(true) {
			System.out.print("��ǰ��ȣ ����>>");
			changeStock = numException();
			if(changeStock<1 || changeStock >num+1)	System.out.println("�������� �ʴ� ����Ʈ�Դϴ�.");
			else break;
		}
		for(int i=0;i<num;i++) {
			if(changeStock == arr[i].id) {
				System.out.print("���� ������ " +arr[i].numOfStock+" �Դϴ�. ���ο� ������? ");
				arr[i].setNumOfStock(numException());
			}
		}
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
		System.out.println("�ϰ� �ø� ��� ������ ?");
		changeAllPrice = numException();
		for(int i=0;i<num;i++) {
			arr[i].setPrice(arr[i].price+changeAllPrice);
		}
	}
	
	public void changeAllPrice_two() {
		System.out.println("�ϰ� ���� ���� ������ ?");
		changeAllPrice = numException();
		for(int i=0;i<num;i++) {
			if(arr[i].price<=changeAllPrice)	arr[i].setPrice(0);
			else	arr[i].setPrice(arr[i].price-changeAllPrice);
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
		 ArrayList<Product> searchList = new ArrayList<Product>();
		 priceCompare priceSort = new priceCompare();
		 for(int i=0;i<num;i++) {
				if(start<=arr[i].price && end>=arr[i].price) {
					searchList.add(arr[i]);
				}
				else ;
			}	
		 Collections.sort(searchList,priceSort);
			for(Product p:searchList) {
				System.out.println(p.toString());
			}
	}
}