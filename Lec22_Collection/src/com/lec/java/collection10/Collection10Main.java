package com.lec.java.collection10;

import java.util.Iterator;
import java.util.TreeSet;

public class Collection10Main {

	public static void main(String[] args) {
		System.out.println("TreeSet 연습");
		
		// String 타입을 저장할 수 있는 TreeSet 인스턴스 생성
		// 5개 이상의 데이터를 저장해보고
		// 오름차순, 내림차순으로 출력해보기
		TreeSet<String> tset = new TreeSet<String>();
		
		tset.add("자");
		tset.add("바");
		tset.add("수");
		tset.add("업");
		tset.add("시");
		tset.add("간");
		 
		
		System.out.println("오름차순: ");
		Iterator<String> itr = tset.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println(tset);
		
		System.out.println("내림차순: ");
		Iterator<String> itr2 = tset.descendingIterator();
		while(itr2.hasNext()) {
			System.out.println(itr2.next());
		}
		
		System.out.println(tset.descendingSet());
		
		System.out.println("\n프로그램 종료");
	} // end main

} // end class

















