package Model;


public class Move {
	private Position initial;
	private Position target;
	
	public Move(Position initial,Position target){
		this.initial = initial;
		this.target = target;
	}

	public Position getInitial() {
		return initial;
	}

	public Position getTarget() {
		return target;
	}
	
	
}
