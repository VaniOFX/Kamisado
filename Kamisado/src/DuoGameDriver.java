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
	public boolean moveExecuted = false;
	
	public DuoGameDriver(AbstractPlayer player1, AbstractPlayer player2){
		if(player1.getColor() == Color.WHITE){
			this.playerWhite = player1;
			this.playerBlack = player2;
		}else{
			this.playerWhite = player2;
			this.playerBlack = player1;
		}
		currentState = new State();
		history = new Stack<State>();
		board = new Board();
		observers = new ArrayList<Observer>();
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
	
	public void play(){
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
						currentState = history.pop();
						notifyObservers();
						continue;
					}
				}
			}
			
			
			//validate move
			if(GameRules.isLegalMove(currentState, move)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					running = false;
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
				if(currentPlayer == playerWhite){
					currentPlayer = playerBlack;
				}else{
					currentPlayer = playerWhite;
				}
				
				
				currentState.setCurrentInitial(currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget())));
				System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
				moveExecuted = true;
				
			}else{
				System.out.println("Illegal move.");
			}
			
		}
		System.out.println(currentPlayer.getName()+ " won");
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
