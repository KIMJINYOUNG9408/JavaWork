package com.lec.java.class06;

public class Class06Main {

	public static void main(String[] args) {
		System.out.println("클래스 연습 : 성적처리");
		
		Score st1 = new Score("김진영", 70, 80, 90);
		Score st2 = new Score("김진영", 20, 10, 30);
		Score st3 = new Score("김진영", 40, 40, 40);
		Score st4 = new Score("김진영", 100, 100, 100);
        
		int total; double avg;
		
		total = st1.calcTotal();
		avg = st1.calcAvg();
		st1.displayInfo();
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + avg);
		System.out.println("===============");

		total = st2.calcTotal();
		avg = st2.calcAvg();
		st2.displayInfo();
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + avg);
		System.out.println("===============");
		System.out.println("프로그램 종료");
	} // end main()

} // end class Clas06Main










