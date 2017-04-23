package Controller;

import Model.Color;
import Model.Position;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class Tile extends Button{
	
	private int x;
	private int y;
	
	public Tile(ImageView iv, int x, int y){
		this.x = x;
		this.y = y;
//		iv.fitWidthProperty().bind(this.widthProperty());
//		iv.fitHeightProperty().bind(this.heightProperty());
		this.getChildren().add(iv);
	}
	
	public Position getPos(){
		//might be reversed???
		return new Position(x, y);
	}
	
	public void setBackground(Color color){
		String colorHex;
		switch(color){
			case ORANGE:
				colorHex = "#ffad33";
				break;
			case BLUE:
				colorHex = "#1ac6ff";
				break;
			case PURPLE:
				colorHex = "#9933ff";
				break;
			case PINK:
				colorHex = "#ff80d5";
				break;
			case YELLOW:
				colorHex = "#ffff66";
				break;
			case RED:
				colorHex = "#ff471a";
				break;
			case GREEN:
				colorHex = "#5cd65c";
				break;
			case BROWN:
				colorHex = "#ac7339";
				break;
			default:
				colorHex = "";
		}
			
		this.setStyle("-fx-background-color:"+ colorHex+";");
	}
}
