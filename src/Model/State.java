package Model;

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
	
	public State(Piece[][] pieces){
		this.pieces = pieces;
	}
	
	private void initPieces(){
		// Initialize pieces
		pieces = new Piece[8][8];
		pieces[0][0] = new Piece(Color.BLACK, board.getColor(new Position(0,0)),0);
		pieces[0][1] = new Piece(Color.BLACK, board.getColor(new Position(0,1)),0);
		pieces[0][2] = new Piece(Color.BLACK, board.getColor(new Position(0,2)),0);
		pieces[0][3] = new Piece(Color.BLACK, board.getColor(new Position(0,3)),0);
		pieces[0][4] = new Piece(Color.BLACK, board.getColor(new Position(0,4)),0);
		pieces[0][5] = new Piece(Color.BLACK, board.getColor(new Position(0,5)),0);
		pieces[0][6] = new Piece(Color.BLACK, board.getColor(new Position(0,6)),0);
		pieces[0][7] = new Piece(Color.BLACK, board.getColor(new Position(0,7)),0);
		
		pieces[7][0] = new Piece(Color.WHITE, board.getColor(new Position(7,0)),0);
		pieces[7][1] = new Piece(Color.WHITE, board.getColor(new Position(7,1)),0);
		pieces[7][2] = new Piece(Color.WHITE, board.getColor(new Position(7,2)),0);
		pieces[7][3] = new Piece(Color.WHITE, board.getColor(new Position(7,3)),0);
		pieces[7][4] = new Piece(Color.WHITE, board.getColor(new Position(7,4)),0);
		pieces[7][5] = new Piece(Color.WHITE, board.getColor(new Position(7,5)),0);
		pieces[7][6] = new Piece(Color.WHITE, board.getColor(new Position(7,6)),0);
		pieces[7][7] = new Piece(Color.WHITE, board.getColor(new Position(7,7)),0);
	}
	
	
	public void move(Move move){
		Position initial = move.getInitial();
		Position target = move.getTarget();
		lastPieceOn = target;
		pieces[target.getPosX()][target.getPosY()] = pieces[initial.getPosX()][initial.getPosY()];
		pieces[initial.getPosX()][initial.getPosY()] = null;
	}
	
	public State clone(){
		State state = new State(pieces);
		state.setCurrentInitial(currentInitial);
		return state;
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
					System.out.print("|______|");
				}else{
					System.out.print("|"+ pieces[i][j].getPlayerColor().toString().charAt(0)+","+ pieces[i][j].getColor().toString().substring(0, 2)+"," + pieces[i][j].getSumo()+"|");
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
	
	public void refillRightToLeft(Piece[][] piecesRef){
		int nextToFill  = 0;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				if(piecesRef[row][col]==null)
					continue;
				
				if(piecesRef[row][col].getPlayerColor().equals(Color.BLACK)){
					pieces[0][nextToFill] = piecesRef[row][col];
					nextToFill++;
				}	
			}
		}
		
		nextToFill  = 7;
		for(int row = 7; row >= 0; row--){
			for(int col = 7; col >= 0; col--){
				if(piecesRef[row][col]==null)
				 	continue;
				
				if(piecesRef[row][col].getPlayerColor().equals(Color.WHITE)){
					pieces[7][nextToFill] = piecesRef[row][col];
					nextToFill--;
				}	
			}
		}
	}
	
	public void refillLeftToRight(Piece[][] piecesRef){
		int nextToFill  = 7;
		for(int row = 0; row < 8; row++){
			for(int col = 7; col >= 0; col--){
				if(piecesRef[row][col]==null)
					continue;
				
				if(piecesRef[row][col].getPlayerColor().equals(Color.BLACK)){
					pieces[0][nextToFill] = piecesRef[row][col];
					nextToFill--;
				}	
			}
		}
		
		nextToFill  = 0;
		for(int row = 7; row >= 0; row--){
			for(int col = 0; col < 8; col++){
				if(piecesRef[row][col]==null)
					continue;
				
				if(piecesRef[row][col].getPlayerColor().equals(Color.WHITE)){
					pieces[7][nextToFill] = piecesRef[row][col];
					nextToFill++;
				}	
			}
		}
	}
	
	public Position sumoPush(Position initial, Color col){
		int initialX = initial.getPosX();
		int initialY = initial.getPosY();
		Position nextPosition = null;
		int count = 1;
		if(col.equals(Color.BLACK)){
			while(pieces[initialX+1][initialY] != null){
				count++;
				initialX++;
			}
			initialX = initial.getPosX();
			nextPosition = new Position(initialX+count, initialY);
			while(count > 0){
				pieces[initialX+count][initialY] = pieces[initialX+count-1][initialY];
				pieces[initialX+count-1][initialY] = null;
				count--;
			}
		}
		else if(col.equals(Color.WHITE)){
			while(pieces[initialX-1][initialY] != null){
				count++;
				initialX--;
			}
			initialX = initial.getPosX();
			nextPosition = new Position(initialX-count, initialY);
			while(count > 0){
				printState();
				pieces[initialX-count][initialY] = pieces[initialX-count+1][initialY];
				printState();
				pieces[initialX-count+1][initialY] = null;
				count--;
			}
		}
		return nextPosition;
	}
	
	public boolean isSumoPushable(Move move,Color col,int curSumo){
		Position target = move.getTarget();
		int targetX = target.getPosX();
		int targetY = target.getPosY();
		if(move.getInitial().getPosY() == move.getTarget().getPosY()){
			if(col.equals(Color.BLACK)){
				if(targetX + 1 <= 7 && pieces[targetX+1][targetY] == null){
					int numPieces = 1;
					int x = move.getInitial().getPosX() +1;
					while(pieces[x][targetY] != null){
						if(numPieces > curSumo || x+1>7 || pieces[x][targetY].getSumo()>= curSumo) return false;
						numPieces++;
						x++;
					}
					for(int i = x; i < i + numPieces; i++){
						if(i > 7 || pieces[i][targetY] != null) return false;
					}
				}else{
					return false;
				}
				
				
			}else if(col.equals(Color.WHITE)){
				if(targetX - 1 >= 0 && pieces[targetX-1][targetY] == null){
					int numPieces = 1;
					int x = move.getInitial().getPosX()-1;
					while(pieces[x][targetY] != null){
						if(numPieces > curSumo || x-1<0 || pieces[x][targetY].getSumo()>= curSumo) return false;
						numPieces++;
						x--;
					}
					for(int i = x; i >= x - numPieces; i--){
						if(i < 0 || pieces[i][targetY] != null) return false;
					}
				}else{
					return false;
				}
			}
			return true;
		}
		return false;
	}
}


