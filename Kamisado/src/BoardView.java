import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class BoardView extends JPanel {
	    
	    private Board board;
	    public BoardView() {
	       GridLayout grid = new GridLayout(8,8);
	       this.setLayout(grid);
	       board = new Board();
	       for(int i = 0; i < 8; i++){	
				for(int j = 0; j < 8; j++){
					Color tileColor = board.getColor(new Position(i, j));
					JButton pieceIcon = new JButton();
					switch(tileColor){
					case ORANGE:
						pieceIcon.setBackground(java.awt.Color.ORANGE);
						break;
					case BLUE:
						pieceIcon.setBackground(java.awt.Color.BLUE);
						break;
					case PURPLE:
						pieceIcon.setBackground(new java.awt.Color(147,112,219));
						break;
					case PINK:
						pieceIcon.setBackground(java.awt.Color.PINK);
						break;
					case YELLOW:
						pieceIcon.setBackground(java.awt.Color.YELLOW);
						break;
					case RED:
						pieceIcon.setBackground(java.awt.Color.RED);
						break;
					case GREEN:
						pieceIcon.setBackground(java.awt.Color.GREEN);
						break;
					case BROWN:
						pieceIcon.setBackground(new java.awt.Color(139,69,19));
						break;
					}
					
					pieceIcon.setOpaque(true);
					
					this.add(pieceIcon);
				
					
				}
			}
	    }
}

