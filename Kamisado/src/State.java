import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class State {
	
	
	private Piece[][] pieces;
	
	public State(){
		
		initPieces();
	
	}
	
	public State(String filename) throws FileNotFoundException, IOException{
		
		readFromFile(filename);
	}

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
	
	public void writeToFile() throws IOException{
		BufferedWriter outputWriter = new BufferedWriter(new FileWriter("state.txt"));
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(pieces[i][j]!= null){
					outputWriter.write(i);
					outputWriter.write(" ");
					outputWriter.write(j);
					outputWriter.write(" ");
					outputWriter.write(pieces[i][j].getColor().toString());
					outputWriter.write(" ");
					outputWriter.write(pieces[i][j].getPlayerColor().toString());
					outputWriter.newLine();
				}
			}
		}
		outputWriter.flush();  
		outputWriter.close();  
	}
	
	public void readFromFile(String filename) throws FileNotFoundException, IOException{
		pieces = new Piece[8][8];
		BufferedReader inputReader= new BufferedReader(new FileReader(filename));
		String line;
		StringTokenizer tokenizer;
		int x = 0,y = 0;
		String color = null, playerColor = null;
		for (int i = 0; i < 16; i++){
			while ((line = inputReader.readLine()) != null){
				tokenizer = new StringTokenizer(line);
				x = Integer.parseInt(tokenizer.nextToken());
				y = Integer.parseInt(tokenizer.nextToken());
				color = tokenizer.nextToken();
			    playerColor = tokenizer.nextToken(); 
			}    	
			pieces[x][y] = new Piece(Color.valueOf(playerColor),Color.valueOf(color));
		}
	}

}

