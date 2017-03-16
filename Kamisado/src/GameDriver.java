import java.util.ArrayList;
import java.util.Stack;

public class GameDriver implements Observable {
	
	private AbstractPlayer playerWhite;
	private AbstractPlayer playerBlack;
	private Stack<State> history;
	private State currentState; 
	private Board board;
	private ArrayList<Observer> observers;
	private AbstractPlayer currentPlayer;
	private Position currentInitial;
	private boolean running;
	
	public GameDriver(AbstractPlayer white, AbstractPlayer black){
		this.playerWhite = white;
		this.playerBlack = black;
		history = new Stack<State>();
		currentState = new State();
		board = new Board();
		observers = new ArrayList<Observer>();
	}
	
	public void startGame(){
		history.push(currentState);
		currentPlayer = playerWhite;
		currentInitial = currentPlayer.getInitialPosition();
		running = true;
		while(running){

			//current player makes a move
			Move move = currentPlayer.getMove(currentInitial);
			//validate move
			if(GameRules.isLegalMove(currentState, move)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					running = false;
				}
				//update board
				currentState.move(move);
				
				notifyObservers();
								
				//push board to history
				history.push(currentState);
				
				//switch current player
				if(currentPlayer == playerWhite){
					currentPlayer = playerBlack;
				}else{
					currentPlayer = playerWhite;
				}
				
				currentInitial = currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget()));
				System.out.println(currentInitial.getPosX()+" "+currentInitial.getPosY());
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
