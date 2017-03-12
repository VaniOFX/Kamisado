import java.util.Scanner;

public class Xterm {
	
	private Scanner in;
	
	
	public Xterm(){
		in = new Scanner(System.in);
	}

	public String setWhitePlayer(){
		System.out.println("Enter player name for white pieces");
		//validate
		String name = in.next();
		
		return name;

	}
	
	public String setBlackPlayer(){
		System.out.println("Enter player name for black pieces");
		//validate
		String name = in.next();
		
		return name;

	}
	
	public Position getTargetPositionInput(){
		System.out.println("Enter X and Y coordinates for the desired move");
		int x = in.nextInt();
		int y = in.nextInt();
		return new Position(x,y);
	}
	
}
