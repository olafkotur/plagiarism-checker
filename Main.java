import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		Load load = new Load();
		Frequency frequency = new Frequency();
		Match match = new Match();
		Display display = new Display();

		// Calculating word frequency	
		display.generateHTML();

	}
}