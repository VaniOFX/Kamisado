import java.util.Scanner;

public class Xterm{
	
	private static Scanner in;

	public static String setWhitePlayer(){
		in = new Scanner(System.in);
		System.out.println("Enter player name for white pieces");
		String name;
		while(!in.hasNext()){
			in.next();
		}
		name = in.next();
		checkQuit(name);
		return name;

	}
	
	public static String setBlackPlayer(){
		in = new Scanner(System.in);
		System.out.println("Enter player name for black pieces");
		String name;
		while(!in.hasNext()){
			in.next();
		}
		name = in.next();
		checkQuit(name);
		return name;

	}
	
	public static Position getInitialPosition(){
		in = new Scanner(System.in);
		int x,y;
		System.out.println("Enter X and Y coordinates for the desired move");
		do{
			System.out.println("Please enter a number between 1 and 8 for X:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			x = in.nextInt();
		}while(x != 7);
		
		do{
			System.out.println("Please enter a number between 1 and 8 for Y:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			y = in.nextInt();
		}while(y > 7 || y < 0);
		
		System.out.println("The initial position is "+ x +" and " + y);
		return new Position(x,y);
	}
	
	public static Position getPositionInput(){
		in = new Scanner(System.in);
		int x,y;
		System.out.println("Enter X and Y coordinates for the desired move");
		do{
			System.out.println("Please enter a number between 1 and 8 for X:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			x = in.nextInt();
		}while(x > 7 || x < 0);
		
		do{
			System.out.println("Please enter a number between 1 and 8 for Y:");
			while(!in.hasNextInt()){
				checkQuit(in.next());
				System.out.println("Please enter an integer");
			}
			y = in.nextInt();
		}while(y > 7 || y < 0);
		
		System.out.println("The cordinates are "+ x +" and " + y);
		return new Position(x,y);
	}
	
	public static String chooseGameMode(){
		in = new Scanner(System.in);
		System.out.println("Choose game mode: normal or speed");
		String mode;
		while(!in.hasNext()){
			in.next();
		}
		do{
		mode = in.next();
		checkQuit(mode);
		}while(!((mode.trim().toLowerCase().equals("speed")||(mode.trim().toLowerCase().equals("normal")))));
		return mode;
	}
	
	public static int moveTime(){
		in = new Scanner(System.in);
		System.out.println("Choose amount of time for move in seconds");
		while(!in.hasNextInt()){
			checkQuit(in.next());
			System.out.println("Please enter an integer");
		}
		return in.nextInt()*1000;
	}
	
	private static void checkQuit(String quit){
		if(quit.trim().toLowerCase().equals("quit")){
			System.out.println("Quitting...");
			System.exit(0);
		}
	}
	
}
