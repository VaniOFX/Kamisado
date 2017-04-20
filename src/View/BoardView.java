package View;
import java.io.IOException;
import java.util.HashMap;

import Model.Observer;
import Model.Piece;
import Model.State;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class BoardView extends GridPane implements Observer{
	@FXML private GridPane gridPane;
	HashMap<Piece,Image> hashMap;
	
	public BoardView(){
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
				"FXMLFiles/BoardView.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
	}

	@FXML
	public void initialize(State currentState){
    	System.out.println("inside controller");
        gridPane.setGridLinesVisible(true);
        ObservableList<Node> childrens = gridPane.getChildren();
        Piece[][] pieces = currentState.getPieces();
        hashMap.put(pieces[0][0], new Image("C:\\Users\\Damyan\\Desktop\\damyan_kalev.jpg"));
        for (Node node : childrens) {
            gridPane.getChildren().remove(node);
        }

        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.println("I'm in!");
                Pane current = new Pane();
                if(hashMap.get(pieces[i][j]) != null) {
                    ImageView iv = new ImageView(hashMap.get(pieces[i][j]));
                    current.getChildren().add(iv);
                }
                gridPane.getChildren().add(current);
            }
        }
    }
	@Override
	public void update(State currentState) {
		initialize(currentState);
		
	}

	@Override
	public void update(int screenMode) {
		// TODO Auto-generated method stub
		
	}
}

