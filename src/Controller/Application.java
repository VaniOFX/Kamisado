package Controller;
import Model.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;

public class Application {	

	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		//MainView mv = new MainView();
		String newGame = Xterm.setNewGame();
		if(newGame.equals("new")){
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
					//game.subscribe(new StateView());
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
					GameDriver game = new GameDriver(white, black,false,Board.NORMAL);

					//game.subscribe(new StateView());
					game.playGame(3);
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
					AIPlayer = new AIPlayer("AIPlayer",AIPlayerCol,2);
				}
				else if(modeDiff.equals("hard")){
					AIPlayer = new AIPlayer("AIPlayer",AIPlayerCol,4);
				}
				GameDriver game = new GameDriver(LocalPlayer, AIPlayer,false,Board.NORMAL);
				//game.subscribe(new StateView());
				game.playGame(3);
			}
		}else if(newGame.equals("restore")){
			//GameDriver game = SaveManager.restoreState();
//			game.getCurrentState().printState();
//			System.out.println("The next player to move is " + game.getCurrentPlayer().getColor());
//			System.out.println("The next piece to move is on " +
//					game.getCurrentState().getCurrentInitial().getPosX() + " " +
//					game.getCurrentState().getCurrentInitial().getPosY());
			//game.getRoundWinner();
			
		}

		}
	
}
