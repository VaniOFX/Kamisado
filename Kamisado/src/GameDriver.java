import java.util.*;

public class GameDriver implements Observable {
	
	private AbstractPlayer player1;
	private AbstractPlayer player2;
	private Stack history;
	private Board board;
	private ArrayList<Observer> observers;
	
	public GameDriver(){
		player1 = new LocalPlayer();
		player2 = new LocalPlayer();
		history = new Stack();
		board = new Board();
		observers = new ArrayList<>();
	}
	
	public void startGame(){
		//push initial board to history
		while(true){

			//current player makes a move
			//validate move
			//update board
			notifyObservers();
			//push board to history
			//switch current player
		}
	}

	@Override
	public void subscribe(Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void unsubscribe(Observer observer) {
		// TODO Auto-generated method stub
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		for(Observer ob : observers){
			ob.update();
		}
	}
	
	
	
	

}
