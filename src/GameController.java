import java.awt.TextField;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class GameController implements Observable{

	private ArrayList<Observer> observers;
	private int scene;
	
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
	
	//Game settings 
	private static final int SPEEDMODE = 10;
	private static final int NORMALMODE = 11;
	
	private static final int WHITEPIECES = 12;
	private static final int BLACKPIECES = 13;
	
	//Scene Modes
	public static final int MENU0 = 0;
	public static final int MENU1 = 1;
	public static final int GAMEVIEW = 2;
	
	public GameController(){
		observers = new ArrayList<Observer>();
	}

	public void startSingleGame(){
		scene = GAMEVIEW;
		notifyObservers();
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
		scene = GAMEVIEW;
		notifyObservers();
	}
	
	public void playMultiplayerGame(){
		
	}
	
	public void playNetworkGame(){
		
	}
	
	
	@Override
	public void subscribe(Observer observer) {
		observers.add(observer);
		
	}

	@Override
	public void unsubscribe(Observer observer) {
		observers.remove(observer);		
	}

	@Override
	public void notifyObservers() {
		for(Observer ob : observers){
			ob.update(scene);
		}
		
	}
	
}
