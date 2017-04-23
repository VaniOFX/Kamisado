package Controller;

import java.io.IOException;

import Model.GameDriver;
import Model.SaveManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainMenuController {

	@FXML
	private Button sButton;
	@FXML
	private Button mButton;
	@FXML
	private Button netButton;
	@FXML
	private Button playButton;
	@FXML
	private Button exitButton;
	@FXML
	private Button loadGameButton;
	
	public static final String MAINMENU = "../View/FXMLFiles/MainMenu.fxml";
	public static final String SMENU = "../View/FXMLFiles/SingleGameMenu.fxml";
	public static final String MMENU = "../View/FXMLFiles/MultiplayerGameMenu.fxml";
	public static final String NMENU = "../View/FXMLFiles/NetworkGameMenu.fxml";
	public static final String GAMEVIEW = "../View/FXMLFiles/GameView.fxml";
	
	@FXML
	public void initialize(){

		if(SaveManager.restoreState() == null) loadGameButton.setDisable(true);
	}
	
	public void handleMenus(ActionEvent e) throws IOException{

		if(e.getSource() == sButton ){
			showScene(SMENU,sButton);
		}else if(e.getSource() == mButton){
			showScene(MMENU,mButton);
		}else if(e.getSource() == netButton){
			showScene(NMENU,netButton);
		}else if(e.getSource() == exitButton){
			Stage stage = (Stage) exitButton.getScene().getWindow();
			stage.close();
		}else if(e.getSource() == loadGameButton){
			loadGame();
		}
	}
	
	public void showScene(String location,Button butt) throws IOException{
		Stage stage = (Stage) butt.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(location));
		stage.setScene(new Scene(root,800,600));
		stage.show();
		
	}
	
	private void loadGame() {
		GameDriver game = SaveManager.restoreState();
		if(game != null) game.playGame(-1);
	}
}
