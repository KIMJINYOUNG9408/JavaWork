package com.lec.java.collection02;

import java.util.ArrayList;
import java.util.Iterator;

public class Collection02Main {

	public static void main(String[] args) {
		System.out.println("ArrayList 연습");
		
		// String 타입을 담는 ArrayList를 만들고
		// 5개 이상의 String을 저장하고
		// set(), remove() 등의 메소드 사용하여
		// 임의의 것을 수정, 삭제 도 해보시고
		// 3가지 방식으로 출력해보세요
		//  for, Enhanced-for, Iterator
        ArrayList<String> list1 = new ArrayList<String>();
        
        list1.add("Arr");
        list1.add("ay");
        list1.add("List");
        list1.add("연습");
        list1.add("해보자");
        System.out.println(list1);
        list1.add(3, "를");
        System.out.println(list1);
        
        list1.remove(3);
        for(int i=0; i<list1.size(); i++) {
        	System.out.print(list1.get(i)+ "  ");
        }
        
        System.out.println();
        list1.add(3, "를");
        for(int i=0; i<list1.size(); i++) {
        	System.out.print(list1.get(i)+ "  ");
        }
        System.out.println();
        
        list1.set(3, "을");
        for(int i=0; i<list1.size(); i++) {
        	System.out.print(list1.get(i)+ "  ");
        }
        System.out.println();
        
        System.out.println("==Enhanced for문 사용 출력==");
		for(String e : list1) {
			System.out.print(e + " - ");
		}
		System.out.println();
        System.out.println("==Iterator=");
		
		Iterator<String> itr = list1.iterator();
		
		while(itr.hasNext()) {
			System.out.print(itr.next()+ " - ");
		}
		
		System.out.println();
		System.out.println("==foreach() 출력==");
		list1.forEach(System.out::print);
		System.out.println("\n프로그램 종료");
	} // end main()

} // end class












