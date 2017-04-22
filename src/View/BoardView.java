package View;

import Model.Board;
import Model.ColorManager;
import Model.Observer;
import Model.Piece;
import Model.Position;
import Model.State;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class BoardView extends Parent{
	
//	@FXML
//	private GridPane gridPane;
//	
//	HashMap<PieceView,Image> hashMap;
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
//        PieceView[][] pieces = currentState.getPieces();
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
	private Board board = new Board(Board.NORMAL);
	
	public BoardView(EventHandler<? super MouseEvent> handler){
		for(int i = 0; i < 8; i++){
			HBox row = new HBox();
			for(int j = 0; j < 8; j++){
				Color local = ColorManager.convertColor(board.getColor(new Position(i,j)));
				PieceView p = new PieceView(local);
				Cell c = new Cell(local);
				addPiece(c,p);
				c.setOnMouseClicked(handler);
				row.getChildren().add(c);
			}
			rows.getChildren().add(row);
		}	
	}
	
	
	public Cell getCell(int x,int y){
		return (Cell) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
	}
	
	public void addPiece(Cell cell, PieceView p){
		cell.getChildren().add(p);
	}
	
	public void removePiece(Cell cell){
		cell.getChildren().remove(0);
	}
	
	public class Cell extends Pane{
		
		private PieceView PieceView;
		
		public Cell(Color col){
			Rectangle rect = new Rectangle(20, 20);
			rect.setFill(col);
			rect.setStroke(Color.BLACK);
		}
		
		public void setPiece(PieceView p){
			this.PieceView = p;
		}
		
		public PieceView getPiece(){
			return PieceView;
		}
		
		
	}
	
	public class PieceView extends Pane{
		
		private Color col;
		
		public PieceView(Color col){
			this.col = col;
			Circle c = new Circle(15);
			c.setFill(col);
			c.setStroke(Color.WHITE);
		}
		
		public Color getColor(){
			return col;
		}
			
	}
	
	
	public void update(State currentState) {
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				Piece statePiece = currentState.getPiece(new Position(x,y));
				Cell currentCell = getCell(x,y);
				
				if(statePiece != null){
					if(currentCell.getPiece() == null){
						PieceView p = new PieceView(ColorManager.convertColor(statePiece.getColor()));
						currentCell.setPiece(p);
						addPiece(currentCell,p);
					}else if(ColorManager.convertColor(statePiece.getColor()) !=  currentCell.getPiece().getColor()){
						removePiece(currentCell);
						PieceView p = new PieceView(ColorManager.convertColor(statePiece.getColor()));
						currentCell.setPiece(p);
						addPiece(currentCell,p);
					}
				}else{
					if(currentCell.getPiece() != null){
						currentCell.setPiece(null);
						removePiece(currentCell);
					}
				}
			}
		}
	}
}

