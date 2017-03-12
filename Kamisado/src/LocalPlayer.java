
public class LocalPlayer implements AbstractPlayer{

	@Override
	public Position getMove() {
		Xterm in = new Xterm();
		return in.getTargetPositionInput();
	}

	
}
