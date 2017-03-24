import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class Game implements Observable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3000944870154795536L;
	private AbstractPlayer playerWhite;
	private AbstractPlayer playerBlack;
	private AbstractPlayer currentPlayer;
	private Board board;
	private ArrayList<Observer> observers;
	private State currentState;
	private Stack<State> history;
	private boolean historyEnabled;
	private Position currentInitial;
	private boolean running;
	protected long moveStarted;
	protected long moveTime;
	
	
	public Game(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
	}
	
	public Game(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled, long moveTime){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
		this.moveTime = moveTime;
		MoveTimer mt = new MoveTimer();
		mt.run();
	}
	
	private class MoveTimer implements Runnable{

		@Override
		public void run() {
			while(true){
				if(System.currentTimeMillis() - moveStarted >= moveTime){
					onTimeOut();
				}
			}
		}

		private void onTimeOut() {
			System.out.println(currentPlayer.getName()+ " ran out of time!");
			if (currentPlayer.getColor() == Color.WHITE){
				System.out.println(playerBlack.getName()+ " won");
			}else{
				System.out.println(playerWhite.getName()+ " won");
			}
			System.exit(1);
			
		}
		
	}
	
	public void initGame(){
		if(historyEnabled){
			history.push(currentState);
		}
		notifyObservers();
		currentPlayer = playerWhite;
		currentInitial = currentPlayer.getInitialPosition();
	}
	
	public void play(){
		running = true;
		while(running){
			//current player makes a move
			moveStarted = System.currentTimeMillis();
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
