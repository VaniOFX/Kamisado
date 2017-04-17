package Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Stack;

public class SaveManager {
	public static void saveGame(DuoGameDriver game){
		
		try {
			ObjectOutputStream outputWriter = new ObjectOutputStream(new FileOutputStream("gameDriver.ser"));
			outputWriter.writeObject(game);
			outputWriter.flush();  
			outputWriter.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static DuoGameDriver restoreState(){
		ObjectInputStream inputReader;
		try {
			inputReader = new ObjectInputStream(new FileInputStream("gameDriver.ser"));
			DuoGameDriver game = (DuoGameDriver) inputReader.readObject();
			inputReader.close();
			return game;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
