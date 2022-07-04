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
		System.out.print("�Ա��� �ݾ� : ");
		balance = mBankScan.inputNumber();
		
		while(ifNegativeAmount(balance) == false) 
			balance = mBankScan.inputNumber();
	
		System.out.println(convertToDecimalFormat(balance) + "�� �Ա��մϴ�.");
		mBalance = mBalance + balance;
	}

	public void withdraw() {
		System.out.print("����� �ݾ� : ");
		balance = mBankScan.inputNumber();
		
		while(withdrawError(mBalance, balance) != true  || ifNegativeAmount(balance) != true)
			balance = mBankScan.inputNumber();
			
		System.out.println(convertToDecimalFormat(balance) + "�� ����մϴ�.");
		mBalance = mBalance - balance;
	}
	
	public String convertToDecimalFormat(int amount) {
		DecimalFormat format = new DecimalFormat("###,###");
		return format.format(amount);
	}
	
	public boolean withdrawError(int mBalance, int balance) {
		if(balance > mBalance) {
			System.out.print("�ܾ��� �����մϴ�. �ٽ� �Է��ϼ��� : ");
			return false;
		}
		else
			return true;	
	}
	
	public boolean ifNegativeAmount(int amount) {
		if(amount < 0) {
			System.out.print("�ùٸ� �ݾ��� �Է��ϼ��� : ");
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
		return "\t���¹�ȣ:" + mAccount + "\t ������: " + mAccountName + "\t �ܾ�: " + mBalance;
	}
}
