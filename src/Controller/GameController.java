package Controller;

import java.util.HashMap;

import Model.Color;
import Model.Observer;
import Model.Piece;
import Model.State;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class GameController implements Observer{
	
	@FXML
	private GridPane board;
    @FXML
	private GridPane boardPane;

	HashMap<Piece,Image> hashMap;

	
	
	public GameController(){

		hashMap = new HashMap<>();

		populateHashMap();
	}
    @FXML
	public void initialize(){
    	System.out.println("inside controller");
    	ObservableList<Node> children = board.getChildren();
    	//should be in update; only for testing
    	for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	ImageView img = new ImageView(new Image("file:///C://Users//Damyan//Desktop//ic_action_settings.png"));
            	Button tile1 = new Button("",img);
            	Tile tile = new Tile(img, i, j);
            	tile.setBackground(Color.RED);
            	tile1.setStyle("-fx-background-color: #CCFF99");
            	tile1.setMaxSize(65, 65);
            	tile1.setMinSize(30, 30);
            	img.fitWidthProperty().bind(tile1.widthProperty());
            	img.fitHeightProperty().bind(tile1.heightProperty());
            	GridPane.setColumnIndex(tile1, i);
            	GridPane.setRowIndex(tile1, j);
                board.getChildren().add(tile1);
            }
        }
      
    }
	private void loadImages(){
		
	}
	
	private void populateHashMap(){
		
	}
	

	public void update(State currentState) {
		System.out.println("subscribed");
		for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
            	ImageView img = new ImageView(new Image("file:///C://Users//Damyan//Desktop//ic_action_settings.png"));
            	Button tile1 = new Button("",img);
            	Tile tile = new Tile(img, i, j);
            	tile.setBackground(Color.RED);
            	tile1.setStyle("-fx-background-color: #CCFF99");
            	tile1.setMaxSize(65, 65);
            	tile1.setMinSize(30, 30);
            	img.fitWidthProperty().bind(tile1.widthProperty());
            	img.fitHeightProperty().bind(tile1.heightProperty());
            	GridPane.setColumnIndex(tile1, i);
            	GridPane.setRowIndex(tile1, j);
                board.getChildren().add(tile1);
            }
        }
//		board.setGridLinesVisible(true);
//        ObservableList<Node> children = board.getChildren();
//        Piece[][] pieces = currentState.getPieces();
//        hashMap.put(pieces[0][0], new Image("file:///C://Users//Damyan//Desktop//soccer-ball.png"));
//       
//
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                System.out.println("I'm in!");
//                Pane current = new Pane();
//                if(hashMap.get(pieces[i][j]) != null) {
//                    ImageView iv = new ImageView(hashMap.get(pieces[i][j]));
//                    current.getChildren().add(iv);
//                }
//                board.getChildren().add(current);
//            }
//        }
	}
}
