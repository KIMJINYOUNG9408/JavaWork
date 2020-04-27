package com.lec.java.while02;

public class While02Main {

	public static void main(String[] args) {
		System.out.println("while 연습");

		// 구구단 2단
		// while문 사용

		int i = 1; // 초기식
		while (i <= 9) { // 조건식
			System.out.println("2 * " + i + " = " + (2 * i));
			i++; // 증감식
		}

	} // end main()

} // end class While02Main
