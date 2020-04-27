package 배열1.자가진단09;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int a[] = new int[10];
		Scanner sc = new Scanner(System.in);
		int temp = 0;
		for (int i = 0; i < a.length; i++) {
			a[i] = sc.nextInt();
		}
         
		
		/* 버블정렬 
		 * for(int i = array.length; i>0; i--){
		 *  for(int j = 0; j< i-1; j++ {
 		 *   if(array[j] < array[j + 1]) {
		 *       int temp = array[j]'
		 *       array[j] = array[j+1];
		 *       array[j+1] = temp;
 		 *      }
 		 *    }
 		 *   }   
		 * 
		 */  
		
		
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				if (a[i] < a[j]) {
					temp = a[j];
					a[j] = a[i];
					a[i] = temp;
				}
			}
		}
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}

}
