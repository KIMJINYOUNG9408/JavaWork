import java.util.Scanner;

public class Main4 {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int num;
		int max = 0;
		int min = 0;
		int cnt = 0;
		while (true) {
			num = sc.nextInt();
			if (num == 0)
				break;
			cnt++;
			if (cnt == 1) {
				max = num;
				min = num;
			} else {
				if (num > max) {
					max = num;
				}
				if (num < min) {
					min = num;
				}
			}
		}

		System.out.println(max + " " + min);

	}
}
