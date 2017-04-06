import java.io.Serializable;

public class Board implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4126546523662966923L;
	private Color[][] board;
	
	public Board(int mode){
		if(mode == 0) initBoard();
		else if(mode == 1) initRandomBoard();
		else System.out.println("Error Wrong mode");
		
	}
	
	private void initBoard(){
		// Set board colors
		board = new Color[8][8];
		
		board[0][0] = Color.ORANGE;
		board[0][1] = Color.BLUE;
		board[0][2] = Color.PURPLE;
		board[0][3] = Color.PINK;
		board[0][4] = Color.YELLOW;
		board[0][5] = Color.RED;
		board[0][6] = Color.GREEN;
		board[0][7] = Color.BROWN;
		
		board[1][0] = Color.RED;
		board[1][1] = Color.ORANGE;
		board[1][2] = Color.PINK;
		board[1][3] = Color.GREEN;
		board[1][4] = Color.BLUE;
		board[1][5] = Color.YELLOW;
		board[1][6] = Color.BROWN;
		board[1][7] = Color.PURPLE;
		
		board[2][0] = Color.GREEN;
		board[2][1] = Color.PINK;
		board[2][2] = Color.ORANGE;
		board[2][3] = Color.RED;
		board[2][4] = Color.PURPLE;
		board[2][5] = Color.BROWN;
		board[2][6] = Color.YELLOW;
		board[2][7] = Color.BLUE;
		
		board[3][0] = Color.PINK;
		board[3][1] = Color.PURPLE;
		board[3][2] = Color.BLUE;
		board[3][3] = Color.ORANGE;
		board[3][4] = Color.BROWN;
		board[3][5] = Color.GREEN;
		board[3][6] = Color.RED;
		board[3][7] = Color.YELLOW;
		
		board[4][0] = Color.YELLOW;
		board[4][1] = Color.RED;
		board[4][2] = Color.GREEN;
		board[4][3] = Color.BROWN;
		board[4][4] = Color.ORANGE;
		board[4][5] = Color.BLUE;
		board[4][6] = Color.PURPLE;
		board[4][7] = Color.PINK;
		
		board[5][0] = Color.BLUE;
		board[5][1] = Color.YELLOW;
		board[5][2] = Color.BROWN;
		board[5][3] = Color.PURPLE;
		board[5][4] = Color.RED;
		board[5][5] = Color.ORANGE;
		board[5][6] = Color.PINK;
		board[5][7] = Color.GREEN;
		
		board[6][0] = Color.PURPLE;
		board[6][1] = Color.BROWN;
		board[6][2] = Color.YELLOW;
		board[6][3] = Color.BLUE;
		board[6][4] = Color.GREEN;
		board[6][5] = Color.PINK;
		board[6][6] = Color.ORANGE;
		board[6][7] = Color.RED;
		
		board[7][0] = Color.BROWN;
		board[7][1] = Color.GREEN;
		board[7][2] = Color.RED;
		board[7][3] = Color.YELLOW;
		board[7][4] = Color.PINK;
		board[7][5] = Color.PURPLE;
		board[7][6] = Color.BLUE;
		board[7][7] = Color.ORANGE;
	}
	
	private void initRandomBoard(){
		board = new Color[8][8];
		Color c;
		for(int row = 0; row < 8; row++){
			for(int col = 0; col < 8; col++){
				c =  Color.getRandomColor();
				if(isLegalColor(row,col,c)){
					board[row][col] = c;
				}else{
					col--;
				}
			}
		}

	}
	
	private boolean isLegalColor(int row,int col, Color c){
		if(col > 0){
			for(int j = 0; j < col; j++){
				if(board[row][j].equals(c))
					return false;
			}
		}
		if(row > 0){
			for(int i = 0; i < row; i++){
				if(board[i][col].equals(c))
					return false;
			}
		}
		return true;
	}
	
	public Color getColor(Position pos){
		return board[pos.getPosX()][pos.getPosY()];
	}
	
	public void printBoard(){
		for (int i = 0; i < 8; i++){
			for(int j = 0; j < 8; j++){
				System.out.print("|"+board[i][j].toString()+"|");
			}
			System.out.println();
		}
	}
	
}
