
public class Piece {
	
	private Color playerColor;
	private Color color;
	
	public Piece(Color playerColor, Color color){
		//should we validate the arguments???
		this.playerColor=playerColor;
		this.color = color;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public Color getColor() {
		return color;
	}

}
