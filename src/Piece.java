
public class Piece implements java.io.Serializable {
	
	private Color playerColor;
	private Color color;
	private int sumo;
	
	public int getSumo() {
		return sumo;
	}

	public void setSumo(int sumo) {
		this.sumo = sumo;
	}

	public Piece(Color playerColor, Color color, int sumo){
		this.playerColor=playerColor;
		this.color = color;
		this.sumo = sumo;
	}

	public Color getPlayerColor() {
		return playerColor;
	}

	public Color getColor() {
		return color;
	}

}
