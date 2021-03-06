package com.lec.java.access03;

import com.lec.java.access04.TestPublic2;

//동일 이름의 클래스가 다른 패키지에 있다면 import 하지 말자 import하면 원래것도 바뀜 
//import com.lec.java.access04.TestPublic;

// ↓ 다른 패키지의 (default) 클래스는 애초에 import 불가 ↓
// import com.lec.java.access04.MyDefault2; // not visible 에러

// 클래스의 접근 권한 수식어: public, (default)
// 1. public class: 어디에서나 인스턴스 생성이 가능한 클래스
//   (주의) public 클래스의 이름은 .java 파일 이름과 반드시 같아야 함
// 2. (default) class: 같은 패키지에 있는 클래스들에서만 인스턴스 생성이 가능

public class Access03Main {

	public static void main(String[] args) {
		System.out.println("public, default 클래스");

		TestPublic t1 = new TestPublic();

		// 같은 패키지에 있는 클래스에서는 인스턴스 생성(사용) 가능
		MyDefault t2 = new MyDefault();

		// 다른 패키지더라도 public은 인스턴스 생성(사용) 가능
		TestPublic2 t3 = new TestPublic2(); // ctrl shift o 하면 다른패키지도 import 된다.
		
		
		// 다른 패키지에 있는 (default) 클래스 
		// --> 인스턴스 생성 불가능 함.
		// MyDefault t4;
		
		// 다른 패키지의 클래스와 동일 이름의 클래스가 충돌된다면 ? -> 풀 네임을 사용해서 해결하자
		com.lec.java.access04.TestPublic t11 = new com.lec.java.access04.TestPublic();
		

	} // end main()

} // end class Access05Main
