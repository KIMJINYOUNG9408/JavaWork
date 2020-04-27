package practice.isogram;

import java.util.Arrays;
import java.util.Scanner;

/*	Isogram
	 문자열을 입력받으면 isogram 여부를 판단하여 true/false 를 출력하다가, quit 가 입력되면 종료
	isogram 이란?  : 중복된 알파벳이 없는 단어
	
	 isogram 예) Machine, isogram, Alphabet, quit
 */
public class Isogram {

	// TODO : 필요한 메소드 있으면 추가 작성

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String line;
		while (true) {
			boolean a = true;
			line = sc.nextLine();
			line = line.toUpperCase();
			if (line.trim().equalsIgnoreCase("quit"))
				break;
			for (int i = 0; i < line.length() - 1; i++) {
				for (int j = i + 1; j < line.length(); j++) {
					if (line.charAt(i) == line.charAt(j)) {
						a = false;
					}

				}
			}

			System.out.println(a);
		}

		sc.close();
	} // end main()
} // end class
