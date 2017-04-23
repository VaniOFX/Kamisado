package View;

import Model.Board;
import Model.ColorManager;
import Model.Piece;
import Model.Position;
import Model.State;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class BoardView extends Parent{
	
	private VBox rows = new VBox();
	private Board board = new Board(Board.NORMAL);
	private EventHandler<? super MouseEvent> handler;
	
	public BoardView(EventHandler<? super MouseEvent> handler){
		this.handler = handler;
	}
	
	public Parent createBoard(){
		for(int i = 0; i < 8; i++){
			HBox row = new HBox();
			for(int j = 0; j < 8; j++){
				Color local = ColorManager.convertColor(board.getColor(new Position(i,j)));
				StackPane stack = new StackPane();	
				Rectangle cell = new Rectangle(75,75,local);
				cell.setStroke(Color.BLACK);
				cell.setOnMouseClicked(handler);
				stack.getChildren().addAll(cell);
				if(i==0 || i ==7){
					Circle piece = new Circle(25,local);
					piece.setStroke(Color.BLACK);
					stack.getChildren().addAll(piece);
				}
				row.getChildren().addAll(stack);
				
			}
			rows.getChildren().add(row);
		}	
		return rows;
	}
	
	private StackPane getCell(int x,int y){
		return (StackPane) ((HBox) rows.getChildren().get(y)).getChildren().get(x);
	}
	
	private Node getPiece(StackPane pane){
		if(pane.getChildren().size()>1) return pane.getChildren().get(1);
		return null;
		
	}
	
	private void addPiece(StackPane pane, Circle piece){
		if(pane.getChildren().size() == 1) pane.getChildren().add(piece);
		else System.out.println("Existing piece");
	}
	
	private void removePiece(StackPane pane){
		if(pane.getChildren().size()>1){
			pane.getChildren().remove(1);
		}
	}

	
	public Parent update(State currentState) {
		for(int x = 0; x < 8; x++){
			for(int y = 0; y < 8; y++){
				Piece statePiece = currentState.getPiece(new Position(x,y));
				StackPane currentCell = getCell(x,y);
				if(statePiece != null){
					if(getPiece(currentCell) == null){
						Circle piece = new Circle(25,ColorManager.convertColor(statePiece.getColor()));
						addPiece(currentCell,piece);
					}else if(ColorManager.convertColor(statePiece.getColor()) !=  ((Shape) getPiece(currentCell)).getFill()){
						removePiece(currentCell);
						Circle piece = new Circle(25,ColorManager.convertColor(statePiece.getColor()));
						addPiece(currentCell,piece);
					}
				}else{
					if(getPiece(currentCell) != null){
						removePiece(currentCell);
					}
				}
			}
		}
		return rows;
	}
}

