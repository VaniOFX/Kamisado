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
	private AbstractPlayer winner;
	private Board board;
	private transient ArrayList<Observer> observers;
	private State currentState;
	private Stack<State> history;
	private boolean historyEnabled;
	private boolean running;
	private int scoreWhite;
	private int scoreBlack;
	protected long moveStarted;
	protected long moveTime;
	private Piece winnerPiece;
	private int boardMode;
	private boolean deadlocked;
	private int winScore;
	private boolean restored;
	private boolean sumoPushed;
	
	public static final boolean HISTORYENABLED = true;
	public static final boolean HISTORYDISABLED = false;
	
	public GameDriver(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled, int boardMode){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
		this.boardMode = boardMode;
		initGameDriver();
	}
	
	public GameDriver(AbstractPlayer playerWhite, AbstractPlayer playerBlack, boolean historyEnabled, int boardMode, long moveTime){
		this.playerWhite = playerWhite;
		this.playerBlack = playerBlack;
		this.historyEnabled = historyEnabled;
		this.moveTime = moveTime;
		this.boardMode = boardMode;
		initGameDriver();
		moveStarted = System.currentTimeMillis();
		MoveTimer mt = new MoveTimer();
		Thread t = new Thread(mt);
		t.start();
	}
	
	private class MoveTimer implements Runnable{
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
	}
	
	private void initGameDriver(){
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
		running = true;
		deadlocked = false;
	}
	private void initNextGame(){
		board = new Board(boardMode);
		State newState = new State(board);
		
		newState.refillLeftToRight(currentState.getPieces());
		
		notifyObservers();
		currentState = newState;
		initGame();

	}
	
	public void playGame(int winScore){
		if(winScore == -1){
			winScore = this.winScore;
			observers = new ArrayList<Observer>();
			restored = true;
		}else{
			this.winScore = winScore;
			restored = false;
			initGame();
		}
		while(scoreBlack < winScore && scoreWhite < winScore){
			if(getRoundWinner().equals(Color.BLACK)){
				if(winnerPiece != null){
					switch(winnerPiece.getSumo()){
					case 1: scoreBlack = scoreBlack + 1;
							break;
							
					case 2: scoreBlack = scoreBlack + 3;
							break;
							
					case 3: scoreBlack = scoreBlack + 7;
							break;
							
					default:
							break;
					}
				}	
			}				
			else{
				if(winnerPiece != null){
					switch(winnerPiece.getSumo()){
					case 1: scoreWhite = scoreWhite + 1;
							break;
							
					case 2: scoreWhite = scoreWhite + 3;
							break;
							
					case 3: scoreWhite = scoreWhite + 7;
							break;
							
					default:
							break;
					}
				}
			}	
			System.out.println("The score is" + scoreWhite + ":" + scoreBlack);
			initNextGame();

		}
		if(scoreWhite>scoreBlack) System.out.print(playerWhite.getName() + " has won.");
		else System.out.println(playerBlack.getName()+ " has won.");
	}
	

	public Color getRoundWinner(){
		if(!restored){
			currentState.setCurrentInitial(currentPlayer.getInitialPosition());
		}else{
			System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
					+currentState.getCurrentInitial().getPosY());
		}
		currentState.setCurrentPlayer(playerBlack);
		notifyObservers();
		//used to check for deadlock
		ArrayList<Position> positions = new ArrayList<Position>();
		int counter = 0;
		while(running){
			SaveManager.saveGame(this);
			Move move = null;
			if(positions.size() > 4 ) positions.remove(0);
			positions.add(currentState.getCurrentInitial());
			
			if(deadlocked || counter >= 20){
				System.out.println("deadlock");
				return winner.getColor();
			}
			
			if(GameRules.hasNoLegalMoves(currentState, currentState.getCurrentInitial())){
				if(counter == 0) winner = currentPlayer;
				switchPlayer();
				setNextPiece(currentState.getCurrentInitial());
				counter++;
				continue;
				
			}
			
			if(positions.size() == 4 && positions.get(0) == positions.get(2) && positions.get(1) == positions.get(3)){
				deadlocked = true;
				switchPlayer();
				winner = currentPlayer;
				continue;
			}
			
			moveStarted = System.currentTimeMillis();
			//current player makes a move
			
			if(currentPlayer.getName().equals("AIPlayer")){
				move = currentPlayer.getMove(currentState.clone());
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
					winnerPiece = currentState.getPiece(move.getInitial());
					currentState.getPiece(move.getInitial()).setSumo(curSumo + 1);
				}
				//update board
				State newState = currentState.clone();
				
				if(curSumo > 0 && newState.isSumoPushable(move, currentPlayer.getColor(), curSumo)){
					Position nextPosition = newState.sumoPush(move.getInitial(), currentPlayer.getColor());
					newState.setCurrentInitial(newState.getPiecePosition(
													(currentPlayer.getColor() == Color.WHITE) ? Color.WHITE : Color.BLACK,
															board.getColor(nextPosition)));
//					System.out.println(newState.getCurrentInitial().getPosX()+" "+newState.getCurrentInitial().getPosY());
//					notifyObservers();
//					continue;
					sumoPushed = true;
				}else{
					newState.move(move);
					sumoPushed = false;
				}
				
				
				if(historyEnabled){
					history.push(newState);
				}
				
				currentState = newState;
				
				notifyObservers();
	
				if(!sumoPushed){
					switchPlayer();
					currentState.setCurrentInitial(currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget())));
				}
				
				System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
						+currentState.getCurrentInitial().getPosY());
				
			}else{
				System.out.println("Illegal move.");

			}
		}		
		return currentPlayer.getColor();
		}
	
	private void setNextPiece(Position lastPiece){
		currentState.setCurrentInitial(currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(lastPiece)));
		System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
				+currentState.getCurrentInitial().getPosY());
	}
	
	private void switchPlayer(){
		if(currentPlayer == playerWhite){
			currentPlayer = playerBlack;
		}else{
			currentPlayer = playerWhite;
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
		currentState.printState();
		for(Observer ob : observers){
			ob.update(currentState);
		}
	}

}
