
public interface AbstractPlayer extends java.io.Serializable {
	public Move getMove(Position initial);
	public Color getColor();
	public Position getInitialPosition();
	public String getName();
}
