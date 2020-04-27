package practice.game369;

public class Game369 {
	public static void main(String[] args) {

		int n = 1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {

				if ((n < 10 && n % 3 == 0) || ((n % 10 % 3) == 0 && n % 10 != 0) || (n / 10) % 3 == 0 && n > 10) {
					  //  ↑ 10보다 작을때                         ↑                                      ↑ 10보다 큰수에서 
					System.out.print("*\t");
				} else {
					System.out.print(n + "\t");
				}

				n++;
			}
			System.out.println();
		}
	}
}
