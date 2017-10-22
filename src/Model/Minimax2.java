package Model;

import java.util.ArrayList;
import java.util.List;

public class Minimax2 {
	
	public static int[] minimax(int depth,Color playerColor, int alpha, int beta, State state){
		List<Position> possibleMoves = GameRules.legalPositions(state, state.getCurrentInitial());
		int score = 0;
		int bestX = -2;
		int bestY = -2;
		
		if(possibleMoves.isEmpty() || depth ==0){
			score = evaluate(state, playerColor);
			return new int[] {score,bestX,bestY};
		}
		
		for(Position p : possibleMoves){
			State newState = new State(state.getPieces());
			newState.move(new Move(state.getCurrentInitial(), p));
			newState.setLastPieceOn(p);
			newState.setCurrentInitial(newState.getPiecePosition(playerColor, new Board(0).getColor(p)));
			
			if(playerColor == Color.WHITE){
				score = minimax(depth-1,Color.BLACK,alpha,beta,state)[0];
				if(score>alpha){
					alpha = score;
					bestX = p.getPosX();
					bestY = p.getPosY();
				}
			}else{
				score = minimax(depth-1,Color.WHITE,alpha,beta,state)[0];
				if(score<beta){
					beta = score;
					bestX = p.getPosX();
					bestY = p.getPosY();
				}
			}
			if(alpha >= beta) break;
		}
		return new int[] {(playerColor == Color.WHITE) ? alpha : beta, bestX, bestY};		
	}
	
	private static int evaluate(State state, Color playerColor) {
		int eval = 0;
		
		if(isWinningState(state,playerColor)){
			eval = Integer.MAX_VALUE;
			return eval;
		}
		ArrayList<Position> blackPieces = new ArrayList<Position>();
		ArrayList<Position> whitePieces = new ArrayList<Position>();
		for(Color c : Color.values()){
			if(c.equals(Color.BLACK) || c.equals(Color.WHITE)) continue;
			blackPieces.add(state.getPiecePosition(Color.BLACK, c));
			whitePieces.add(state.getPiecePosition(Color.WHITE, c));
		}
	
		ArrayList<Position> legalPosBlack = new ArrayList<Position>();
		ArrayList<Position> legalPosWhite = new ArrayList<Position>();
		for(int i = 0; i < 8; i++){
			if(GameRules.legalPositions(state, blackPieces.get(i)).size() == 0) continue;
			if(GameRules.legalPositions(state, whitePieces.get(i)).size() == 0) continue;
			legalPosBlack.addAll(GameRules.legalPositions(state, blackPieces.get(i)));
			legalPosWhite.addAll(GameRules.legalPositions(state, whitePieces.get(i)));
			
			
			//if pieces are in the opponents side update eval
			if(playerColor == Color.BLACK){
				if(blackPieces.get(i).getPosX() > 3) eval++;
				if(whitePieces.get(i).getPosX() < 4) eval--;
			}else if(playerColor == Color.WHITE){
				if(blackPieces.get(i).getPosX() > 3) eval--;
				if(whitePieces.get(i).getPosX() < 4) eval++;
			}
		}
		
		// if pieces have opening to a winning position update eval
		if(playerColor == Color.BLACK){
			for(Position pos : legalPosBlack){
				if(pos.getPosX() == 7) eval = eval + 3;
			}
			for(Position pos : legalPosWhite){
				if(pos.getPosX() == 0) eval = eval - 3;
			}
		}else if(playerColor == Color.WHITE){
			for(Position pos : legalPosBlack){
				if(pos.getPosX() == 7) eval = eval - 3;
			}
			for(Position pos : legalPosWhite){
				if(pos.getPosX() == 0) eval = eval + 3;
			}
		}
	
		return eval;
	}

	private static boolean isWinningState(State state, Color playerColor){
		ArrayList<Position> pieces = new ArrayList<Position>();
		for(Color c : Color.values()){
			if(c.equals(Color.BLACK) || c.equals(Color.WHITE)) continue;
			pieces.add(state.getPiecePosition(playerColor, c));
		}
		for(int i = 0; i < 8; i++){
			if(playerColor == Color.BLACK){
				if(pieces.get(i).getPosX() == 7) return true;
			}else if(playerColor == Color.WHITE){
				if(pieces.get(i).getPosX() == 0) return true;
			}
		}
		return false;
		
	}
	
}
