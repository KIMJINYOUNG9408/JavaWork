package practice.stddev;

import java.util.Random;

public class StdDev {

	public static void main(String[] args) {

		int[] randArr = new int[5];
		Random rand = new Random();
        
		for (int i = 0; i < randArr.length; i++) {
			randArr[i] = rand.nextInt(100) + 1;
		    System.out.print(randArr[i] + " ");
		}
		System.out.println();
		
		double avg = calcAvg(randArr);
		System.out.println("평균 : " + avg);
		
		double Variance = calcVariance(randArr);
		System.out.println("분산 : " + Variance);
		
		double stdDev = calcStdDev(randArr);
		System.out.println("표준편차 : + " + stdDev);
		// 임의정수 5개로 초기화한 정수로
		// 평균, 분산, 표준편차 구하기

	} // end main

	/**
	 * 메소드 이름 : calcAvg 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '평균값' 리턴
	 */
	public static double calcAvg(int randArr[]) {
		double avg = 0;
		int total = 0;
		for (int i = 0; i < randArr.length; i++) {
			total += randArr[i];
		}
		avg = (double) total / randArr.length;
		return avg;
	}

	/**
	 * 메소드 이름 : calcVariance 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '분산값' 리턴
	 */
	public static double calcVariance(int randArr[]) {
		double avg = calcAvg(randArr);
		double Variance = 0;
		for (int i = 0; i < randArr.length; i++) {
			Variance += Math.pow(avg - randArr[i], 2);

		}
		Variance /= randArr.length;

		return Variance;
	}

	/**
	 * 메소드 이름 : calcStdDev 매개변수 : int [] 리턴값 : double
	 * 
	 * 주어진 배열의 '표준편차' 리턴
	 */
	public static double calcStdDev(int randArr[]) {
		double stdDev = calcVariance(randArr);
		stdDev = Math.sqrt(stdDev);
		return stdDev;
	}

} // end class
