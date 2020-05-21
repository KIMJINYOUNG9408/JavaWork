package cal;

import java.util.Scanner;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		Scanner scanner = new Scanner(System.in);
		String in;

		while (true) {
			System.out.print(">>> ");

			in = scanner.nextLine().trim();

			if (in.equals("q"))
				break;

			calculator.run(in);
			calculator.printStatus(); // 출력
		}
		;
		System.out.println("계산 종료");
		scanner.close();
	}
}
