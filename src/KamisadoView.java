import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class KamisadoView extends Application implements Observer{
	
	private GameController controller, cont;
	private Stage stage;
	private Scene menu0,menu1,gameView;
	
	public void KamisadoView(){
		
	}
	public static void main(String[] args){
		KamisadoView kv = new KamisadoView();
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//controller = new GameController();
		//controller.subscribe(this);
		stage = primaryStage;
		
		menu0 = new Scene(loadParent("Menu0.fxml"), 800, 500);
		menu1 = new Scene(loadParent("Menu1.fxml"), 800, 500);
		gameView = new Scene(loadParent("GameView.fxml"), 800, 500);
		
		primaryStage.setScene(menu1);
		primaryStage.show();

	}

	private Parent loadParent(String location){
		try {
			FXMLLoader loader = new FXMLLoader();
			return loader.load(getClass().getResource(location));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void update(int screenMode) {
		switch(screenMode){
		case 0: stage.setScene(menu0);
				stage.show();
				
		case 1: stage.setScene(menu1);
				stage.show();
				
		case 2: stage.setScene(gameView);
				stage.show();
		}
				
		}
		
	
	@Override
	public void update(State currentState) {
		
	}
}
