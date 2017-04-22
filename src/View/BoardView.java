package View;

import Model.Board;

import Model.Observer;
import Model.Position;
import Model.State;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardView extends Parent implements Observer{
	
//	@FXML
//	private GridPane gridPane;
//	
//	HashMap<Piece,Image> hashMap;
//	
//	
//	public BoardView(){
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
//				"FXMLFiles/BoardView.fxml"));
//        fxmlLoader.setRoot(this);
//        fxmlLoader.setController(this);
//        try {
//            fxmlLoader.load();
//        } catch (IOException exception) {
//            throw new RuntimeException(exception);
//        }
//	}
//
//	@FXML
//	public void initialize(State currentState){
//    	System.out.println("inside controller");
//        gridPane.setGridLinesVisible(true);
//        ObservableList<Node> childrens = gridPane.getChildren();
//        Piece[][] pieces = currentState.getPieces();
//        hashMap.put(pieces[0][0], new Image("C:\\Users\\Damyan\\Desktop\\damyan_kalev.jpg"));
//        
//        for (Node node : childrens) {
//            gridPane.getChildren().remove(node);
//        }
//
//        for(int i = 0; i < 8; i++){
//            for(int j = 0; j < 8; j++){
//                System.out.println("I'm in!");
//                Pane current = new Pane();
//                if(hashMap.get(pieces[i][j]) != null) {
//                    ImageView iv = new ImageView(hashMap.get(pieces[i][j]));
//                    current.getChildren().add(iv);
//                }
//                gridPane.getChildren().add(current);
//            }
//        }
//    }
	
	private VBox rows = new VBox();
	private Board board;
	
	public BoardView(EventHandler<? super MouseEvent> handler){
		for(int i = 0; i < 8; i++){
			HBox row = new HBox();
			for(int j = 0; j < 8; j++){
				Cell c = new Cell(i,j,Color.BLACK);
				c.setOnMouseClicked(handler);
				row.getChildren().add(c);
			}
			rows.getChildren().add(row);
		}
		
	}
	
	public class Cell extends Rectangle{
		public int x,y;
		public Piece piece;
		public Color col;
		
		public Cell(int x, int y,Color col){
			super(30,30,col);
			this.x = x;
			this.y = y;
			this.col = col;
			setStroke(Color.BLACK);
		}
		
		
	}
	
	public class Piece extends Circle{
		
		public Piece(Color col){
			super(10);
			setFill(col);
			setStroke(Color.WHITE);
		}
			
	}
	
	@Override
	public void update(State currentState) {
//		initialize(currentState);
		
	}

}

