package com.lec.java.oop06;

import java.util.Scanner;

public class Interface02Main  {

	public static void main(String[] args) {
		System.out.println("인터페이스");

		Scanner sc = new Scanner(System.in);
		System.out.println("메뉴 선택: ");
		int menu = sc.nextInt();
		sc.close();

		switch (menu) {
		case Menu.MENU_QUIT:
			System.out.println(Menu.STR_QUIT);
			return;
		case Menu.MENU_INSERT:
			System.out.println(Menu.STR_INSERT);
			break;
		case Menu.MENU_SEARCH:
			System.out.println(Menu.STR_SEARCH);
			break;
		default:
			System.out.println(Menu.STR_ERROR);
			break;
		}

		System.out.println("\n 프로그램 종료");
	} // end main()

} // end class

