
public class GameRules {
	

	public static boolean isLegalMove(State state, Move move){

	
		int initialX = move.getInitial().getPosX();
		int initialY = move.getInitial().getPosY();
		int targetX = move.getTarget().getPosX();
		int targetY = move.getTarget().getPosY();
		
		if (state.getPiece(move.getTarget()) != null) 
			return false;
		
		Piece piece = state.getPiece(move.getInitial());
		
		if(piece.getPlayerColor() == Color.BLACK){
			if(initialX < targetX){
				//straight
				if(initialY == targetY){
					int x = initialX;
					while(x != targetX){
						if(state.getPiece(new Position(x, initialY)) != null) return false;
						x++;
					}
				}//left diagonal
				else if((initialX + initialX) == (initialY - targetY)){
					int x = initialX;
					int y = initialY;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x++;
						y--;
					}
				}//right diagonal
				else if((initialX + initialX) == (initialY + targetY)){
					int x = initialX;
					int y = initialY;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x++;
						y++;
					}
				}
			}
			
				
		}else if(piece.getPlayerColor() == Color.WHITE){
			if(initialX > targetX){
				//straight
				if(initialY == targetY){
					int x = initialX;
					while(x != targetX){
						if(state.getPiece(new Position(x, initialY)) != null) return false;
						x--;
					}
				}//left diagonal
				else if((initialX + initialX) == (initialY + targetY)){
					int x = initialX;
					int y = initialY;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x--;
						y--;
					}
					
				}//right diagonal
				else if((initialX + initialX) == (initialY - targetY)){
					int x = initialX;
					int y = initialY;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x--;
						y++;
					}
				}
			}
				
		}
		return true;
	}
	
	public static boolean isWinningMove(State state, Move move){
	
		
		int targetX = move.getTarget().getPosX();
		int targetY = move.getTarget().getPosY();
		
		Piece piece = state.getPiece(move.getInitial());
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
	
}
