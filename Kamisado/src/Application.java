import java.util.Scanner;
import java.util.Timer;

public class Application {

	
	public static void main(String[] args) {
		
		
		//MainView mv = new MainView();
		String playerWhite = Xterm.setWhitePlayer();
		String playerBlack = Xterm.setBlackPlayer();
		AbstractPlayer white = new LocalPlayer(playerWhite,Color.WHITE);
		AbstractPlayer black = new LocalPlayer(playerBlack,Color.BLACK);
		String mode = Xterm.chooseGameMode();
		int moveTime = Xterm.moveTime();
		if(mode.equals("speed")){
			SpeedGameDriver game = new SpeedGameDriver(white,black);
			game.subscribe(new StateView());
			game.startGame();
			long startTime = System.currentTimeMillis();
			Thread t = new Thread(game);
			t.start();
			while(t.isAlive()){
				if(System.currentTimeMillis() - startTime > moveTime && t.isAlive() && !t.isInterrupted()){
					t.interrupt();
					game.onTimeOut();
				}
			}
		}else if(mode.equals("normal")){
			GameDriver game = new GameDriver(white, black);
			game.subscribe(new StateView());
			game.startGame();
		}

	}

}
