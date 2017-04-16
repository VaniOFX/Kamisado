import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class NetworkGameDriver implements Runnable {

	private String ip = "localhost";
	private int port = 7;
	private Thread thread;
	
	private Socket socket;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	private ServerSocket serverSocket;
	
	private boolean client = true;
	private boolean accepted = false;
	private boolean unableToCommunicateWithOpponent = false;

	private boolean won = false;
	private boolean enemyWon = false;
	
	private int errors;
	
	private Board board;
	private State currentState;
	private ArrayList<Observer> observers;
	private int scoreWhite,scoreBlack;
	private boolean yourTurn = false;

	
	public NetworkGameDriver(){
		
		if(!connect()) initializeServer();
		
		board = new Board(0);
		currentState = new State(board);
		
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
			System.out.println("Unable to connect to the address: " + ip + ":" + port + " | Starting a server");
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
			System.out.println("CLIENT HAS REQUESTED TO JOIN, AND WE HAVE ACCEPTED");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while(true){
			if(!client && !accepted){
				listenForServerRequest();
				currentState.setCurrentInitial(Xterm.getInitialPosition());
			}
			
			if(!yourTurn) receiveMove();
			else makeMove();
			
			
		}
		
	}
	
	private void makeMove() {
		try {
			
			while(true){
				Move move = new Move(currentState.getCurrentInitial(),Xterm.getPositionInput());
				int curSumo = currentState.getPiece(currentState.getCurrentInitial()).getSumo();
				if(GameRules.isLegalMove(currentState, move, curSumo)){
					currentState.move(move);
					
					if(curSumo > 0 && currentState.isSumoPushable(move.getTarget(), client ? Color.WHITE : Color.BLACK)){
						Position nextPosition = currentState.sumoPush(move.getTarget(), client ? Color.WHITE : Color.BLACK);
						currentState.setCurrentInitial(currentState.getPiecePosition(client ? Color.WHITE : Color.BLACK, board.getColor(move.getTarget())));						System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());

						continue;
					}
					
					currentState.printState();
					
					currentState.setCurrentInitial(currentState.getPiecePosition(client ? Color.WHITE : Color.BLACK, board.getColor(move.getTarget())));
					System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
					
					NetworkProtocol state = new NetworkProtocol(0,currentState);
					oos.writeObject(state);
					yourTurn = false;
					break;
				}else{
					System.out.println("Illegal move.");
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void receiveMove() {
		if (errors >= 10) unableToCommunicateWithOpponent = true;

		if (!unableToCommunicateWithOpponent) {
			try {
				NetworkProtocol state = (NetworkProtocol) ois.readObject();
				currentState = (State) state.getMessage();
				currentState.printState();
				System.out.println(currentState.getCurrentInitial().getPosX()+" "+currentState.getCurrentInitial().getPosY());
				
				yourTurn = true;
			} catch (IOException e) {
				e.printStackTrace();
				errors++;
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				errors++;
			}
		}		
	}

	public static void main(String[] args) {
		NetworkGameDriver gamedriver = new NetworkGameDriver();
	}


	
}
