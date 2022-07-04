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
				System.out.print("숫자를 입력해 주세요: ");
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