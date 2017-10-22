package Model;

import javafx.scene.paint.Color;

public class ColorManager {

	public static Color convertColor(Model.Color col){
		if(col == Model.Color.WHITE) return Color.WHITE;
		else if(col == Model.Color.BLACK) return Color.BLACK;
		else if(col == Model.Color.GREEN) return Color.GREEN;
		else if(col == Model.Color.YELLOW) return Color.YELLOW;
		else if(col == Model.Color.PINK) return Color.PINK;
		else if(col == Model.Color.ORANGE) return Color.ORANGE;
		else if(col == Model.Color.BLUE) return Color.BLUE;
		else if(col == Model.Color.BROWN) return Color.BROWN;
		else if(col == Model.Color.PURPLE) return Color.PURPLE;
		else if(col == Model.Color.RED) return Color.RED;
		return null;	
	}
}
