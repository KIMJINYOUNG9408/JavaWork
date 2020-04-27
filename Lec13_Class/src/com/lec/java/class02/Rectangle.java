package com.lec.java.class02;

public class Rectangle {

	// 속성 : 멤버 변수
	// 가로, 세로
	double width;
	double height;

	// 생성자 2개
	// 1. 디폴트 생성사
	public Rectangle() {
     System.out.println("Rectangle() 생성");
     width = 100; height = 100; // 디폴트 값도 지정 가능하다.
     System.out.println("가로: " + width);
     System.out.println("세로: " + height);
	}

	// 2. 매개변수 가진 생성자
	public Rectangle(double w,double h) {
		width = w ; // 멤버 변수 초기화
		height = h;
		System.out.println("가로 : " + width);
		System.out.println("세로 : " + height);
	}

	// 동작 : 멤버 메소드
	// 1) CalcPerimeter: 사각형의 두레
	public double CalcPerimeter() {
		return 2*width +2*height;
	}
	// 2) CalcArea: 사각형의 넓이
	public double CalcArea() {
		return width * height;
	}
}
