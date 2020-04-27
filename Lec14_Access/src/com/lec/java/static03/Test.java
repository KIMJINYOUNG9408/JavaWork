package com.lec.java.static03;

public class Test {
	private int num;
    
	static int count = 0;
	private Test() { // private로 막아놓으면 인스턴스를 함부로 못만듬
		count++;
		System.out.println(count + "번째 인스턴스 생성");
	}
	
	private static Test instance = null;
	
	public static Test getInstance() {
		if(instance == null) {
			instance = new Test(); // 인스턴스 생성
		}
		return instance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}




