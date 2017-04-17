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
		AnchorPane ap = new AnchorPane();
		//Parent root = FXMLLoader.load(getClass().getResource("View/MainMenu.fxml"));
		menu1 = new Scene(ap, 800, 500);
		
		primaryStage.setScene(menu1);
		primaryStage.show();

	}

		
	

}
