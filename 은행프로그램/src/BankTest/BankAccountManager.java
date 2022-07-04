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
		System.out.print("생성할 계좌 입력 : ");
		account = mBankScan.inputString();
		System.out.print("계좌주 입력 : ");
		name = mBankScan.inputString();
		System.out.print("계좌 잔액 입력 : ");
		balance = mBankScan.inputNumber();
		
		if(addAccount(account, name, balance, mBankScan))
			System.out.println("계좌를 생성하였습니다.");
		else 
			System.out.println("중복된 계좌입니다.");	
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
		System.out.println("==============계좌 목록==============");
		for(BankAccount ba : mBankAccounts) {
			System.out.println(number + "." + ba.toString());
			number++;
		}
		System.out.println("=====================================");
		System.out.print("삭제할 계좌를 선택하세요 : ");
		int index = 0;
		index = mBankScan.inputNumber();
		if((index - 1) <= mBankAccounts.size() && (index - 1) >= 0){
			mBankAccounts.remove(index-1);
			System.out.println(index + "번 계좌를 삭제하였습니다.");
		}else
			System.out.println("존재하지 않는 목록입니다.");
	}
	
	public void selectAccount() {
		int number = 1;
		System.out.println("==============계좌 목록==============");
		for(BankAccount ba : mBankAccounts) {
			System.out.println(number + "." + ba.toString());
			number++;
		}
		System.out.println("=====================================");
		System.out.print("계좌를 선택하세요 : ");
		int index = 0;
		index = mBankScan.inputNumber();
		if((index - 1) <= mBankAccounts.size() && (index - 1) >= 0){
			start(index);
		}else
			System.out.println("존재하지 않는 목록입니다.");
	}
	
	public void menu() {
		System.out.println("===================");
		System.out.println("1. 계좌 확인\n2. 잔액확인\n3. 입금\n4. 출금\n5. 종료");
		System.out.println("===================");
	}
	
	public void start(int index) {
		int i = 0;
		int indexNumber = index -1;
		while(i!=5) {
			menu();
			System.out.print("번호 입력: ");
			i = mBankScan.inputNumber();
			if(i == 1)
				System.out.println(mBankAccounts.get(indexNumber));
			else if(i == 2)	
				System.out.println("잔액 : " + connectToDecimalFormat(mBankAccounts.get(indexNumber).getmBalance()));
			else if(i == 3) mBankAccounts.get(indexNumber).deposit();
			else if(i == 4) mBankAccounts.get(indexNumber).withdraw();
			else if(i == 5) break;
		}
		System.out.println("종료합니다.");
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