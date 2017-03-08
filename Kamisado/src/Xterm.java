import java.util.Scanner;

public class Xterm {
	
	public Xterm(){
		
	}

	public void setPlayerNames(){
		Scanner in = new Scanner(System.in);
		System.out.println("Player 1:");
		Player1 = in.next();
		System.out.println("Player 2:");
		Player2 = in.next();
	}
	
}
