
public class GameRules {
	
	static int initialX,initialY,targetX,targetY;
	
	public GameRules(){
		
	}

	public static boolean isLegalMove(State state, Move move){

		initXY(move);
		
		if (state.getPiece(new Position(targetX,targetY)) != null) 
			return false;
		
		Piece piece = state.getPiece(new Position(initialX,initialY));
		
		if(piece.getPlayerColor() == Color.BLACK){
			if(initialX < targetX)
				return false;
			return true;
				
		}else if(piece.getPlayerColor() == Color.WHITE){
			if(initialX > targetX)
				return false;
			return true;
		}
		return false;
	}
	
	public static boolean isWinningMove(State state, Move move){
	
		initXY(move);
		
		Piece piece = state.getPiece(new Position(initialX,initialY));
		if(piece.getPlayerColor() == Color.BLACK){
			if(targetX == 7)
				return true;
				
		}else if(piece.getPlayerColor() == Color.WHITE){
			if(targetY == 0 )
				return true;
		}
		return false;
	}
	
	public static boolean isInDeadLock(State state, Move move){
		
		return false;
	}
	
	public static void initXY(Move move){
		initialX = move.getInitial().getPosX();
		initialY = move.getInitial().getPosY();
		targetX = move.getTarget().getPosX();
		targetY = move.getTarget().getPosY();
	}
}
