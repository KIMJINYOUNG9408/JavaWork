package com.lec.java.class01;

/*
 	클래스 정의 및 사용
 	
 	우리가 만든 클래스를 사용하려면,
	그 클래스의 '인스턴스(실체 instance)'를 생성해야 함.
	
	구문
		클래스이름 변수이름 = new 생성자();
		생성자(): 클래스의 이름과 동일, 클래스의 멤버변수들을 초기화
		
 	new 로 생성하는 instance 들은
 	지역변수와는 달리 인스턴스가 생성될 때 (자동으로) 초기화가 된다.
 	특별히 초기화를 할 값들이 지정되어 있지 않을 때는
 	각 변수 타입의 기본값(디폴트 값)으로 초기화가 됨
 		boolean -> false, 숫자타입(int, double, ...) -> 0
 		String, 참조 타입(클래스) -> null
 	
 	지역변수: 메소드 안에서 선언된 변수
 	지역변수는 사용하기 전에 반드시 초기화를 해줘야 함.
 */
public class Class01Main {

	public static void main(String[] args) {
		System.out.println("클래스 생성 & 사용");

		MyTV tv1 = new MyTV();

		tv1.displayStatus();
		tv1.powerOnOff(); // TV켜기
		tv1.displayStatus();
		tv1.channelUp(); // 채널증가시키기
		tv1.volumeUp(); // 볼륨증가시키기
		tv1.displayStatus();
		tv1.powerOnOff(); // TV끄기
		tv1.displayStatus();

		// 일반적으로 멤버변수는 private 로 설정하여 외부클래스에서 직접 사용하는것을 금하게 설계함.

		// tv1.isPowerOn = true;
		// tv1.volume = 32;
		// tv1.channel = 52;
		// tv1.displayStatus();
		System.out.println("==================");
		MyTV tv2 = new MyTV(); // 다른 MyTV 인스턴스
		tv2.powerOnOff();
		tv2.channelUp();
		tv2.volumeUp();
		tv2.displayStatus();

		System.out.println();
		System.out.println(tv1); // 인스턴스 객체를 그대로 찍으면
		System.out.println(tv2); // 이런형태로 나옴 --> com.lec.java.class01.MyTV@15db9742
		
		System.out.println();
		tv1 = tv2; // tv2의 주소를 tv1에 대입하는것.
		           // 즉 tv1의 주소가 있고 tv2의 주소가 있는데 tv2의 주소가 tv1으로 복사된다. 
		           //결국 tv2 와 tv1 둘다 tv2 주소임
		           // tv1이 레퍼런싱 하던 인스턴스 = 가비지컬렉터가 제거. 
		           //결국 스택영역에는 tv2와 tv1의 주소 같아지고
		           // 힙영역에는 초기 tv2밖에 남지 않는다.
		
	    tv1.displayStatus(); 
	    tv1.powerOnOff();
	    tv2.displayStatus(); // tv1과 tv2는 같은 인스턴스를 레퍼런싱 하고있다. 결국 같이바뀐다.
	    
	    System.out.println(tv1); 
	    System.out.println(tv2);  // 주소값이 같아진걸 확인할 수 있다.
		System.out.println("\n프로그램 종료");
		
	} // end main()

} // end class
