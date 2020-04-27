import java.util.Scanner;

public class Main2 {
   public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	
	String a = sc.nextLine();
	String b = sc.nextLine();
	String c = sc.nextLine();
	String d = "" + a.charAt(0)+b.charAt(0)+c.charAt(0);
	
	System.out.println(d);
}
}
