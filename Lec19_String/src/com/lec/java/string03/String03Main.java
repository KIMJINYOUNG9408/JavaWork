package com.lec.java.string03;

/*  StringBuffer, StringBuilder

	 String 클래스는 immutable 클래스이고, // 새로 만들어야 함
	 StringBuffer, StringBuilder는 mutable 클래스임 // 내용에서 바뀜 

     
	 StringBuffer:
		Java ver 1.0부터 있던 클래스
		쓰레드에 안전(thread-safe), 속도가 느림
	
	 StringBuilder:
		Java 5부터 생긴 클래스
		쓰레드에 안전하지 않다(thread-unsafe), 속도가 빠름.
	
		그 외에는 StringBuffer와 StringBuilder는 같은 기능(메소드)을 갖고 있음
		

	** 웹 프로그래밍 등에선 문자열을 '붙여 나가며' 완성하는 동작을 많이 함
		실무에서는
			String 을 concat() (혹은 + 연산)하기 보다는
			StringBuffer 를 append() 하는 것을 더 많이 사용함  (성능 UP)
			
		※ 그러나 학습예제에선 예제단순화를 위해 String 을 기본적으로 사용하니 참조 바랍니다
 */
public class String03Main {

	public static void main(String[] args) {
		System.out.println("[1] StringBuffer, StringBuilder");
		// String 클래스는 immutable 클래스이고,
		// StringBuffer, StringBuilder는 mutable 클래스임 mutable = 변할 수 있는 immutable = 변할 수
		// 없는

		System.out.println("[String: immutable]");

		String str1 = "Hello";
		String str2 = str1;

		System.out.println("str1 = " + str1);
		System.out.println("str2 = " + str2);

		if (str1 == str2) {
			System.out.println("== 동일 참조");
		} else {
			System.out.println("== 다른 참조");
		}

		System.out.println();
		System.out.println("[StringBuffer: mutable]"); 
        
		
		System.out.println(str1.concat("Java")); // str1이 실제 변경되지 않는다  == > concat을 사용하면 HelloJava 가 Heap에 생기는거임 그전에는 MA에 Hello가 있음.
 		System.out.println(str1);      // str1은 MA에 있는 Hello
		str1 = str1.concat("Java"); // str1이 실제 변경 된다. --> str1.concat의 새로운 객체가 새로 heap에 만들어진다. 그 주소가 stack에  str1의 주소를 새로 변경.
		System.out.println(str1);

		if (str1 == str2) {
			System.out.println("== 동일 참조");
		} else {
			System.out.println("== 다른 참조");
		}
		
		StringBuffer buff1 = new StringBuffer("Hello"); // buff1 은 stack에 생기고 주소값을 담고 heap에생기는 String0Buffer는 Hello를 담는다  stack에 담고있는 주소가 heap의 SB주소를 가르킴
		StringBuffer buff2 = buff1;
		StringBuffer buff4 = new StringBuffer("zz");
		buff4 = buff4.append("d");
		System.out.println(buff4);
		System.out.println("buff1 = " + buff1);
		System.out.println("buff2 = " + buff2);
		
		if(buff1 == buff2) {
			System.out.println("== 동일참조");
		} else {
			System.out.println("== 다른참조");
		}
		
		System.out.println(buff1.append("Java")); // append를 사용해서 heap에있는 내용을 바꾸는거다 주소 변경 = x String 과 다른점.
		System.out.println(buff1);
		System.out.println(buff2);
		if(buff1 == buff2) {
			System.out.println("== 동일참조");
		} else {
			System.out.println("== 다른참조");
		}
		
		// StringBuffer:
		// Java ver 1.0부터 있던 클래스
		// 쓰레드에 안전(thread-safe), 속도가 느림

		// StringBuilder:
		// Java 5부터 생긴 클래스
		// 쓰레드에 안전하지 않다(thread-unsafe), 속도가 빠름.

		// 그 외에는 StringBuffer와 StringBuilder는 같은 기능을 갖고 있음

		StringBuilder buff3 = new StringBuilder("Hello");
		System.out.println(buff3.append("Java"));
		
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class
