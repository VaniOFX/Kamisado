import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class MainView extends JFrame {

	public MainView(){
		
		this.setSize(600, 600);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		Dimension dim = tk.getScreenSize();
		
		int posX = dim.width / 2 - this.getWidth() / 2;
		int posY = dim.height / 2 - this.getHeight()/ 2;
		
		this.setLocation(posX, posY);
		this.setTitle("Kamisado");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setPreferredSize(new Dimension(600, 600));
		c.add(layeredPane, BorderLayout.CENTER);
		StateView stateView = new StateView();
		//stateView.setLayout(new GridBagLayout());
		BoardView boardView = new BoardView();
		stateView.setBounds(0, 0, 600, 600);
		boardView.setBounds(0, 0, 600, 600);
		layeredPane.add(stateView, 1);
		layeredPane.add(boardView, 0);
		this.pack();
		this.setVisible(true);
	
	}
}
