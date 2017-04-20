package Model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkGameDriver implements Observable, Runnable {

	private String ip = "localhost";
	private int port = 7;
	private Thread thread;
	
	private Socket socket;
	private ServerSocket serverSocket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private boolean client = true;
	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;

	private int scorePersonal;
	private int scoreOpponent;
	private int winScore;
	private boolean won;
	
	private int errors;
	
	private Board board;
	private State currentState;
	private ArrayList<Observer> observers;
	private boolean yourTurn = false;
	
	private NetworkProtocol incoming;

	
	public NetworkGameDriver(int winScore,int boardMode){
		this.winScore = winScore;
		observers = new ArrayList<>();
		scorePersonal = 0;
		scoreOpponent = 0;
		
		
		if(!connect()) initializeServer();
		
		if(!client){
			board = new Board(boardMode);
			currentState = new State(board);
		}
		else{
			if(receivePackage()){
				if(incoming.getType() == NetworkProtocol.BOARD){
					board = (Board) incoming.getMessage();
					currentState = new State(board);
				}
			}
		}
		
		
		thread = new Thread(this,"NetworkGame");
		thread.start();
	}
	
	private boolean connect() {
		try {
			socket = new Socket(ip, port);
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			accepted = true;
			
		} catch (IOException e) {
			System.out.println("Cannot find the server. Starting a new one.");
			return false;
		}
		System.out.println("Successfully connected to the server.");
		return true;
	}
	
	private void initializeServer() {
		try {
			serverSocket = new ServerSocket(port,8, InetAddress.getByName(ip));
		} catch (Exception e) {
			e.printStackTrace();
		}
		yourTurn = true;
		client = false;	
	}

	private void listenForServerRequest() {
		try {
			socket = serverSocket.accept();
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			accepted = true;
			NetworkProtocol boardMode = new NetworkProtocol(NetworkProtocol.BOARD, board);
			oos.writeObject(boardMode);
			System.out.println("An opponent has joined the game");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(scorePersonal < winScore && scoreOpponent < winScore){
			System.out.println("Current score: " +scorePersonal + " " + scoreOpponent);
			if(!client && !accepted){
				listenForServerRequest();
				currentState.setCurrentInitial(Xterm.getInitialPositionWhite());
			}
			
			
			if(!yourTurn){
				if(receivePackage()){
					if(incoming.getType() == NetworkProtocol.STATE){
						receiveMove();
					}else if(incoming.getType() == NetworkProtocol.LEFTTORIGHT){
						State newState = new State(board);
						newState.refillLeftToRight(currentState.getPieces());
						currentState = newState;
						notifyObservers();
						scoreOpponent++;
					}else if(incoming.getType() == NetworkProtocol.RIGHTTOLEFT){
						State newState = new State(board);
						newState.refillRightToLeft(currentState.getPieces());
						currentState = newState;
						notifyObservers();
						scoreOpponent++;
					}
				}
			}
			else{
				if(won){
					if(client) currentState.setCurrentInitial(Xterm.getInitialPositionBlack());
					else currentState.setCurrentInitial(Xterm.getInitialPositionWhite());
					won = false;
				}
				sendMove();
			}
		}
		if(scorePersonal>scoreOpponent) System.out.println("Player 1 has won");
		else System.out.println("Player 2 has won");
		
	}
	
	
	private void sendMove() {
		try {
			
			while(true){
				Move move = new Move(currentState.getCurrentInitial(),Xterm.getPositionInput());
				int curSumo = currentState.getPiece(currentState.getCurrentInitial()).getSumo();
				if(GameRules.isLegalMove(currentState, move, curSumo)){
					if(GameRules.isWinningMove(currentState, move)){
						currentState.getPiece(move.getInitial()).setSumo(curSumo + 1);
						State newState = new State(board);
						newState.refillRightToLeft(currentState.getPieces());
						currentState = newState;
						notifyObservers();
						scorePersonal++;
						NetworkProtocol direction = new NetworkProtocol(NetworkProtocol.LEFTTORIGHT);
						oos.writeObject(direction);
						yourTurn = true;
						won = true;
						break;
					}
					currentState.move(move);
					
					if(curSumo > 0 && currentState.isSumoPushable(move, client ? Color.WHITE : Color.BLACK,curSumo)){
						
						Position nextPosition = currentState.sumoPush(move.getTarget(), client ? Color.WHITE : Color.BLACK);
						
						currentState.setCurrentInitial(currentState.getPiecePosition(client ? Color.WHITE : Color.BLACK,
																						board.getColor(nextPosition)));		
						
						System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
						continue;
					}
					
					notifyObservers();
					
					currentState.setCurrentInitial(currentState.getPiecePosition(client ? Color.WHITE : Color.BLACK, board.getColor(move.getTarget())));
					System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
																		+currentState.getCurrentInitial().getPosY());
					
					NetworkProtocol state = new NetworkProtocol(NetworkProtocol.STATE,currentState);
					oos.writeObject(state);
					yourTurn = false;
					System.out.println("Waiting for the opponent......");
					break;
				}else{
					System.out.println("Illegal move.");
				}
			}
			
		} catch (IOException e) {
			accepted = false;
			currentState = new State(board);
			System.out.println("The opponent has disconnected");
		}
	}
	
	private boolean receivePackage() {
		if (errors >= 10) unableToCommunicateWithOpponent = true;

		if (!unableToCommunicateWithOpponent) {
			try {
				incoming = (NetworkProtocol) ois.readObject();
				
				if(incoming==null)return false;
				
				return true;
			} catch (IOException e) {
				e.printStackTrace();
				errors++;
				return false;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				errors++;
				return false;
			}
		}
		return false;
	}
	 
	private void receiveMove(){
		currentState = (State) incoming.getMessage();
		notifyObservers();
		System.out.println("The next piece to move is " + currentState.getCurrentInitial().getPosX() + " "
				+currentState.getCurrentInitial().getPosY());
		yourTurn = true;
	}

	public static void main(String[] args) {
		NetworkGameDriver gameDriver = new NetworkGameDriver(1,Board.RANDOM);
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
