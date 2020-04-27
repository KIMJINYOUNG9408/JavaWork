package 반복제어문1.자가진단01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {

		String a = "11+15+33+22";
		String b;
		String c;
		int tmp = 0;
		int tmp2 = 0;
		int num1, num2, result = 0;

		while (true) {
			
			for (int i = 0; i < a.length(); i++) {
				if (a.charAt(i) == '+') {
					tmp2 = i;
					b = a.substring(0, i);
					for (int j = i + 1; j < a.length(); j++) {

						if (a.charAt(j) == '+') {
							tmp = j + 1;
							c = a.substring(i + 1, tmp - 1);
							num2 = Integer.parseInt(c);
							num1 = Integer.parseInt(b);
							result = num1 + num2;
							a= result + a.substring(tmp);
							System.out.println(result);
							

						} else {
							c = a.substring(i + 1);

						}
					}
				}
			}

		}
		
	}
}
