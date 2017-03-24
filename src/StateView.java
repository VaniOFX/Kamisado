import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StateView extends JPanel implements Observer {
//	public void createView();
	
	private State currentState;
	
	public void update(State currentState){
		currentState.printState();
	}
	
	public StateView(){
		Piece piece;
		GridBagLayout gridbag = new GridBagLayout();
		this.setLayout(gridbag);
        GridBagConstraints c = new GridBagConstraints();
        c.fill = c.BOTH;
        c.weightx = 1;
        c.weighty = 1;
		
		for(int i = 0; i < 8; i++){	
			for(int j = 0; j < 8; j++){
				//piece = currentState.getPiece(new Position(i, j));
				c.gridx = j;
				c.gridy = i;
				JLabel pieceIcon = new JLabel(new ImageIcon());
				pieceIcon.setBackground(java.awt.Color.RED);
				pieceIcon.setOpaque(true);
				gridbag.setConstraints(pieceIcon, c);
				this.add(pieceIcon,c);
			
				
			}
		}
		
	}
}
