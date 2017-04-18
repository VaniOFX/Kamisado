package Model;

 
public class LocalPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	
	public LocalPlayer(String name, Color playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}
	
	public Move getMove(State state) {
		Position initial = state.getCurrentInitial();
		return new Move(initial, Xterm.getPositionInput());
	}
	
	public Color getColor() {
		return playerColor;
	}

	public Position getInitialPosition(){
		Position pos = null;
		if(playerColor == Color.WHITE) pos = Xterm.getInitialPositionWhite();
		else if (playerColor == Color.BLACK) pos = Xterm.getInitialPositionBlack();
		return pos;
	}
	
	public String getName(){
		return name;
	}
	
}
