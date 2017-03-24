
public class Piece implements java.io.Serializable {
	
	private Color playerColor;
	private Color color;
	
	public Piece(Color playerColor, Color color){
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
