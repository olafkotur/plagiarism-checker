import java.io.*;
import java.util.*;

public class Frequency {

	// New instances of classes used
	Load load = new Load();
	Load reload = new Load();

	// Initialising Arraylists
	private ArrayList <Integer> frequencyOneList = new ArrayList <Integer>();
	private ArrayList <Integer> frequencyTwoList = new ArrayList <Integer>();
	private int originalIndex = 0;
	private int compareIndex = 0;

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

	// Orders the words and their corresponding frequencies by using an insertion sort
	// algortithm where @param list is the list of words from another class.
	public void rankWords(ArrayList list) {
		for (int i = 1; i < frequencyOneList.size(); i++) {
			int x = frequencyOneList.get(i);
			for (int j = i - 1; j >= 0 && frequencyOneList.get(j) > x; j--) {
				Collections.swap(frequencyOneList, j + 1, j);
				Collections.swap(frequencyTwoList, j + 1, j);
				Collections.swap(list, j + 1, j);
			}
		}
		Collections.reverse(frequencyOneList);
		Collections.reverse(frequencyTwoList);
		Collections.reverse(list);
	}

	// Removes the duplicates in the wordList, searches for multiple cases of the same 
	// word, also removes the corresponding frequency values to maintain data positions 
	// in the table. 
	public void removeDuplicates(ArrayList list) {
		int listSize = list.size();
		for (int i = 0; i < listSize - 1; i++) {
			for (int j = i + 1; j < listSize; j++) {
				if (list.get(j).equals(list.get(i))) {
					list.remove(list.get(j).toString());
					frequencyOneList.remove(frequencyOneList.get(j));
					frequencyTwoList.remove(frequencyTwoList.get(j));
					listSize--;
					j--;
				}
			}
		}
	}

	// Ask user which two files they would like to compare in the frequency check
	public void getUserRequest() {
		System.out.println("\n--------- PLEASE CHOOSE WHICH FILES TO COMPARE ---------");
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nOriginal File (1 - 5): ");
		originalIndex = scanner.nextInt() - 1;
		
		System.out.println("\nComparison File (1 - 5): ");
		compareIndex = scanner.nextInt() - 1;
		scanner.close();

		if (originalIndex > 4 || compareIndex > 4 || originalIndex < 0 || compareIndex < 0) {
			System.out.println("\n--------- CHOSEN FILE DOES NOT EXIST ---------");
			System.out.println("\n--------- PLEASE CHOOSE A DIFFERENT FILE ---------");
			System.out.println("\n--------- Halting\n");
			System.exit(0);
		}
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