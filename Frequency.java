import java.io.*;
import java.util.*;

public class Frequency {

	Load load = new Load();
	Load reload = new Load();

	ArrayList <Integer> frequencyOneList = new ArrayList <Integer>();
	ArrayList <Integer> frequencyTwoList = new ArrayList <Integer>();
	int originalIndex = 0;
	int compareIndex = 0;

	// Read both files in to separate instances, find the word frequency of the original
	// file and compare it to the second one.
	public void frequencyCheck(int indexOne, int indexTwo) throws IOException {
		
		// Storing the words of both files into memory
		load.readParagraph();
		load.toWords(indexOne);
		reload.readParagraph();
		reload.toWords(indexTwo);

		// Check for occurence of each word and store in separate ArrayLists
		int countOne = 0;
		int countTwo = 0;
		for (int i = 0; i < load.getWords().size(); i++) {
			countOne = Collections.frequency(load.getWords(), load.getSingleWord(i));
			countTwo = Collections.frequency(reload.getWords(), load.getSingleWord(i));
			frequencyOneList.add(countOne);
			frequencyTwoList.add(countTwo);
		}
	}

	// Ask user which two files they would like to compare in the frequency check
	public void getUserRequest() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter index number of the original file (1 - 5): ");
		originalIndex = scanner.nextInt() - 1;
		
		System.out.println("Please enter index number of the comparison file (1 - 5): ");
		compareIndex = scanner.nextInt() - 1;
		scanner.close();
	}

	// Retrieve the occurences of each word in the original file
	public int getFrequencyOne(int index) {
		return frequencyOneList.get(index);
	}

	// Retrieve the occurences of each word in the comparison file
	public int getFrequencyTwo(int index) {
		return frequencyTwoList.get(index);
	}

	// Retrieve the value of the original file requested by user
	public int getOriginalIndex() {
		return originalIndex;
	}

	// Retrieve the value of the comparison file requested by user
	public int getCompareIndex() {
		return compareIndex;
	}
}