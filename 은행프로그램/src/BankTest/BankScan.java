package BankTest;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BankScan {
	Scanner sc = new Scanner(System.in);
	
	public int inputNumber() {
		int number = 0;
		while(true) {
			try {
				number = sc.nextInt();
				break;
			} catch (InputMismatchException e) {
				sc = new Scanner(System.in);
				System.out.print("���ڸ� �Է��� �ּ���: ");
			}
		}
		return number;
	}
	
	public String inputString() {
		String sentence = "";
		sentence = sc.next();
		return sentence;
	}
}