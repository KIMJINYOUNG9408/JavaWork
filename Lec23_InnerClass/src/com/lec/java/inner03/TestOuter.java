package com.lec.java.inner03;

public class TestOuter {
	private int value; // 1 Outer value
	
	public TestOuter(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	//멤버 내부 클래스
	public class TestInner {
		private int value; // 2. Inner value
		
		public TestInner(int value) {
			this.value = value;
		}
		
		public void printValue() {
			int value = 10; // 3. 지역변수 
			System.out.println("value = " + value);
			System.out.println("value = " + this.value); // <-- this 는 TestInner 객체
			System.out.println("value = " + TestOuter.this.value);
		}
	}

} // end class TestOuter














