package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	private int depth;
	
	public AIPlayer(String name, Color playerColor, int depth){
		this.name = name;
		this.playerColor = playerColor;
		this.depth = depth;
	}
	
	@Override
	public Move getMove(State state){
		Position targetPos = Minimax.minimax(state, this, depth);
		return new Move(state.getCurrentInitial(),targetPos);
		
//		int[] position = Minimax2.minimax(depth,playerColor, Integer.MIN_VALUE, Integer.MAX_VALUE,state);
//		return new Move(state.getCurrentInitial(),new Position(position[1],position[2]));
	}
	

	@Override
	public Color getColor() {
		return playerColor;
	}

	@Override
	public Position getInitialPosition() {
		return new Position(7,new Random().nextInt(7));
	}

	@Override
	public String getName() {
		return name;
	}

}
