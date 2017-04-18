package Model;


public interface AbstractPlayer extends java.io.Serializable {
	public Move getMove(State state);
	public Color getColor();
	public Position getInitialPosition();
	public String getName();
}
