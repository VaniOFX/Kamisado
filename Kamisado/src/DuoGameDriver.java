import java.util.ArrayList;
import java.util.Stack;

public class DuoGameDriver implements Observable {
	
	protected AbstractPlayer playerWhite;
	protected AbstractPlayer playerBlack; 
	protected Board board;
	protected ArrayList<Observer> observers;
	protected State currentState;
	protected AbstractPlayer currentPlayer;
	protected Position currentInitial;
	private Stack<State> history; 
	protected boolean running;
	protected boolean historyEnabled = false;
	public boolean moveExecuted = false;
	
	public DuoGameDriver(AbstractPlayer white, AbstractPlayer black){
		this.playerWhite = white;
		this.playerBlack = black;
		currentState = new State();
		history = new Stack<State>();
		board = new Board();
		observers = new ArrayList<Observer>();
	}
	
	public void startGame(){
		if(historyEnabled){
			history.push(currentState);
		}
		currentPlayer = playerWhite;
		currentInitial = currentPlayer.getInitialPosition();
		running = true;
		while(running){
			moveExecuted = false;
			//current player makes a move
			Move move = currentPlayer.getMove(currentInitial);
			
			if(historyEnabled){
				if(move.getTarget().equals(new Position(-1,-1))){
					if(history.empty()){
						System.out.println("The history is empty");
					}
					else{
						currentState = history.pop();
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
				currentState.move(move);
				
				notifyObservers();
								
				if(historyEnabled){
					history.push(currentState);
				}
				
				//switch current player
				if(currentPlayer == playerWhite){
					currentPlayer = playerBlack;
				}else{
					currentPlayer = playerWhite;
				}
				
				
				currentInitial = currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget()));
				System.out.println(currentInitial.getPosX()+" "+currentInitial.getPosY());
				moveExecuted = true;
				
			}else{
				System.out.println("Illegal move.");
			}
			
		}
		System.out.println(currentPlayer.getName()+ " won");
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
