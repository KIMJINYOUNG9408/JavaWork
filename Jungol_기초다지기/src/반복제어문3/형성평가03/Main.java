package 반복제어문3.형성평가03;

import java.util.Scanner;

public class Main {
   public static void main(String[] args) {
	
	   Scanner sc = new Scanner(System.in);
	   
	   int n = sc.nextInt();
	   
	   for(int i=1; i<n*2; i++) {
		   if(i<=n) {
		   for(int j=0; j<i; j++) {
			System.out.print("*");   
		   }
		   }
		   else {
		   for(int k=n*2; k>i; k--) {
			   System.out.print("*");
		   }
		   }
		   System.out.println();
	   }
}
}
