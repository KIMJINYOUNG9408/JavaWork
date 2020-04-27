package com.lec.java.access01;

public class Test01 {
  private int privateNum; // 자기 자신 클래스 에서만
  int defaultNum; // 자기 자신 + 같은패키지의 클래스 에서만
  protected int protectedNum; // 자기자신 + 같은패키지의 클래스 + 상속받은 클래스
  public int publicNum; // 아무곳이나 
  
  private void privateMethod() {
	  
  }
  
  void defaultMethod() {
	  
  }
  
  protected void protectedMethod() {
	  
  }
  
  public void publicMethod() {
	  
  }
}
