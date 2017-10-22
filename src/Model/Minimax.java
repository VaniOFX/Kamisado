package Model;

import java.util.ArrayList;

public class Minimax {
	
	public static Position minimax(State state, AbstractPlayer player, int depth){
		State newState = max(state, Integer.MIN_VALUE, Integer.MAX_VALUE, player.getColor(), depth).getState();
		return newState.getLastPieceOn();
		
	}
	
	private static StateTuple min(State state, int a, int b, Color playerColor, int depth){
		
		ArrayList<Position> positions = GameRules.legalPositions(state, state.getCurrentInitial());
		if(positions.size() == 0 || depth == 0)return new StateTuple(state, evaluate(state, playerColor));
		depth--;
		int minUtility = Integer.MAX_VALUE;
		State minChild = null;
		
		ArrayList<State> children = new ArrayList<State>();
		if(playerColor == Color.BLACK){
			playerColor = Color.WHITE;
		}else{
			playerColor = Color.BLACK;
		}
		for(Position p : positions){
			State newState = new State(state.getPieces());
			newState.move(new Move(state.getCurrentInitial(), p));
			newState.setLastPieceOn(p);
			newState.setCurrentInitial(newState.getPiecePosition(playerColor, new Board(0).getColor(p)));
			children.add(newState);
		}
		
		for(State child : children){
			
			int childUtility = max(child, a, b, playerColor, depth).getValue();
			
			if(childUtility < minUtility){
				minUtility = childUtility;
				minChild = child;
			}
			
			if (minUtility <= a) break;
			
			if (minUtility < b) b = minUtility;
		}
		
		return new StateTuple(minChild, minUtility);
		
	}
	
	private static StateTuple max(State state, int a, int b, Color playerColor, int depth) {
		
		ArrayList<Position> positions = GameRules.legalPositions(state, state.getCurrentInitial());
		if(positions.size() == 0 || depth == 0)return new StateTuple(state, evaluate(state, playerColor));
		depth--;
		int maxUtility = Integer.MIN_VALUE;
		State maxChild = null;
		
		ArrayList<State> children = new ArrayList<State>();
		if(playerColor == Color.BLACK){
			playerColor = Color.WHITE;
		}else{
			playerColor = Color.BLACK;
		}
		for(Position p : positions){
			State newState = new State(state.getPieces());
			newState.move(new Move(state.getCurrentInitial(), p));
			newState.setLastPieceOn(p);
			newState.setCurrentInitial(newState.getPiecePosition(playerColor, new Board(0).getColor(p)));
			children.add(newState);
		}
		
		for(State child : children){
			
			int childUtility = min(child, a, b, playerColor, depth).getValue();
			
			if(childUtility > maxUtility){
				maxUtility = childUtility;
				maxChild = child;
			}
			
			if (maxUtility >= b) break;
			
			if (maxUtility > a) a = maxUtility;
		}
		
		return new StateTuple(maxChild, maxUtility);
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
	
	static class StateTuple{
		private State state;
		private int value;
		public StateTuple(State state, int value){
			this.state = state;
			this.value = value;
		}
		public State getState() {
			return state;
		}
		public void setState(State state) {
			this.state = state;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
	}
}
