package com.mvn.junitTest09;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
@RunWith(value = Parameterized.class)
public class AppTest3 {
	
	private String expected;
	private String valueOne;
	
	@Parameters
	public static Collection<String[]> getTestParameters() {
		
		return Arrays.asList(new String[][] {
			{"01033333333", "010-3333-3333"}, // expected, valueOne, valueTwo
			{"123", "1a2a3a"},
			{"72", "7a2"}
		});
	}
	
	
	

	public AppTest3(String expected, String valueOne) {
		super();
		this.expected = expected;
		this.valueOne = valueOne;
	}




	// 실습 3
	@Test
	public void test3() {
		App app = new App();
		assertEquals(expected, app.toNumber(valueOne));
		System.out.println("테스트 통과 " + expected + ":" + valueOne);
		System.out.println();
		
	}
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("<<시작합니다!>>");
	}

}
