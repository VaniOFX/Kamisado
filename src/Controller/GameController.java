package Controller;
import Model.*;
import View.*;
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
	private String singleName;
	
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
	public static final String MAINMENU = "FXMLFiles/MainMenu.fxml";
	public static final String SMENU = "FXMLFiles/SingleGameMenu.fxml";
	public static final String MMENU = "FXMLFiles/MultiplayerGameMenu.fxml";
	public static final String NMENU = "FXMLFiles/NetworkGameMenu.fxml";
	public static final String GAMEVIEW = "FXMLFiles/GameView.fxml";
	
	
	public GameController(){
		observers = new ArrayList<Observer>();
	}

	public void handleMenus(ActionEvent e) throws IOException{

		if(e.getSource() == sButton ){
			showScene(SMENU,sButton);
		}else if(e.getSource() == mButton){
			showScene(MMENU,mButton);
		}else if(e.getSource() == netButton){
			showScene(NMENU,netButton);
		}
	}
	
	public void showScene(String location,Button butt) throws IOException{
		Stage stage = (Stage) butt.getScene().getWindow();
		Parent root = FXMLLoader.load(getClass().getResource(location));
		stage.setScene(new Scene(root,800,500));
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
		Color AIPlayerCol = null, LocalPlayerCol = null;
		if(piecesSelected == WHITEPIECES){
			AIPlayerCol = Color.BLACK;
			LocalPlayerCol = Color.WHITE;
		}
		else if(piecesSelected == BLACKPIECES)
		{
			AIPlayerCol = Color.WHITE;
			LocalPlayerCol = Color.BLACK;
		}
		AbstractPlayer LocalPlayer = new LocalPlayer(singleName,LocalPlayerCol);	
		AbstractPlayer AIPlayer = null;
		String modeDiff = Xterm.chooseGameMode("easy", "hard");
		if(modeDiff.equals("easy")){
			AIPlayer = new AIPlayer("AIPlayer",AIPlayerCol,2);
		}
		else if(modeDiff.equals("hard")){
			AIPlayer = new AIPlayer("AIPlayer",AIPlayerCol,4);
		}
		SingleGameDriver game = new SingleGameDriver(LocalPlayer,AIPlayer);
		game.countScore(3);
		
	}
	
	public void playMultiplayerGame(){
		AbstractPlayer white = new LocalPlayer(playerWhite,Color.WHITE);
		AbstractPlayer black = new LocalPlayer(playerBlack,Color.BLACK);
		if(mode == NORMALMODE){
			DuoGameDriver game = new DuoGameDriver(white, black);
			game.countScore(3);
		}
		else if(mode == SPEEDMODE){
			DuoSpeedGameDriver game = new DuoSpeedGameDriver(white,black);
			long startTime = System.currentTimeMillis();
			Thread t = new Thread(game);
			t.start();
			while(t.isAlive()){
				if(game.moveExecuted) startTime = System.currentTimeMillis();
				if(System.currentTimeMillis() - startTime > timer && t.isAlive() && !t.isInterrupted()){
					t.interrupt();
					game.onTimeOut();
				}
			}
		}
	}
	
	public void playNetworkGame(){
		
	}
	
	
}
