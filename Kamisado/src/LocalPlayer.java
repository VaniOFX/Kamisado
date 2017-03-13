
public class LocalPlayer implements AbstractPlayer{

	private Color playerColor;
	
	public LocalPlayer(Color playerColor){
		this.playerColor = playerColor;
	}

	public Move getMove(Position initial) {
		return new Move(initial, Xterm.getPositionInput());
	}
	
	public Position getInitialPosition(){
		return Xterm.getPositionInput();
	}
	
	public Color getPlayerColor() {
		return playerColor;
	}
	
	

	
}
