package Model;

public class StatsMember {

	private int wins,losses;
	private String name;
	
	public StatsMember(String name, int wins,int losses){
		this.name = name;
		this.wins = wins;
		this.losses = losses;
	}
	
	public int getWins() {
		return wins;
	}

	public int getLosses() {
		return losses;
	}

	public String getName() {
		return name;
	}

	
}

