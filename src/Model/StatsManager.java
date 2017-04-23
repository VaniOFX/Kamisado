package Model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class StatsManager {

	public static void updateWins(AbstractPlayer winner){
		try{
			int[] arr = removePrev(winner);
			File inputFile = new File("gameStats.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile,true));
			int wins = arr[0]+1;
			writer.write(winner.getName()+" "+ wins +" "+ arr[1]+System.clearProperty("line.separator"));
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void updateLosses(AbstractPlayer loser){
		try{
			int[] arr = removePrev(loser);
			File inputFile = new File("gameStats.txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile,true));
			int losses = arr[1]+1;
			writer.write(loser.getName()+" "+ arr[0] +" "+ losses +System.clearProperty("line.separator"));
			writer.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private static int[] removePrev(AbstractPlayer winner){
		try{
			File inputFile = new File("gameStats.txt");
			File tempFile = new File("temp.txt");
	
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
	
			String lineToRemove = winner.getName();
			String currentLine;
			int wins = 0;
			int losses = 0;
			int[] arr = new int[2];
			arr[0] = wins;
			arr[1] = losses;
	
			while((currentLine = reader.readLine()) != null) {
			    // trim newline when comparing with lineToRemove
				StringTokenizer st = new StringTokenizer(currentLine);
				String name = st.nextToken();
			    if(name.equals(lineToRemove)){
			    	wins = Integer.parseInt(st.nextToken());
			    	losses = Integer.parseInt(st.nextToken());
			    	arr[0] = wins;
			    	arr[1] = losses; 
			    	continue;
			    }
			    writer.write(currentLine + System.getProperty("line.separator"));
			}
			reader.close();
			writer.close();
			inputFile.delete();
			boolean successful = tempFile.renameTo(inputFile);
			return arr;
		}
		catch(IOException e){
			
		}
		return null;
		
		
	}
}
