import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {

		// Starts the system timer
		long start = System.currentTimeMillis();

		Display display = new Display();
		display.generateHTML();

		// End the system timer and prints out how long the program took
		long end = System.currentTimeMillis();
		System.out.println("\n--------- DONE in " + (end - start) + " milliseconds");
	}
}