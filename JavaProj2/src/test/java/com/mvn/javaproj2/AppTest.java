package com.mvn.javaproj2;

import static org.junit.Assert.*;

import org.junit.Test;

public class AppTest {
	private App app = new App();
	
	@Test
	public void test1() {
//		if("welcome".equals(app.getWelecome())) {
//			System.out.println(true);
//		} else {
//			System.out.println(false);
//		}
		
		// assertXXX() 메소드 사용
		assertEquals("welcome", app.getWelecome());
		assertEquals("Hello", app.getHello());
		assertEquals("bye", app.getBye());
		
	}
	
	@Test
	public void test2() {
		
		assertEquals("Bye", app.getBye());
	}
	
	@Test
	public void test3() {
		
		assertEquals("hello", app.getHello());
	}
	

}
