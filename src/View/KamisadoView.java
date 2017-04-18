package View;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;



public class KamisadoView extends Application{
	
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Scene menu1;
		Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/MainMenu.fxml"));
		
		primaryStage.setScene(new Scene(root, 800, 500));
		primaryStage.show();

	}

		
	

}
