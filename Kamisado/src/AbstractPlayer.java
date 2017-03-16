
public interface AbstractPlayer {
	public Move getMove(Position initial);
	public Color getColor();
	public Position getInitialPosition();
	public String getName();
}
