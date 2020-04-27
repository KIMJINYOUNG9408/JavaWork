package com.lec.java.for02;

public class For02Main {

	public static void main(String[] args) {
		System.out.println("For문 - 구구단 출력");
		
		System.out.println();
		
		for(int i = 1; i <= 9; i++) {
		   System.out.println("2 X " + i + " = " + (2 * i));	
		}
		
		System.out.println();
		
		for(int i = 1; i <= 9; i++) {
			System.out.println("4 X " + i + " = " + (4 * i));	
		}
		
		System.out.println();
		
		// 2 x 2 = 4 
		// 3 x 3 = 9
		// 9 x 9 = 81
		for(int i = 2; i <= 9; i++) {
			   System.out.println(i + " X " + i + " = " + (i * i));	
			}
	} // end main()

} // end class For02Main












