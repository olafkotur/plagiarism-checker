import java.io.*;
import java.util.*;

public class Match {

	ArrayList<String> phraseOneList = new ArrayList<>();
	ArrayList<String> phraseTwoList = new ArrayList<>();
	ArrayList<String> tempList = new ArrayList<>();

	Frequency frequency = new Frequency();
	Load load = new Load();
	
	public void splitToPhrase() throws IOException {
		load.readParagraph();
		int counter = 0;
		int fileOne = frequency.getOriginalIndex();
		String original = load.getParagraph(fileOne);
		load.toWords(fileOne);
		System.out.println(fileOne);

		for (int i = 0; i < load.getWords().size(); i++) {
			counter++;
			tempList.add(load.getSingleWord(i));
			if (counter == 5) {
				counter = 0;
				phraseOneList.add(tempList.toString());
				tempList.clear();
			}
		}
		System.out.println(phraseOneList.get(1));
	}	
}

