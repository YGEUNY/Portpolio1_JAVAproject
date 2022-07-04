package BankTest;

/////////////// ctrl + Shift + F ������ �� ���� //////////

public class Bank {
	public static void main(String[] args) {
		Bank ba = new Bank();
		ba.start();
	}

	public void menu() {
		System.out.println("===================");
		System.out.println("1. ���� ����\n2. ���� ����\n3. ���� ����\n4. ����");
		System.out.println("===================");
	}

	public void start() {
		int i = 0;
		BankScan bs = new BankScan();
		BankAccountManager bm = new BankAccountManager(bs);
		while (i != 4) {
			menu();
			System.out.print("��ȣ �Է�: ");
			i = bs.inputNumber();
			if (i == 1)
				bm.makeAccount();
			else if (i == 2)
				bm.removeAccount();
			else if (i == 3)
				bm.selectAccount();
			else if (i == 4)
				break;
		}
		System.out.println("�����մϴ�.");
	}
}
