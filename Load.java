import java.io.*;
import java.util.*;

public class Load {

	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> fileList = new ArrayList <String>();
	private ArrayList <String> characterList = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();

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

	// Converts a string of text into an ArrayList of words
	public void readCharacter() throws IOException {
		BufferedReader input[] = new BufferedReader[5];
		int ch;
		for (int i = 0; i < 5; i++) {
			input[i] = new BufferedReader(new FileReader("resources/test" + (i + 1) + ".txt"));
			while ((ch = input[i].read()) != -1) {
				char character = (char)ch;
				characterList.add(character);
			}
		}
	}

	// Retrieve entire file in a string
	public String getParagraph(int index) {
		return fileList.get(index).toString();
	}

	// Retrieve the	
}