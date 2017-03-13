import java.util.Scanner;

public class Xterm {
	

	public static String setWhitePlayer(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter player name for white pieces");
		//validate
		String name = in.next();
		in.close();
		return name;

	}
	
	public static String setBlackPlayer(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter player name for black pieces");
		//validate
		String name = in.next();
		in.close();
		return name;

	}
	
	public static Position getPositionInput(){
		Scanner in = new Scanner(System.in);
		System.out.println("Enter X and Y coordinates for the desired move");
		int x = in.nextInt();
		int y = in.nextInt();
		in.close();
		return new Position(x,y);
	}
	
}
