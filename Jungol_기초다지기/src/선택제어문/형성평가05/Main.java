package 선택제어문.형성평가05;
/*
 * 
 * 1~12사이의 정수를 입력받아 평년의 경우 입력받은 월을 입력받아 평년의 경우 해당 월의 날수를 출력하는 프로그램을 작성하시오.
 */
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int month = sc.nextInt();
        
        switch (month) {
		case 1: case 3: case 5: case 7: case 8: case 10: case 12: 
			System.out.println("31");
			break;
		case 2:
			System.out.println("28");
            break;
		default:
			System.out.println("30");
			break;
		}
		
	}

}
