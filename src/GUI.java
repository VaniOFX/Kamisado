import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;


public class GUI {

	
	public static void paintGUI(){
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		BufferedImage boardImage = loadImage("board.png");
		JLabel board = new JLabel(new ImageIcon(boardImage));
		f.getContentPane().add(board);
		
		
		BufferedImage pieceImage = loadImage("piece.png");
		JLabel piece = new JLabel(new ImageIcon(pieceImage));
        f.getContentPane().add(piece);
        
        
		JLayeredPane layeredPane = f.getLayeredPane();
		
        f.pack();
        f.setVisible(true);
	}
	
	public static BufferedImage loadImage(String path){
		File file = new File(path);
        BufferedImage image;
		try {
			image = ImageIO.read(file);
			return image;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
