package 선택제어문.자가진단09;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		int c = sc.nextInt();
		
		int min;
		
		if(a>b) {
			if (b>c) {
				min = c;
			} else {
				min = b;
			}
		} else {
			if (a>c) {
				min = c;
			} else {
				min = a;
			}
		}
       
		System.out.println(min);
	}

}
