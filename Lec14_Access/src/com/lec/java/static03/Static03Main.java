package com.lec.java.static03;

import java.util.Calendar;

// Singleton 디자인 패턴
// 생성되는 인스턴스가 최대 1개까지만 허용해야 하는 패턴설계

public class Static03Main {

	public static void main(String[] args) {

		System.out.println("Singleton 디자인 패턴");

//        Test t1 = new Test(); 
           
//        Test t2 = new Test();
      Test t1 = Test.getInstance();
      
      System.out.println("t1: num " + t1.getNum());
      t1.setNum(123);
      System.out.println("t1: num " + t1.getNum());
      
      Test t2 = Test.getInstance(); // 결국 t2도 t1과 같은 주소값을 레퍼런싱
      System.out.println("t2: num " + t2.getNum()); // ?
       
      t2.setNum(500); // 결국 주소값이 같으니 값이 다같다
      System.out.println(t1.getNum());
      System.out.println(t2.getNum());
       
      
      // Singletone 사용 예 
//      Calendar c = new Calendar(); // new 제공 x
        Calendar c = Calendar.getInstance();  
       
	} // end main()

} // end class Static03Main
