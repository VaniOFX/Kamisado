package Model;

import View.BoardView;

public class LocalPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	
	public LocalPlayer(String name, Color playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}
	
	public Move getMove(State state) {
		Position initial = state.getCurrentInitial();
		int x,y;
		Position pos;
//		Position pos = null;
//		if(playerColor == Color.WHITE) pos = Xterm.getInitialPositionWhite();
//		else if (playerColor == Color.BLACK) pos = Xterm.getInitialPositionBlack();
		do{
			pos = BoardView.getPosition();
			x = pos.getPosX();
			y = pos.getPosY();
		}while(x < 0 && y < 0);
		return new Move(initial,pos);
	}
	
	public Color getColor() {
		return playerColor;
	}

	public Position getInitialPosition(){
		int x,y;
		Position pos;
//		Position pos = null;
//		if(playerColor == Color.WHITE) pos = Xterm.getInitialPositionWhite();
//		else if (playerColor == Color.BLACK) pos = Xterm.getInitialPositionBlack();
		do{
			pos = BoardView.getPosition();
			x = pos.getPosX();
			y = pos.getPosY();
		}while(x < 0 && y < 0);
		return pos;
	}
	
	public String getName(){
		return name;
	}
	
}
