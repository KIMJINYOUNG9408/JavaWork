package 반복제어문2.형성평가08;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
	
	  Scanner sc = new Scanner(System.in);
	  
	  int x = sc.nextInt();
	  int y = sc.nextInt();
	  int n;
	  for(int i=0; i<x; i++) {
		  
		  for(int j=0; j<y; j++) {
			   n = i + 1;
			   n = n * (j+1);
			  System.out.print(n+ " ");
			  
		  }
		  System.out.println();
	  }
}
}

