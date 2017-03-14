import java.util.Scanner;

public class Xterm{
	
	private Scanner in;
	
	public Xterm(){
		in = new Scanner(System.in);
	}

	public String setWhitePlayer(){
		System.out.println("Enter player name for white pieces");
		String name;
		while(!in.hasNext()){
			in.next();
		}
		name = in.next();
		checkQuit(name);
		return name;

	}
	
	public String setBlackPlayer(){
		System.out.println("Enter player name for black pieces");
		String name;
		while(!in.hasNext()){
			in.next();
		}
		name = in.next();
		checkQuit(name);
		return name;

	}
	
	public Position getPositionInput(){
		int x,y;
		System.out.println("Enter X and Y coordinates for the desired move");
		do{
			System.out.println("Please enter a number between 1 and 8 for X:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			x = in.nextInt();
		}while(x>8 || x == 0);
		
		do{
			System.out.println("Please enter a number between 1 and 8 for Y:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			y = in.nextInt();
		}while(y>8 || y == 0);
		
		System.out.println("The cordinates are "+ x +" and " + y);
		return new Position(x,y);
	}
	
	public void checkQuit(String quit){
		if(quit.trim().toLowerCase().equals("quit"))
			System.out.println("Quitting...");
			System.exit(0);
	}
	
}
