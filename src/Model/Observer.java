package Model;

public interface Observer {

	public void update(State currentState);

	public void update(int screenMode);
}
