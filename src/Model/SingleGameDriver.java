package Model;


public class SingleGameDriver extends DuoGameDriver{


	public SingleGameDriver(AbstractPlayer white, AbstractPlayer black){
		super(white, black);
		historyEnabled = true;
	}
	
	public void startGame(){
		if(historyEnabled){
			history.push(currentState);
		}
		currentPlayer = playerWhite;
		
		if(playerWhite.getName().equals("AIPlayer")){
			currentState.setCurrentInitial(new Position(7,3));
			running = true;
		}else{
			currentState.setCurrentInitial(currentPlayer.getInitialPosition());
			running = true;
		}
	}
	
}
