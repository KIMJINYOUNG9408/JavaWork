package practice.gugu2;

public class Gugu2 {

	public static void main(String[] args) {

		System.out.println("1.===============================================");

		for (int i = 2; i <= 9; i += 3) { // 2중 포문 / 출력문이 많아짐.

			for (int j = 1; j <= 9; j++) {

				System.out.print(i + " X " + j + " = " + i * j + "\t");

				System.out.print((i + 1) + " X " + j + " = " + ((i + 1) * j) + "\t");

				if (i < 8) {

					System.out.print((i + 2) + " X " + j + " = " + (i + 2) * j + "\n");

				} else {

					System.out.println();

				}

			}

			System.out.println();

		}

		System.out.println("2.===============================================");

		System.out.println();

		for (int i = 2; i <= 9; i += 3) { // 3중 포문 /

			for (int j = 1; j <= 9; j++) {

				for (int k = i; k <= i + 2; k++) {

					if (k != 10)

						System.out.print(k + " X " + j + " = " + k * j + "\t");

				}

				System.out.println();

			}

			System.out.println();

		}

	}

}
