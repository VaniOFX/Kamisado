import java.io.FileNotFoundException;
import java.io.IOException;

public class SavedGameDriver extends DuoGameDriver {

	public SavedGameDriver(AbstractPlayer white, AbstractPlayer black) throws FileNotFoundException, IOException, ClassNotFoundException {
		super(white, black);
	}

}
