import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class DuoGameDriver implements Observable, Serializable{
	
	private static final long serialVersionUID = -4838628746443249629L;
	protected AbstractPlayer playerWhite;
	protected AbstractPlayer playerBlack; 
	protected Board board;
	protected ArrayList<Observer> observers;
	protected State currentState;
	protected AbstractPlayer currentPlayer;
	protected Stack<State> history; 
	protected boolean running;
	protected boolean historyEnabled = false;
	protected int scoreWhite;
	protected int scoreBlack;
	public boolean moveExecuted = false;
	
	public DuoGameDriver(AbstractPlayer player1, AbstractPlayer player2){
		if(player1.getColor() == Color.WHITE){
			this.playerWhite = player1;
			this.playerBlack = player2;
		}else{
			this.playerWhite = player2;
			this.playerBlack = player1;
		}
		history = new Stack<State>();
		board = new Board(0);
		currentState = new State(board);
		observers = new ArrayList<Observer>();
		scoreWhite = 0;
		scoreBlack = 0;
	}
	
	public void startGame(){
		if(historyEnabled){
			history.push(currentState);
		}

		SaveManager.saveGame(this);
		currentPlayer = playerWhite;
		currentState.setCurrentInitial(currentPlayer.getInitialPosition());
		running = true;
	}
	
	public void countScore(int winScore){
		while(true){
			if(play().equals(Color.BLACK)){
				scoreBlack++;
				if(scoreBlack == winScore){
					System.out.println(playerWhite.getName()+ " won");
					break;
				}
				
			}				
			else{
				scoreWhite++;
				if(scoreWhite == winScore){
					System.out.println(playerWhite.getName()+ " won");
					break;
				}
			}
			State newState = new State(board);
			newState.refillLeftToRight(currentState.getPieces());
			newState.printState();
			currentState = newState;
			startGame();
			System.out.println("New GAME!");
		}
	}
	
	
	public Color play(){
		while(running){
			moveExecuted = false;
			SaveManager.saveGame(this);
			//current player makes a move
			Move move;
			if(currentPlayer.getName().equals("AIPlayer")){
				State newState = new State(currentState.getPieces());
				newState.setCurrentInitial(currentState.getCurrentInitial());
				move = currentPlayer.getMove(newState);
			}else{
				move = currentPlayer.getMove(currentState.getCurrentInitial());
			}
			
			
			if(historyEnabled){
				if(move.getTarget().equals(new Position(-1,-1))){
					if(history.empty()){
						System.out.println("The history is empty");
						continue;
					}
					else{
						history.pop();
						history.pop();
						currentState = history.pop();
						notifyObservers();
						continue;
					}
				}
			}
			
			int curSumo = currentState.getPiece(currentState.getCurrentInitial()).getSumo();
			//validate move
			if(GameRules.isLegalMove(currentState, move, curSumo)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					running = false;
					

					currentState.getPiece(move.getInitial()).setSumo(curSumo + 1);
				}
				//update board
				State newState = new State(currentState.getPieces());
				newState.move(move);
				

								
				if(historyEnabled){
					history.push(newState);
				}
				
				currentState = newState;
				
				notifyObservers();
				
				//switch current player
				if(running){
					if(currentPlayer == playerWhite){
						currentPlayer = playerBlack;
					}else{
						currentPlayer = playerWhite;
					}
				}
				
				
				currentState.setCurrentInitial(currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget())));
				System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
				moveExecuted = true;
				
			}else{
				System.out.println("Illegal move.");
			}
			
		}
//		System.out.println(currentPlayer.getName()+ " won");
		return currentPlayer.getColor();
		}

	
	public State getCurrentState(){
		return currentState;
	}
	
	public AbstractPlayer getCurrentPlayer(){
		return currentPlayer;
	}
	
	public AbstractPlayer getPlayerBlack(){
		return playerBlack;
	}
	
	public AbstractPlayer getPlayerWhite(){
		return playerWhite;
	}
	@Override
	public void subscribe(Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void unsubscribe(Observer observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for(Observer ob : observers){
			ob.update(currentState);
		}
	}
	
}
