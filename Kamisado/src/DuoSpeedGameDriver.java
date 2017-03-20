import java.util.ArrayList;
import java.util.Stack;

public class DuoSpeedGameDriver extends DuoGameDriver implements Runnable{


	public DuoSpeedGameDriver(AbstractPlayer white, AbstractPlayer black){
		super(white, black);
	}
	
	@Override
	public void run() {
		super.startGame();
		
	}
	
	public void onTimeOut(){
		System.out.println(currentPlayer.getName()+ " ran out of time!");
		if (currentPlayer.getColor() == Color.WHITE){
			System.out.println(playerBlack.getName()+ " won");
		}else{
			System.out.println(playerWhite.getName()+ " won");
		}
		System.exit(1);
	}
		
}