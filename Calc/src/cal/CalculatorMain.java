package cal;

import java.util.Scanner;

public class CalculatorMain {

	public static void main(String[] args) {
		Calculator calculator = new Calculator(); // calculator 객체를 만든다.
		Scanner scanner = new Scanner(System.in); // 입력을 위한 scanner 객체를 만든다.
		String in; // 입력데이터를 저장할 참조변수를 선언한다

		while (true) { // 무한루프
			System.out.print(">>> ");

			in = scanner.nextLine().trim(); // trim은 좌우 공백을 제거하기위함

			if (in.equals("q")) // 입력이 q가 되면 종료.
				break;

			calculator.run(in); // run 메소드에게 입력을 전달한다.

		}

		System.out.println("계산 종료");
		scanner.close(); // scanner 객체를 닫는다.
	}
}
