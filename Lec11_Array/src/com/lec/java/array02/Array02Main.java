package com.lec.java.array02;

/*  배열의 선언과 초기화
 	
 	배열 선언 따로, 초기화 따로
		타입[] 이름 = new 타입[배열의 길이];
		
	배열을 선언과 동시에 초기화 1
		타입[] 이름 = new 타입[] {a, b, c, ...};
		
	배열을 선언과 동시에 초기화 2
		타입[] 이름 = {a, b, c, ...};
		
	배열의 길이를 자동으로 알 수 있는 방법
		배열이름.length 
 */
public class Array02Main {
	

	public static void main(String[] args) {
		System.out.println("배열의 선언과 초기화");
		// 배열 선언 따로, 초기화 따로
		int[] korean = new int[3];
		// length : 데이터 개수 -> 3
		// size : 데이터 용량 -> 12 byte (이상)

		korean[0] = 100;
		korean[1] = 90;
		korean[2] = 80;

		for (int i = 0; i < korean.length; i++) {
			System.out.println("국어 " + i + " : " + korean[i]);
		}

		// 선언과 동시에 초기화
		int[] english = new int[] { 30, 20, 30 };

		for (int i = 0; i < english.length; i++) {

			System.out.println("영어 " + i + " : " + english[i]);
		}

		int[] math = { 99, 88, 77, 66 }; // new int[]{99, 88, 77, 66}
		for (int i = 0; i < math.length; i++) {
			System.out.println("수학 " + i + " : " + math[i]);
		}
		
		// 수학점수들의 총점과 평균 계산
		int total = 0;
		for (int i = 0; i < math.length; i++) {
			total += math[i];
		}
		System.out.println("총 합: "+total);
		System.out.println("평균: "+(double)total/math.length);
		
		System.out.println(math);
		
		
		
	} // end main()

} // end class Array02Main
