import java.util.HashMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

public class Controller{

	HashMap<Piece,Image> hashMap;
    @FXML
	GridPane boardPane;
	
	public Controller(){

		hashMap = new HashMap<>();

		populateHashMap();
	}
    @FXML
	public void initialize(State currentState){

        boardPane.setGridLinesVisible(true);
        ObservableList<Node> childrens = boardPane.getChildren();
        Piece[][] pieces = currentState.getPieces();
        hashMap.put(pieces[0][0], new Image("C:\\Users\\Damyan\\Desktop\\damyan_kalev.jpg"));
        for (Node node : childrens) {
            boardPane.getChildren().remove(node);
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.println("I'm in!");
                Pane current = new Pane();
                if(hashMap.get(pieces[i][j]) != null) {
                    ImageView iv = new ImageView(hashMap.get(pieces[i][j]));
                    current.getChildren().add(iv);
                }
                boardPane.getChildren().add(current);
            }
        }
    }
	private void loadImages(){
		
	}
	
	private void populateHashMap(){
		
	}
	

	public void update(State currentState) {


	}
	
}
