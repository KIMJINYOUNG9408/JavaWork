package com.lec.java.array04;

import java.util.Scanner;

/* 연습
 * 길이 5개 int 형 배열을 선언하고
 * 사용자로부터 5개 정수를 입력받아 초기화 한뒤 
 * 
 * 총점, 평균, 최대값, 최소값  출력해보기
 */
public class Array04Main {

	public static void main(String[] args) {
		System.out.println("배열 연습");

		int score[] = new int[5];
		int total = 0, max = 0, min = 0;
		double avg;
		Scanner sc = new Scanner(System.in);

		for (int i = 0; i < score.length; i++) {
			score[i] = sc.nextInt();
			total += score[i];
			if (i == 0) {
				max = score[i];
				min = score[i];
			} else {
				if (score[i] > max)
					max = score[i];
				if (score[i] < min)
					min = score[i];
			}
		}
		avg = (double) total / score.length;
		System.out.println("총점: " + total);
		System.out.println("평균: " + avg);
		System.out.println("최대값: " + max);
		System.out.println("최소값: " + min);

	} // end main()

} // end class Array04Main
