package com.lec.java.static02;

public class Test {
	int num; // 인스턴스 변수
	static int sNum; // 클래스 변수

	public void show() { // 인스턴스 메소드
		System.out.println("인스턴스 num = " + num);
		System.out.println("클래스(static) sNum = " + sNum);
	}

	public static void show2() { // 클래스 메소드
		int num3 = 10;
		//System.out.println("인스턴스 num = " + num);
		System.out.println("클래스(static) sNum = " + sNum);
		
	} 

}
