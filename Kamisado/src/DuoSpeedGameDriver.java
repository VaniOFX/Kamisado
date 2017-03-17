import java.util.ArrayList;
import java.util.Stack;

public class DuoSpeedGameDriver extends DuoGameDriver implements Runnable{

	
	public DuoSpeedGameDriver(AbstractPlayer white, AbstractPlayer black){
		super(white, black);
	}
	
	@Override
	public void run() {
		super.startGame();
		
		
		/*
		currentInitial = currentPlayer.getInitialPosition();
		
		while(true) {
			//current player makes a move
			Move move = currentPlayer.getMove(currentInitial);
			
			//validate move
			if(GameRules.isLegalMove(currentState, move)){
				if(GameRules.isWinningMove(currentState, move)){
					//generate match report
					System.out.println(currentPlayer.getName()+ " won");
					//end game
				}
				//update board
				currentState.move(move);
				
				notifyObservers();
								
				
				//switch current player
				if(currentPlayer == playerWhite){
					currentPlayer = playerBlack;
				}else{
					currentPlayer = playerWhite;
				}
				
				currentInitial = currentState.getPiecePosition(currentPlayer.getColor(), board.getColor(move.getTarget()));
				System.out.println(currentInitial.getPosX()+" "+currentInitial.getPosY());
			}else{
				System.out.println("Illegal move.");
			}
		}
		*/
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
