import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public enum Color {
	RED,
	PURPLE,
	BROWN,
	BLUE,
	ORANGE,
	PINK,
	YELLOW,
	GREEN,
	WHITE,
	BLACK;
	
	private static final List<Color> values = Collections.unmodifiableList(Arrays.asList(values()));
	private static final int SIZE = values.size();
	private static final Random RANDOM = new Random();

	public static Color getRandomColor(){
		return values.get(RANDOM.nextInt(SIZE-2));
	}
	
	
}
