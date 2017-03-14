import java.util.ArrayList;
import java.util.Stack;

public class GameDriver implements Observable {
	
	private AbstractPlayer player1;
	private AbstractPlayer player2;
	private Stack<State> history;
	private State currentState; 
	private Board board;
	private ArrayList<Observer> observers;
	private AbstractPlayer currentPlayer;
	
	public GameDriver(){
		player1 = new LocalPlayer(Color.WHITE);
		player2 = new LocalPlayer(Color.BLACK);
		history = new Stack<State>();
		currentState = new State();
		board = new Board();
		observers = new ArrayList<Observer>();
	}
	
	public void startGame(){
		history.push(currentState);
		currentPlayer = player1;
		Position currentInitial = currentPlayer.getInitialPosition();
		while(true){

			//current player makes a move
			Move move = currentPlayer.getMove(currentInitial);
			//validate move
			if(GameRules.isLegalMove(currentState, move)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					//end game
				}
				//update board
				currentState.move(move);
				notifyObservers();
				
				currentInitial = currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget()));
				
				//push board to history
				State newState = currentState;
				history.push(newState);
				
				//switch current player
				if(currentPlayer == player1){
					currentPlayer = player2;
				}else{
					currentPlayer = player1;
				}
			}
			
			
			
			
			
			
		}
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
			ob.update();
		}
	}
	
	
	
	

}
