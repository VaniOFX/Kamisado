import java.util.ArrayList;
import java.util.List;

public abstract class AIPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	
	public AIPlayer(String name, Color playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}
	
	@Override
	public Move getMove(Position initial){
		return new Move(initial,null);
		
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

	private int[] minimax(int depth,AbstractPlayer player, int alpha, int beta, DuoGameDriver game){
		List<Position> possibleMoves = generateMoves(game);
		int score =0;
		int bestX = -2;
		int bestY = -2;
		
		if(possibleMoves.isEmpty() || depth ==0){
			score = evaluate();
			return new int[] {score,bestX,bestY};
		} else{
			for(Position position : possibleMoves){
				if(player == game.getPlayerWhite()){
					score = minimax(depth-1,game.getPlayerBlack(),alpha,beta,game)[0];
					if(score>alpha){
						alpha = score;
						bestX = position.getPosX();
						bestY = position.getPosY();
					}
				}else{
					score = minimax(depth-1,game.getPlayerWhite(),alpha,beta,game)[0];
					if(score<beta){
						beta = score;
						bestX = position.getPosX();
						bestY = position.getPosY();
					}
				}
			}
		}

		return new int[] {(player == game.getPlayerWhite()) ? alpha : beta, bestX, bestY};
		
	}
	
	private List<Position> generateMoves(DuoGameDriver game){
		List<Position> possibleMoves = new ArrayList<Position>();
		Position currentInitial = game.getCurrentPosition();
		State currentState = game.getCurrentState();
		for(int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				if(GameRules.isLegalMove(currentState, new Move(currentInitial,new Position(i,j)))){
					possibleMoves.add(new Position(i,j));
				}
			}
		}
		return possibleMoves;
	}
	
	private int evaluate(){
		return 0;
	}
}
