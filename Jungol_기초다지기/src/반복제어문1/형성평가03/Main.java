package 반복제어문1.형성평가03;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum = 0 , cnt = 0 , n;
		
		while(true) {
			n = sc.nextInt();
			if(n<0 || n>100) {
				break;
			} 
			sum  += n;
			cnt ++;
			
		}
		System.out.println("sum : " + sum);
		System.out.printf("avg : %.1f",(double)sum / cnt);
	}

}
