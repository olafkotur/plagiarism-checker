import java.io.*;
import java.util.*;

public class Load {

	// Initialising Arraylists
	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> fileList = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();
	private ArrayList<String> phraseList = new ArrayList <String>();
	private String[] words = null;

	// Read each of the provided files and store them in an ArrayList
	public void readParagraph() throws IOException {
		BufferedReader input[] = new BufferedReader[5];
		String lineOfText = null;
		for (int i = 0; i < 5; i++) {
			input[i] = new BufferedReader(new FileReader("resources/test" + (i + 1) + ".txt"));
			while ((lineOfText = input[i].readLine()) != null) {
				tempList.add(lineOfText);
			}
			fileList.add(tempList.toString());
			tempList.clear();
		}
	}

	// Scans through all words in the ArrayList where @param index is the desired file.
	// Splits the string around where words end, also removes any non-alphabet characters
	// leaving only cleaned words in an ArrayList.
	public void toWords(int index) {
		wordList.clear();
		words = getParagraph(index).split("[- ]");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("\\W", "").toLowerCase();
			wordList.add(words[i]);
			wordList.remove("");
		}
	}


	// Splits the words from a given file into a list of phrases of length 'phraseLength'
	// stores each phrase in an ArrayList of phrases which are used for comparsion. 
	public void toPhrase(int index) throws IOException {
		int counter = 0;
		int phraseLength = 3;
		phraseList.clear();
		readParagraph();
		toWords(index);

		for (int i = 0; i < getWords().size(); i++) {
			counter++;
			tempList.add(getSingleWord(i));
			if (counter >= phraseLength) {
				phraseList.add(tempList.toString());
				tempList.clear();
				counter = 0;
			}
		}
	}

	// Accessor method for the phraseList ArrayList
	public ArrayList getPhrase() {
		return phraseList;
	}

	// Retrieve specified file in String format
	public String getParagraph(int index) {
		return fileList.get(index).toString();
	}

	// Retrieve a String Array of all the words in a file
	public ArrayList getWords() {
		return wordList;
	}

	// Retrieve a specified word by the index from the Word List
	public String getSingleWord(int index) {
		return wordList.get(index);
	}

	// Clears the current Word List
	public void clearWordList() {
		wordList.clear();
	}
}