import java.io.*;
import java.util.*;

public class Load {

	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> fileList = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();
	private String[] words = null;

	// Read each file and store into an ArrayList
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

	// Converts the String ArrayList into a String Array of cleaned words
	public void toWords(int index) {
		words = getParagraph(index).split("[- ]");
		for (int i = 0; i < words.length; i++) {
			words[i] = words[i].replaceAll("\\W", "").toLowerCase();
			wordList.add(words[i]);
			// System.out.println(wordList.get(i));
		}
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