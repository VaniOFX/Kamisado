package Model;

import java.util.ArrayList;

public class GameRules {
	
	
	public static  ArrayList<Position> legalPositions(State state, Position initial){
		
		int initialX = initial.getPosX();
		ArrayList<Position> positions = new ArrayList<Position>();
		Piece piece = state.getPiece(initial);
		Position pos;
		
		if(piece.getPlayerColor() == Color.BLACK){
			for(int x = initialX+1; x < 8; x++){
				for(int y = 0; y < 8; y++){
					pos = new Position(x,y);
					if(isLegalMove(state, new Move(initial, pos),state.getPiece(initial).getSumo()))
						positions.add(pos);
				}
			}
		}else if(piece.getPlayerColor() == Color.WHITE){
			for(int x = initialX-1; x >= 0; x--){
				for(int y = 0; y < 8; y++){
					pos = new Position(x,y);
					if(isLegalMove(state, new Move(initial, pos),state.getPiece(initial).getSumo()))
						positions.add(pos);
				}
			}
		}
		return positions;
		
	}
	
	public static boolean isLegalMove(State state, Move move,int sumo){
		int limit = 0;
		if(sumo == 0) limit = 7;
		else if(sumo == 1) limit = 5;
		else if(sumo == 2) limit = 3;
		else if(sumo == 3) limit = 1;
		
		int initialX = move.getInitial().getPosX();
		int initialY = move.getInitial().getPosY();
		int targetX = move.getTarget().getPosX();
		int targetY = move.getTarget().getPosY();
		
		if (state.getPiece(move.getTarget()) != null) 
			return false;
		
		Piece piece = state.getPiece(move.getInitial());
		
		if(piece.getPlayerColor() == Color.BLACK){
			if(initialX < targetX){
				//check if target position is within range
				if(targetX - initialX > limit) return false;
				//straight
				if(initialY == targetY){
					int x = initialX+1;
					while(x != targetX){
						if(state.getPiece(new Position(x, initialY)) != null) return false;
						x++;
					}					
				}//left diagonal
				else if((targetX - initialX) == (initialY - targetY)){
					int x = initialX+1;
					int y = initialY-1;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x++;
						y--;
					}
				}//right diagonal
				else if((targetX - initialX) == (targetY - initialY)){
					int x = initialX+1;
					int y = initialY+1;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x++;
						y++;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
			
				
		}else if(piece.getPlayerColor() == Color.WHITE){
			if(initialX > targetX){
				//check if target position is within range
				if(initialX - targetX > limit) return false;
				//straight
				if(initialY == targetY){
					int x = initialX-1;
					while(x != targetX){
						if(state.getPiece(new Position(x, initialY)) != null) return false;
						x--;
					}
				}//left diagonal
				else if((initialX - targetX) == (initialY - targetY)){
					int x = initialX-1;
					int y = initialY-1;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x--;
						y--;
					}
					
				}//right diagonal
				else if((initialY - targetY) == (targetX - initialX)){
					int x = initialX-1;
					int y = initialY+1;
					while(x != targetX && y != targetY){
						if(state.getPiece(new Position(x, y)) != null) return false;
						x--;
						y++;
					}
				}else{
					return false;
				}
			}else{
				return false;
			}
				
		}
		return true;
	}
	
	public static boolean isWinningMove(State state, Move move){
	
		
		int targetX = move.getTarget().getPosX();
		
		Piece piece = state.getPiece(move.getInitial());
		
		if(piece.getPlayerColor() == Color.BLACK){
			if(targetX == 7)
				return true;
				
		}else if(piece.getPlayerColor() == Color.WHITE){
			if(targetX == 0 )
				return true;
		}
		return false;
	}
	
	public static boolean hasNoLegalMoves(State state, Position initial){
		
		return legalPositions(state, initial).size() == 0;
	}
	
}
