package BankTest;

import java.text.DecimalFormat;
import java.util.ArrayList;
//import java.util.Dictionary;

public class BankAccountManager {
	private ArrayList<BankAccount> mBankAccounts;
	private BankScan mBankScan;
	
	public BankAccountManager(BankScan bs) {
		mBankAccounts = new ArrayList<BankAccount>();
		mBankScan = bs;
		//mBankDic = new Hashtable();
	}
	
	public void makeAccount() {
		String account, name;
		int balance = 0;
		System.out.print("������ ���� �Է� : ");
		account = mBankScan.inputString();
		System.out.print("������ �Է� : ");
		name = mBankScan.inputString();
		System.out.print("���� �ܾ� �Է� : ");
		balance = mBankScan.inputNumber();
		
		if(addAccount(account, name, balance, mBankScan))
			System.out.println("���¸� �����Ͽ����ϴ�.");
		else 
			System.out.println("�ߺ��� �����Դϴ�.");	
	}
	
	public boolean addAccount(String account, String name, int balance, BankScan bs) {
		for(BankAccount ba : mBankAccounts) {
			if(ba.getmAccount().equals(account)) {
				System.out.println(" error message");
				return false;
			}
		}
		BankAccount ba = new BankAccount(account, name, balance, bs);
		mBankAccounts.add(ba);
		return true;
	}
	
	public void removeAccount() {
		int number = 1;
		System.out.println("==============���� ���==============");
		for(BankAccount ba : mBankAccounts) {
			System.out.println(number + "." + ba.toString());
			number++;
		}
		System.out.println("=====================================");
		System.out.print("������ ���¸� �����ϼ��� : ");
		int index = 0;
		index = mBankScan.inputNumber();
		if((index - 1) <= mBankAccounts.size() && (index - 1) >= 0){
			mBankAccounts.remove(index-1);
			System.out.println(index + "�� ���¸� �����Ͽ����ϴ�.");
		}else
			System.out.println("�������� �ʴ� ����Դϴ�.");
	}
	
	public void selectAccount() {
		int number = 1;
		System.out.println("==============���� ���==============");
		for(BankAccount ba : mBankAccounts) {
			System.out.println(number + "." + ba.toString());
			number++;
		}
		System.out.println("=====================================");
		System.out.print("���¸� �����ϼ��� : ");
		int index = 0;
		index = mBankScan.inputNumber();
		if((index - 1) <= mBankAccounts.size() && (index - 1) >= 0){
			start(index);
		}else
			System.out.println("�������� �ʴ� ����Դϴ�.");
	}
	
	public void menu() {
		System.out.println("===================");
		System.out.println("1. ���� Ȯ��\n2. �ܾ�Ȯ��\n3. �Ա�\n4. ���\n5. ����");
		System.out.println("===================");
	}
	
	public void start(int index) {
		int i = 0;
		int indexNumber = index -1;
		while(i!=5) {
			menu();
			System.out.print("��ȣ �Է�: ");
			i = mBankScan.inputNumber();
			if(i == 1)
				System.out.println(mBankAccounts.get(indexNumber));
			else if(i == 2)	
				System.out.println("�ܾ� : " + connectToDecimalFormat(mBankAccounts.get(indexNumber).getmBalance()));
			else if(i == 3) mBankAccounts.get(indexNumber).deposit();
			else if(i == 4) mBankAccounts.get(indexNumber).withdraw();
			else if(i == 5) break;
		}
		System.out.println("�����մϴ�.");
	}
	
	private String connectToDecimalFormat(int getmBalance) {
		// TODO Auto-generated method stub
		DecimalFormat format = new DecimalFormat("###,###");
		return format.format(getmBalance);
	}	
	
	public BankAccount getAccount(String account) {
		return null;
	}
}