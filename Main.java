import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Load load = new Load();
		Frequency frequency = new Frequency();
		Match match = new Match();
		Display display = new Display();

		// Calculating word frequency
		load.readParagraph();
		
		frequency.getUserRequest();
		int original = frequency.getOriginalIndex();
		int compare = frequency.getCompareIndex();
		
		frequency.frequencyCheck(original, compare);
		display.generateHTML(original, compare);

	}
}