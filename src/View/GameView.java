package View;
import Model.Board;
import Model.Move;
import Model.Observer;
import Model.Position;
import Model.State;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;


public class GameView implements Observer, Runnable{
	

	private  BoardView bv;
	@FXML
	private BorderPane borderPane;
	
	public Parent createGameView(){
		
		borderPane = new BorderPane();
		HBox top = new HBox();
		Label wPlayerName = new Label("Jim");
		Label bPlayerName = new Label("Jack");
		Label wPlayerScore = new Label("0");
		Label bPlayerScore = new Label("1");
		Button pauseButton = new Button("||");
		Region reg1 = new Region();
		Region reg2 = new Region();
		HBox.setHgrow(reg1, Priority.ALWAYS);
		HBox.setHgrow(reg2, Priority.ALWAYS);
		top.getChildren().addAll(wPlayerName, reg1, wPlayerScore, pauseButton,bPlayerScore, reg2, bPlayerName);
		borderPane.setTop(top);
		
		
		
		bv = new BoardView();
//		State state = new State(new Board(0));
//		state.move(new Move(new Position(0,1),new Position(1,1)));
//		state.move(new Move(new Position(0,3),new Position(3,3)));
//		Scene gameScene = new Scene(bv.update(state));
		borderPane.setCenter(bv.createBoard());
		return borderPane;

	}

	@Override
	public void update(State currentState) {
		borderPane.setCenter(bv.update(currentState));
		synchronized(currentState) {
	        notify();
	      }
	}


	@Override
	public void run() {
		
		
	}

}
