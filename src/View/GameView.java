package View;
import java.io.Serializable;

import Controller.Controller;
import Model.Observer;
import Model.State;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class GameView extends Application implements Observer, Serializable{
	

	private BoardView bv;
	@FXML
	private BorderPane borderPane;
	
	public static void main(String[] args){
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLFiles/GameView.fxml"));
		Parent root = loader.load();
		
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
		
		
		
		bv = new BoardView(e-> System.out.println("HUI"));
		Scene gameScene = new Scene(bv);
		primaryStage.setTitle("Kamisado board");
		primaryStage.setScene(gameScene);
		primaryStage.show();

	}

	@Override
	public void update(State currentState) {
		bv.update(currentState);
	}

}
