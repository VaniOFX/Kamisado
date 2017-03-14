import java.util.Scanner;

public class Application {

	
	public static void main(String[] args) {
		
		Xterm in = new Xterm();
		
		GUI.paintGUI();

		String playerWhite = in.setWhitePlayer();
		String playerBlack = in.setBlackPlayer();
		
		GameDriver game = new GameDriver();
		game.startGame();
		//subscribe observer
		
		
		
		

	}

}
