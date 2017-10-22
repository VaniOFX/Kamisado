package View;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class KamisadoView extends Application{
	
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("FXMLFiles/MainMenu.fxml"));
<<<<<<< HEAD
//		GameView gv = new GameView();
//		Parent root = gv.createGameView();
		primaryStage.setScene(new Scene(root));
=======
//		Parent root =  new StatsView();
		primaryStage.setScene(new Scene(root, 800, 500));
>>>>>>> a0082088fa3b5f2d8d104a97ebecbb985fd3ec60
		primaryStage.show();

	}

		
	

}
