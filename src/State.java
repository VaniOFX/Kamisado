import java.util.Arrays;

public class State implements java.io.Serializable  {
	
	private Position currentInitial;
	private Position lastPieceOn;
	private Board board;
	/**
	 * 
	 */
	private static final long serialVersionUID = 755892029592219797L;
	private Piece[][] pieces;
	
	public State(Board board){
		this.board = board;
		initPieces();
	
	}
	
	public State(Piece[][] pieces, Move move){
		this.pieces = pieces;
		Position initial = move.getInitial();
		Position target = move.getTarget();
		lastPieceOn = target;
		pieces[target.getPosX()][target.getPosY()] = pieces[initial.getPosX()][initial.getPosY()];
		pieces[initial.getPosX()][initial.getPosY()] = null;
	}
	
	public State(Piece[][] pieces){
		this.pieces = pieces;
	}
	
	private void initPieces(){
		// Initialize pieces
		pieces = new Piece[8][8];
		pieces[0][0] = new Piece(Color.BLACK, board.getColor(new Position(0,0)));
		pieces[0][1] = new Piece(Color.BLACK, board.getColor(new Position(0,1)));
		pieces[0][2] = new Piece(Color.BLACK, board.getColor(new Position(0,2)));
		pieces[0][3] = new Piece(Color.BLACK, board.getColor(new Position(0,3)));
		pieces[0][4] = new Piece(Color.BLACK, board.getColor(new Position(0,4)));
		pieces[0][5] = new Piece(Color.BLACK, board.getColor(new Position(0,5)));
		pieces[0][6] = new Piece(Color.BLACK, board.getColor(new Position(0,6)));
		pieces[0][7] = new Piece(Color.BLACK, board.getColor(new Position(0,7)));
		
		pieces[7][0] = new Piece(Color.WHITE, board.getColor(new Position(7,0)));
		pieces[7][1] = new Piece(Color.WHITE, board.getColor(new Position(7,1)));
		pieces[7][2] = new Piece(Color.WHITE, board.getColor(new Position(7,2)));
		pieces[7][3] = new Piece(Color.WHITE, board.getColor(new Position(7,3)));
		pieces[7][4] = new Piece(Color.WHITE, board.getColor(new Position(7,4)));
		pieces[7][5] = new Piece(Color.WHITE, board.getColor(new Position(7,5)));
		pieces[7][6] = new Piece(Color.WHITE, board.getColor(new Position(7,6)));
		pieces[7][7] = new Piece(Color.WHITE, board.getColor(new Position(7,7)));
	}
	
	
	public State move(Move move){
		return new State(pieces,move);
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

	public Position getCurrentInitial() {
		return currentInitial;
	}

	public void setCurrentInitial(Position currentInitial) {
		this.currentInitial = currentInitial;
	}
	
	public Piece[][] getPieces(){
		Piece [][] piecesCopy = new Piece[8][];
		for(int i = 0; i < 8; i++)
		    piecesCopy[i] = pieces[i].clone();
		return piecesCopy;
	}

	public Position getLastPieceOn() {
		return lastPieceOn;
	}

	public void setLastPieceOn(Position lastPieceOn) {
		this.lastPieceOn = lastPieceOn;
	}
	
	public Board getBoard(){
		return board;
	}

}


