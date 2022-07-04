package BankTest;


import java.text.DecimalFormat;

public class BankAccount {
	private String mAccount = "", mAccountName = "";
	private int mBalance =0;
	private int balance = 0;
	private BankScan mBankScan;
	
	public BankAccount(String account, String name, int balance, BankScan bs) {
		mAccount = account;
		mAccountName = name;
		mBalance = balance;
		mBankScan = bs;
	}
	
	public void deposit() {
		System.out.print("입금할 금액 : ");
		balance = mBankScan.inputNumber();
		
		while(ifNegativeAmount(balance) == false) 
			balance = mBankScan.inputNumber();
	
		System.out.println(convertToDecimalFormat(balance) + "원 입금합니다.");
		mBalance = mBalance + balance;
	}

	public void withdraw() {
		System.out.print("출금할 금액 : ");
		balance = mBankScan.inputNumber();
		
		while(withdrawError(mBalance, balance) != true  || ifNegativeAmount(balance) != true)
			balance = mBankScan.inputNumber();
			
		System.out.println(convertToDecimalFormat(balance) + "원 출금합니다.");
		mBalance = mBalance - balance;
	}
	
	public String convertToDecimalFormat(int amount) {
		DecimalFormat format = new DecimalFormat("###,###");
		return format.format(amount);
	}
	
	public boolean withdrawError(int mBalance, int balance) {
		if(balance > mBalance) {
			System.out.print("잔액이 부족합니다. 다시 입력하세요 : ");
			return false;
		}
		else
			return true;	
	}
	
	public boolean ifNegativeAmount(int amount) {
		if(amount < 0) {
			System.out.print("올바른 금액을 입력하세요 : ");
			return false;
		}
		else
			return true;	
	}

	public String getmAccount() {
		return mAccount;
	}

	public void setmAccount(String mAccount) {
		this.mAccount = mAccount;
	}	
	
	public int getmBalance() {
		return mBalance;
	}

	public void setmBalance(int mBalance) {
		this.mBalance = mBalance;
	}

	@Override
	public String toString() {
		return "\t계좌번호:" + mAccount + "\t 계좌주: " + mAccountName + "\t 잔액: " + mBalance;
	}
}
