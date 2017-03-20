
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class State implements java.io.Serializable  {
	
	
	private Piece[][] pieces;
	
	public State(){
		
		initPieces();
	
	}
	/*
	public State(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		readFromFile(filename);
	}
*/
	private void initPieces(){
		// Initialize pieces
		pieces = new Piece[8][8];
		pieces[0][0] = new Piece(Color.BLACK, Color.ORANGE);
		pieces[0][1] = new Piece(Color.BLACK, Color.BLUE);
		pieces[0][2] = new Piece(Color.BLACK, Color.PURPLE);
		pieces[0][3] = new Piece(Color.BLACK, Color.PINK);
		pieces[0][4] = new Piece(Color.BLACK, Color.YELLOW);
		pieces[0][5] = new Piece(Color.BLACK, Color.RED);
		pieces[0][6] = new Piece(Color.BLACK, Color.GREEN);
		pieces[0][7] = new Piece(Color.BLACK, Color.BROWN);
		
		pieces[7][0] = new Piece(Color.WHITE, Color.BROWN);
		pieces[7][1] = new Piece(Color.WHITE, Color.GREEN);
		pieces[7][2] = new Piece(Color.WHITE, Color.RED);
		pieces[7][3] = new Piece(Color.WHITE, Color.YELLOW);
		pieces[7][4] = new Piece(Color.WHITE, Color.PINK);
		pieces[7][5] = new Piece(Color.WHITE, Color.PURPLE);
		pieces[7][6] = new Piece(Color.WHITE, Color.BLUE);
		pieces[7][7] = new Piece(Color.WHITE, Color.ORANGE);
	}
	
	
	public void move(Move move){
		Position initial = move.getInitial();
		Position target = move.getTarget();
		pieces[target.getPosX()][target.getPosY()] = pieces[initial.getPosX()][initial.getPosY()];
		pieces[initial.getPosX()][initial.getPosY()] = null;
	}
	
	public Piece getPiece(Position pos){
		return pieces[pos.getPosX()][pos.getPosY()];
	}
	
	public Position getPiecePosition(Color playerColor, Color pieceColor){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j] != null){
					if(pieces[i][j].getColor() == pieceColor 
							&& pieces[i][j].getPlayerColor() == playerColor){
						return new Position(i, j);
					}
				}
			}
		}
		return null;
	}
	
	public void printState(){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j]== null){
					System.out.print("|____|");
				}else{
					System.out.print("|"+ pieces[i][j].getPlayerColor().toString().charAt(0)+","+ pieces[i][j].getColor().toString().substring(0, 2)+"|");
				}
			}
			System.out.println();
		}
	}
/*	
	public void writeToFile() throws FileNotFoundException, IOException{
		ObjectOutputStream outputWriter = new ObjectOutputStream(new FileOutputStream("state.ser"));
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j]!= null){
					outputWriter.writeInt(i);
					outputWriter.writeInt(j);
					outputWriter.writeObject(pieces[i][j]);
				}
			}
		}
		outputWriter.flush();  
		outputWriter.close();  
	}
	
	public void readFromFile(String filename) throws FileNotFoundException, IOException, ClassNotFoundException{
		pieces = new Piece[8][8];
		ObjectInputStream inputReader= new ObjectInputStream(new FileInputStream(filename));
		for(int i = 0; i < 16;i++){
			int x = inputReader.readInt();
			int y = inputReader.readInt();
			pieces[x][y] = (Piece) inputReader.readObject();
		}
	}
	*/
}


