package BankTest;

/////////////// ctrl + Shift + F 누르면 줄 정리 //////////

public class Bank {
	public static void main(String[] args) {
		Bank ba = new Bank();
		ba.start();
	}

	public void menu() {
		System.out.println("===================");
		System.out.println("1. 계좌 생성\n2. 계좌 삭제\n3. 계좌 선택\n4. 종료");
		System.out.println("===================");
	}

	public void start() {
		int i = 0;
		BankScan bs = new BankScan();
		BankAccountManager bm = new BankAccountManager(bs);
		while (i != 4) {
			menu();
			System.out.print("번호 입력: ");
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
		System.out.println("종료합니다.");
	}
}
