package com.lec.java.hello;

public class Korean {
	// 필드
	String nation = "대한민국";
	String name;
	String ssn; // 생성자
	/*
	 * public Korean(String n, String s){ name = n; ssn = s;
	 */

	public Korean(String name, String ssn) {
		this.name = name;
		this.ssn = ssn; // 필드와 매개변수를 구분하기 위해 this를 사용
// this : 객체 자신을 뜻함
	}

}