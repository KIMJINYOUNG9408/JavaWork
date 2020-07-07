package com.mvn.junitTest09;


import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    // 실습1
	@Test
    public void test1() {
		System.out.println("실습1] 오름차순 정렬하기");
		int a[] = new int[]{5,12,2,72,1}; // 5개의 테스트 데이터 준비.
		int answer[] = new int[] {1,2,5,12,72}; // 비교 값 통과하는 정답 배열 한번에 하기위해서 그냥 배열로 지정해줌. 답들
//		int answer[] = new int[] {2,1,5,12,72}; // 비교 값 통과 안하게하는 오답 배열
    	App app = new App();
    	
    	app.sortArr(a);
//    	for(int i=0; i<a.length; i++) {
//    		if(a[i] != Answer[i]) fail();
//    	}
    	for(int i=0; i<a.length; i++) {
    		assertEquals(answer[i], a[i]);
    	}
		 System.out.println("테스트를 통과하였습니다. 값을 출력합니다.");

    	
    	
    	for(int i=0; i<a.length; i++) {
    		System.out.print(a[i] + " ");
    	}
    	System.out.println();
    	
    	
    	
    }
	
	// 실습2
	@Test
	public void test2() {
		System.out.println();
		System.out.println("실습2] 최대값, 최소값");
		String path = System.getProperty("user.dir");
		File dir = new File(path, "TEST");
		 if(!dir.exists()){

			 System.out.println("폴더 생성완료 >> 경로:" + path);  // 테스트 실행전 폴더 생성한다!
	         dir.mkdir();


	      }
		 
		 // 3개의 테스트 데이터
		 int a[] = new int[]{5,12,2,72,1};
		 int b[] = new int[]{31,22,99,4,100};
		 int c[] = new int[]{77,66,55,44,33};
		 
		
		 App app = new App();
		 
		 // 테스트 실행.
		 assertEquals(72, app.max(a));
		 assertEquals(1, app.min(a));
		 assertEquals(100, app.max(b));
		 assertEquals(4, app.min(b));
		 assertEquals(77, app.max(c));
		 assertEquals(33, app.min(c));
		 
		 // 테스트 다 통과하면 콘솔에 찍어주고 파일에 입력하기
		 System.out.println("테스트를 통과하였습니다. 값을 출력합니다.");
		 System.out.println("첫번째 최대값:" +app.max(a) + " & 첫번째 최소값:" + app.min(a));
		 System.out.println("두번째 최대값:" +app.max(b) + " & 두번째 최소값:" + app.min(b));
		 System.out.println("세번째 최대값:" +app.max(c) + " & 세번째 최소값:" + app.min(c));
		 
		 File file = new File(path + "/TEST/report.txt");
		 try {
		      //파일에 문자열을 쓴다.
		      //하지만 이미 파일이 존재하면 모든 내용을 삭제하고 그위에 덮어쓴다
		      //파일이 손산될 우려가 있다.
		      FileWriter fw = new FileWriter(file);
		      
		      fw.write("최대값 / 최소값\n");
		      fw.write(Integer.toString(app.max(a)));
		      fw.write(" / ");
		      fw.write(Integer.toString(app.min(a)));
		      fw.write("\n");
		      fw.write(Integer.toString(app.max(b)));
		      fw.write(" / ");
		      fw.write(Integer.toString(app.min(b)));
		      fw.write("\n");
		      fw.write(Integer.toString(app.max(c)));
		      fw.write(" / ");
		      fw.write(Integer.toString(app.min(c)));
		      
		      fw.close();
		      System.out.println("파일에 입력을 완료 하였습니다.");
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		  
				
	}
    

    
}
