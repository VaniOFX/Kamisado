
public class LocalPlayer implements AbstractPlayer{

	private Color playerColor;
	private Xterm in;
	
	public LocalPlayer(Color playerColor){
		this.playerColor = playerColor;
		in = new Xterm();
	}

	public Move getMove(Position initial) {
		return new Move(initial, in.getPositionInput());
	}
	
	public Position getInitialPosition(){
		return in.getPositionInput();
	}
	
	public Color getColor() {
		return playerColor;
	}
	
	

	
}
