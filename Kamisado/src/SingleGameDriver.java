import java.util.ArrayList;
import java.util.Stack;

public class SingleGameDriver extends DuoGameDriver{


	public SingleGameDriver(AbstractPlayer white, AbstractPlayer black){
		super(white, black);
		historyEnabled = true;
	}
	
}
