package View;
import Controller.Controller;
import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.Serializable;


public class GameView extends Application implements Observer, Serializable{
	
	private Controller controller;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/GameView.fxml"));
		Parent root = loader.load();
		//controller = (Controller) loader.getController();
		
//		primaryStage.setTitle("Kamisado board");
//		BorderPane bp = new BorderPane();
//		HBox top = new HBox();
//		Label wPlayerName = new Label("Jim");
//		Label bPlayerName = new Label("Jack");
//		Label wPlayerScore = new Label("0");
//		Label bPlayerScore = new Label("1");
//		Button pauseButton = new Button("||");
//		Region reg1 = new Region();
//		Region reg2 = new Region();
//		HBox.setHgrow(reg1, Priority.ALWAYS);
//		HBox.setHgrow(reg2, Priority.ALWAYS);
//		top.getChildren().addAll(wPlayerName, reg1, wPlayerScore, pauseButton,bPlayerScore, reg2, bPlayerName);
//		GridPane board = new GridPane();
//		bp.setTop(top);
//		bp.setCenter(board);
		Scene gameScene = new Scene(root, 800, 500);
		primaryStage.setScene(gameScene);
		primaryStage.show();

	}


	@Override
	public void update(State currentState) {
		controller.initialize(currentState);
	}

	@Override
	public void update(int screenMode) {
		// TODO Auto-generated method stub
		
	}
}
