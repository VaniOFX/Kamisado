package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Stack;

public class GameDriver implements Observable, Serializable {

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
	private int scoreWhite;
	private int scoreBlack;
	private long moveStarted;
	private long moveTime;
	private MoveTimer mt;
	
	public static final boolean HISTORYENABLED = true;
	public static final boolean HISTORYDISABLED = false;
	
	public GameDriver(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled, int boardMode){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
		initGameDriver(boardMode);
	}
	
	public GameDriver(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled, int boardMode, long moveTime){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
		this.moveTime = moveTime;
		initGameDriver(boardMode);
		moveStarted = System.currentTimeMillis();
		mt = new MoveTimer(moveStarted,moveTime);
		Thread t = new Thread(mt);
		t.start();
	}
	
	private class MoveTimer implements Runnable{
		private long moveStarted;
		private long moveTime;
		
		public MoveTimer(long moveStarted,long moveTime){ 
			this.moveTime = moveTime;
			this.moveStarted = moveStarted;}
		@Override
		public void run() {
			while(true){
				if(System.currentTimeMillis() - moveStarted >= moveTime*1000){
					onTimeOut();
				}
			}
		}

		private void onTimeOut() {
			System.out.println(" ran out of time!");
			System.exit(1);
			
		}
		public void updateMoveTimer(long moveStarted){
			this.moveStarted = moveStarted;
		}
		
	}
	
	private void initGameDriver(int boardMode){
		scoreWhite = 0;
		scoreBlack = 0;
		board = new Board(boardMode);
		currentState = new State(board);
		observers = new ArrayList<Observer>();
		if(historyEnabled){
			history = new Stack<State>();
		}
		
	}
	
	private void initGame(){
		if(historyEnabled){
			history.push(currentState);
		}
		notifyObservers();
		currentPlayer = playerWhite;
		currentState.setCurrentInitial(currentPlayer.getInitialPosition());
		running = true;
		
	}
	private void initNextGame(){
		State newState = new State(board);
		
		newState.refillLeftToRight(currentState.getPieces());
		
		notifyObservers();
		currentState = newState;
		initGame();

	}
	
	
	public void playGame(int winScore){
		initGame();
		while(true){
			if(getRoundWinner().equals(Color.BLACK)){
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
			initNextGame();
			
			System.out.println("New GAME!");
		}
	}
	
	public Color getRoundWinner(){
		while(running){
			if(mt!= null) mt.updateMoveTimer(System.currentTimeMillis());
			//current player makes a move
			Move move;
			if(currentPlayer.getName().equals("AIPlayer")){
				State stateCopy = currentState.clone();
				move = currentPlayer.getMove(stateCopy);
			}else{
				move = currentPlayer.getMove(currentState);
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
			
			int curSumo = currentState.getPiece(move.getInitial()).getSumo();
			//validate move
			if(GameRules.isLegalMove(currentState, move, curSumo)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					running = false;
					currentState.getPiece(move.getInitial()).setSumo(curSumo + 1);
				}
				//update board
				State newState = currentState.clone();
				newState.move(move);

								
				if(historyEnabled){
					history.push(newState);
				}
				
				currentState = newState;
				
				if(curSumo > 0 && newState.isSumoPushable(move.getTarget(), currentPlayer.getColor())){
					Position nextPosition = currentState.sumoPush(move.getTarget(), currentPlayer.getColor());
					currentState.setCurrentInitial(currentState.getPiecePosition(
													(currentPlayer.getColor() == Color.WHITE) ? Color.WHITE : Color.BLACK,
															board.getColor(nextPosition)));
					System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
					notifyObservers();
					continue;
				}
				
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
				System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
																+currentState.getCurrentInitial().getPosY());
				
			}else{
				System.out.println("Illegal move.");
			}			
		}
		return currentPlayer.getColor();
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
		currentState.printState();
		for(Observer ob : observers){
			ob.update(currentState);
		}
	}

}
