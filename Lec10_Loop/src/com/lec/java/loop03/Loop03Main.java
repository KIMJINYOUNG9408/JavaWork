package com.lec.java.loop03;

public class Loop03Main {

	public static void main(String[] args) {
		System.out.println("중첩 for 문 nested for");

		// 구구단 출력 : 중첩 for 사용
		for (int dan = 2; dan <= 9; dan++) {
			for (int j = 1; j <= 9; j++) {
				System.out.println(dan + " * " + j + " = " + (dan * j));
			}
			System.out.println();
		}

		System.out.println();
		// 구구단 출력 : 중첩 while 문 사용
		int dan = 2, j = 1;
		while (dan <= 9) {

			while (j <= 9) {
				System.out.println(dan + " * " + j + " = " + (dan * j));
				j++;

			}
			j = 1;
			System.out.println();
			dan++;
		}

		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
