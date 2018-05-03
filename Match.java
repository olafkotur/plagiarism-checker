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
		int fileOne = frequency.getOriginalIndex();
		String original = load.getParagraph(fileOne);
		char scanner;
		for (int i = 0; i < original.length(); i++) {
			scanner = original.charAt(i);
			if (Character.toString(scanner) != "\\W") {
				tempList.add(Character.toString(scanner));
			}
			else {
				System.out.println("hello");
				phraseOneList.add(tempList.toString());
				tempList.clear();
			}
			// System.out.println(phraseOneList);
		}



	}	
}

