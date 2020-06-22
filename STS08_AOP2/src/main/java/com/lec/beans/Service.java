package com.lec.beans;

public abstract class Service {
	public abstract void doAction(); // 추상메소드
	
	// 테스트용 : 메소드 호출정보 출력
	public void printInfo() {
		String className = this.getClass().getSimpleName(); // 클래스 이름 꺼내기
		StackTraceElement[] stackTrace = new Throwable().getStackTrace();
		String methodNmae = stackTrace[1].getMethodName(); // 현재 실행되고 있는 메소드.	
		System.out.println(className + " 의 " + methodNmae + "() 호출");
		
		
		
		
	}
	
	
}
