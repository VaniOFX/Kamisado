package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveManager {
	public static void saveGame(GameDriver game){
		
		try {
			ObjectOutputStream outputWriter = new ObjectOutputStream(new FileOutputStream("gameDriver.ser"));
			outputWriter.writeObject(game);
			outputWriter.flush();  
			outputWriter.close(); 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static GameDriver restoreState(){
		ObjectInputStream inputReader;
		try {
			inputReader = new ObjectInputStream(new FileInputStream("gameDriver.ser"));
			GameDriver game = (GameDriver) inputReader.readObject();
			inputReader.close();
			return game;
		} catch (FileNotFoundException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (ClassNotFoundException e) {
			return null;
		}
	}
}
