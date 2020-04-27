package com.lec.java.lambda03;

/*
	 세가지 방법으로 자유자재로 구현할수 있어야 합니다.
	 방법 1) 클래스 implements 인터페이스 + 오버라이딩.
	 방법 2) 익명클래스
	 방법 3) 람다 표현식 lambda expression
*/
public class Lambda03Main {

	public static void main(String[] args) {
		System.out.println("익명 클래스, 람다 표현식 연습");
	     
		/*
		 세가지 방법으로 자유자재로 구현할수 있어야 합니다.
		 방법 1) 클래스 implements 인터페이스 + 오버라이딩.
		 방법 2) 익명클래스
		 방법 3) 람다 표현식 lambda expression
	*/     
		
		//방법1 ) 덧셈
		Operable a1 = new a();
		System.out.println("덧셈 결과 = " + a1.operate(11, 21));
		
		
		// 방법 2) 뺄셈
		Operable a2 = new Operable() {
			
			@Override
			public double operate(double x, double y) {
				
				return x-y;
			}
		};
		System.out.println("뺄셈 결과 = " + a2.operate(13, 7));
		
		// 방법3 ) 제곱 
		Operable a3 = (a,b) -> {
			
			System.out.println("a = " + a + " b = " + b );
			
			return Math.pow(a, b);

		};
		
		System.out.println("a 의 b 승 결과 = " + a3.operate(10, 2));
		
	
		System.out.println("\n프로그램 종료");
	} // end main()
	
} 


interface Operable{
	
	public abstract double operate(double x , double y);
}


class a implements Operable{
	@Override
	public double operate(double x, double y) {
		
		return x+y;
	}
}