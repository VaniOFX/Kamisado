
public class State {
	
	
	private Piece[][] pieces;
	
	public State(){
		
		initPieces();
	
	}

	private void initPieces(){
		// Initialise pieces
		pieces = new Piece[8][8];
		pieces[0][0] = new Piece(Color.BLACK, Color.ORANGE);
		pieces[0][1] = new Piece(Color.BLACK, Color.BLUE);
		pieces[0][2] = new Piece(Color.BLACK, Color.PURPLE);
		pieces[0][3] = new Piece(Color.BLACK, Color.PINK);
		pieces[0][4] = new Piece(Color.BLACK, Color.YELLOW);
		pieces[0][5] = new Piece(Color.BLACK, Color.RED);
		pieces[0][6] = new Piece(Color.BLACK, Color.GREEN);
		pieces[0][7] = new Piece(Color.BLACK, Color.BROWN);
		
		for(int i = 1; i < 7; i++){
			for(int j = 0; j < 8; j++){
				pieces[i][j] = null;
			}
		}
		
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
}
