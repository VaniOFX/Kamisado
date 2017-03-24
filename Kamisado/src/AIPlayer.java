import java.util.ArrayList;
import java.util.List;

public class AIPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	
	public AIPlayer(String name, Color playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}
	
	@Override
	public Move getMove(State state){
		Position targetPos = Minimax.minimax(state, this, 3);
		return new Move(state.getCurrentInitial(),targetPos);
		
	}
	

	@Override
	public Color getColor() {
		return playerColor;
	}

	@Override
	public Position getInitialPosition() {
		return Xterm.getInitialPosition();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Move getMove(Position initial) {
		// TODO Auto-generated method stub
		return null;
	}

//	private int[] minimax(int depth,AbstractPlayer player, int alpha, int beta, DuoGameDriver game){
//		List<Position> possibleMoves = generateMoves(game);
//		int score =0;
//		int bestX = -2;
//		int bestY = -2;
//		
//		if(possibleMoves.isEmpty() || depth ==0){
//			score = evaluate();
//			return new int[] {score,bestX,bestY};
//		} else{
//			for(Position position : possibleMoves){
//				if(player == game.getPlayerWhite()){
//					score = minimax(depth-1,game.getPlayerBlack(),alpha,beta,game)[0];
//					if(score>alpha){
//						alpha = score;
//						bestX = position.getPosX();
//						bestY = position.getPosY();
//					}
//				}else{
//					score = minimax(depth-1,game.getPlayerWhite(),alpha,beta,game)[0];
//					if(score<beta){
//						beta = score;
//						bestX = position.getPosX();
//						bestY = position.getPosY();
//					}
//				}
//			}
//		}
//
//		return new int[] {(player == game.getPlayerWhite()) ? alpha : beta, bestX, bestY};
//		
//	}
//	
//	private int evaluate(State state, AbstractPlayer player) {
//		int eval = 0;
//		
//		if(isWinningState(state,player)){
//			eval = Integer.MAX_VALUE;
//			return eval;
//		}
//		ArrayList<Position> blackPieces = new ArrayList<Position>();
//		ArrayList<Position> whitePieces = new ArrayList<Position>();
//		for(Color c : Color.values()){
//			if(c.equals(Color.BLACK) || c.equals(Color.WHITE)) continue;
//			blackPieces.add(state.getPiecePosition(Color.BLACK, c));
//			blackPieces.add(state.getPiecePosition(Color.WHITE, c));
//		}
//	
//		ArrayList<Position> legalPosBlack = new ArrayList<Position>();
//		ArrayList<Position> legalPosWhite = new ArrayList<Position>();
//		for(int i = 0; i < 8; i++){
//			legalPosBlack.addAll(GameRules.legalPositions(state, blackPieces.get(i)));
//			legalPosWhite.addAll(GameRules.legalPositions(state, whitePieces.get(i)));
//			
//			//if pieces are in the opponents side update eval
//			if(player.getColor() == Color.BLACK){
//				if(blackPieces.get(i).getPosX() > 3) eval++;
//				if(whitePieces.get(i).getPosX() < 4) eval--;
//			}else if(player.getColor() == Color.WHITE){
//				if(blackPieces.get(i).getPosX() > 3) eval--;
//				if(whitePieces.get(i).getPosX() < 4) eval++;
//			}
//		}
//		
//		// if pieces have opening to a winning position update eval
//		if(player.getColor() == Color.BLACK){
//			for(Position pos : legalPosBlack){
//				if(pos.getPosX() == 7) eval = eval + 3;
//			}
//			for(Position pos : legalPosWhite){
//				if(pos.getPosX() == 0) eval = eval - 3;
//			}
//		}else if(player.getColor() == Color.WHITE){
//			for(Position pos : legalPosBlack){
//				if(pos.getPosX() == 7) eval = eval - 3;
//			}
//			for(Position pos : legalPosWhite){
//				if(pos.getPosX() == 0) eval = eval + 3;
//			}
//		}
//	
//		return eval;
//	}
//
//	private boolean isWinningState(State state, AbstractPlayer player){
//		ArrayList<Position> pieces = new ArrayList<Position>();
//		for(Color c : Color.values()){
//			if(c.equals(Color.BLACK) || c.equals(Color.WHITE)) continue;
//			pieces.add(state.getPiecePosition(player.getColor(), c));
//		}
//		for(int i = 0; i < 8; i++){
//			if(player.getColor() == Color.BLACK){
//				if(pieces.get(i).getPosX() == 7) return true;
//			}else if(player.getColor() == Color.WHITE){
//				if(pieces.get(i).getPosX() == 0) return true;
//			}
//		}
//		return false;
//		
//	}
//	
////	private List<Position> generateMoves(DuoGameDriver game){
////		List<Position> possibleMoves = new ArrayList<Position>();
////		Position currentInitial = game.getCurrentPosition();
////		State currentState = game.getCurrentState();
////		for(int i = 0; i < 8; i++){
////			for(int j = 0; j < 8; j++){
////				if(GameRules.isLegalMove(currentState, new Move(currentInitial,new Position(i,j)))){
////					possibleMoves.add(new Position(i,j));
////				}
////			}
////		}
////		return possibleMoves;
////	}
//	
//	private int evaluate(){
//		return 0;
//	}
}
