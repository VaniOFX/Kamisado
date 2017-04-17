package Controller;
import Model.*;
import java.awt.TextField;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class GameController{

	private ArrayList<Observer> observers;
	
	private int mode;
	private int timer;
	private int difficulty;
	private String playerWhite;
	private String playerBlack;
	private int piecesSelected;
	
	@FXML
	private ToggleButton normalMode;
	@FXML
	private ToggleButton speedMode;
	@FXML
	private ToggleGroup modeGroup;
	@FXML
	private ToggleButton whitePiece;
	@FXML
	private ToggleButton blackPiece;
	@FXML
	private ToggleGroup piecesGroup;
	@FXML
	private Slider diff;
	@FXML
	private TextField singlePlayerName;
	@FXML
	private TextField whitePlayerName;
	@FXML
	private TextField blackPlayerName;
	@FXML
	private Button sButton;
	@FXML
	private Button mButton;
	@FXML
	private Button netButton;
	
	//Game settings 
	private static final int SPEEDMODE = 10;
	private static final int NORMALMODE = 11;
	
	private static final int WHITEPIECES = 12;
	private static final int BLACKPIECES = 13;
	
	//Scene Modes
	public static final String MAINMENU = "View/MainMenu.fxml";
	public static final String SMENU = "View/SingleGameMenu.fxml";
	public static final String MMENU = "View/MultiplayerGameMenu.fxml";
	public static final String NMENU = "View/NetworkGameMenu.fxml";
	public static final String GAMEVIEW = "View/GameView.fxml";
	
	
	public GameController(){
		observers = new ArrayList<Observer>();
	}

	public void handleMenus(ActionEvent e) throws IOException{
		System.out.println("blabla");
		if(e.getSource() == sButton ){
			showScene(SMENU,sButton);
		}else if(e.getSource() == mButton){
			showScene(MMENU,mButton);
		}else if(e.getSource() == netButton){
			showScene(NMENU,netButton);
		}
	}
	
	public void showScene(String location,Button butt) throws IOException{
		Parent root;
		Stage stage = (Stage) butt.getScene().getWindow();
		root = FXMLLoader.load(getClass().getResource(location));
		stage.setScene(new Scene(root));
		stage.show();
		
	}
	public void startMultiplayerGame(){
		
	}
	
	public void startNetworkGame(){
		
	}
	
	public void selectGameMode(){
		modeGroup.selectedToggleProperty().addListener(e -> { 
			if(normalMode.isSelected()) System.out.println("normal");
			else if (speedMode.isSelected()) System.out.println("speed");		
		});
	}
	
	public void selectPieces(){
		piecesGroup.selectedToggleProperty().addListener(e -> { 
			if(whitePiece.isSelected()) System.out.println("normal");
			else if (blackPiece.isSelected()) System.out.println("speed");		
		});
	}

	public void setSinglePlayerName(){
		
	}
	
	public void selectDifficulty(){
		
	}
	
	public void setTimer(){
		
	}
	
	public void setMultiplayerNames(){
		
	}
	
	public void playSingleGame(){

	}
	
	public void playMultiplayerGame(){
		
	}
	
	public void playNetworkGame(){
		
	}
	
	
}
