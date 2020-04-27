package com.lec.java.collection08;

// 중복은 MyClass에서 다시 처리 볼것
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Collection08Main {

	public static void main(String[] args) {
		System.out.println("HashSet 연습");
		
		// MyClass 타입을 저장할 수 있는 HashSet 인스턴스 생성
		// 데이터 3개 이상 저장해보고 iterator, enahnce-for 등을 
		// 사용하여 출력해보기
		HashSet<MyClass> set = new HashSet<MyClass>();
		Scanner sc = new Scanner(System.in);
		for(int i = 0; i < 3; i++) {
			System.out.println("학번입력: ");
			int id = sc.nextInt();
			sc.nextLine();
			System.out.println("이름입력: ");
			String name = sc.nextLine();
			
			MyClass my = new MyClass(id, name);
			set.add(my);
		}
		
		// 검색: Iterator, enhanced for
		System.out.println();
		System.out.println("Iterator");
		Iterator<MyClass> itr = set.iterator();
		while(itr.hasNext()){
			System.out.println(itr.next());
		}
		System.out.println();
		System.out.println("enhanced for");
		for(MyClass x : set) {
			System.out.println(x);
		}

		// forEach() 메소드 사용
		System.out.println();
		System.out.println("forEach() 사용");
		set.forEach(System.out::print);
        
		sc.close();
		System.out.println("\n프로그램 종료");
	} // end main()
} // end class

