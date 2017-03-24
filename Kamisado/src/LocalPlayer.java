 
public class LocalPlayer implements AbstractPlayer{

	private Color playerColor;
	private String name;
	
	public LocalPlayer(String name, Color playerColor){
		this.name = name;
		this.playerColor = playerColor;
	}

	public Move getMove(Position initial) {
		return new Move(initial, Xterm.getPositionInput());
	}
	
	public Position getInitialPosition(){
		return Xterm.getInitialPosition();
	}
	
	public Color getColor() {
		return playerColor;
	}
	
	public String getName(){
		return name;
	}

	@Override
	public Move getMove(State state) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
