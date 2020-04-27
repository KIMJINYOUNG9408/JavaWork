package 반복제어문1.자가진단01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
         
		
		for(int i=1; i<=9; i++) {
			System.out.print("**" + i + "단 ** \t\t");
		}
		
		
		for(int i=2; i<=9; i++) {
			System.out.println();
			for(int j=1; j<=9; j++) {
				System.out.print(j + " X " + i + " = " + ( j*i) +"\t");
			}
		}
		 
	}
}
