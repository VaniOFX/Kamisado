import java.util.ArrayList;
import java.util.Stack;

public class DuoSpeedGameDriver extends DuoGameDriver implements Runnable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -6597023580163111084L;


	public DuoSpeedGameDriver(AbstractPlayer white, AbstractPlayer black){
		super(white, black);
	}
	
	@Override
	public void run() {
		super.startGame();
		super.getRoundWinner();
		
	}
	
	public void onTimeOut(){
		System.out.println(currentPlayer.getName()+ " ran out of time!");
		if (currentPlayer.getColor() == Color.WHITE){
			System.out.println(playerBlack.getName()+ " won");
		}else{
			System.out.println(playerWhite.getName()+ " won");
		}
		SaveManager.saveGame(this);
		System.exit(1);
	}
		
}