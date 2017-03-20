import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

public class Application {

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		
		//MainView mv = new MainView();
		String modePlayers = Xterm.chooseGameMode("single","duo");
		if(modePlayers.equals("duo")){
			String playerWhite = Xterm.setWhitePlayer();
			String playerBlack = Xterm.setBlackPlayer();
			AbstractPlayer white = new LocalPlayer(playerWhite,Color.WHITE);
			AbstractPlayer black = new LocalPlayer(playerBlack,Color.BLACK);
			String modeSpeed = Xterm.chooseGameMode("normal","speed");
			if(modeSpeed.equals("speed")){
				int moveTime = Xterm.moveTime();
				DuoSpeedGameDriver game = new DuoSpeedGameDriver(white,black);
				game.subscribe(new StateView());
				long startTime = System.currentTimeMillis();
				Thread t = new Thread(game);
				t.start();
				while(t.isAlive()){
					if(game.moveExecuted) startTime = System.currentTimeMillis();
					if(System.currentTimeMillis() - startTime > moveTime && t.isAlive() && !t.isInterrupted()){
						t.interrupt();
						game.onTimeOut();
					}
				}
			}else if(modeSpeed.equals("normal")){
				DuoGameDriver game = new DuoGameDriver(white, black);
				
				game.writeToFile();
				game.readFromFile("gameDriver.ser");
				
				game.subscribe(new StateView());
				game.startGame();
			}
		}else if(modePlayers.equals("single")){
			String setCol = Xterm.setPlayerColor();
			String name = "";
			Color AIPlayerCol = null, LocalPlayerCol = null;
			if(setCol.equals("white")){
				name = Xterm.setWhitePlayer();
				AIPlayerCol = Color.BLACK;
				LocalPlayerCol = Color.WHITE;
			}
			else if(setCol.equals("black"))
			{
				name = Xterm.setBlackPlayer();
				AIPlayerCol = Color.WHITE;
				LocalPlayerCol = Color.BLACK;
			}
			
			AbstractPlayer LocalPlayer = new LocalPlayer(name,LocalPlayerCol);	
			AbstractPlayer AIPlayer = null;
			String modeDiff = Xterm.chooseGameMode("easy", "hard");
			if(modeDiff.equals("easy")){
				AIPlayer = new EasyAIPlayer("AIPlayer",AIPlayerCol);
			}
			else if(modeDiff.equals("hard")){
				AIPlayer = new HardAIPlayer("AIPlayer",AIPlayerCol);
			}
			SingleGameDriver game = new SingleGameDriver(LocalPlayer,AIPlayer);
			game.subscribe(new StateView());
			game.startGame();
		}
	}

}
