
public class GameRules {
	
	public GameRules(){
		
	}

	public static boolean isLegalMove(Board board, Move move){
		int initialX = move.getInitial().getPosX();
		int initialY = move.getInitial().getPosY();
		int targetX = move.getTarget().getPosX();
		int targetY = move.getTarget().getPosY();
		if (board.pieces[targetX][targetY] != null) return false;
		Piece piece = board.pieces[initialX][initialY];
		if(piece.getPlayerColor() == Color.BLACK){
			
		}else if(piece.getPlayerColor() == Color.WHITE){
			
		}
		return false;
	}
	
	public static boolean isWinningMove(Board board, Move move){
		return false;
	}
	
	public static boolean isInDeadLock(Board board, Move move){
		return false;
	}
}
