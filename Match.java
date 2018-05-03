import java.io.*;
import java.util.*;

public class Match {

	ArrayList<String> phraseOneList = new ArrayList<>();
	ArrayList<String> phraseTwoList = new ArrayList<>();

	Frequency frequency = new Frequency();

	public void splitToPhrase() throws IOException {
		BufferedReader input[] = new BufferedReader[2];
		String lineOfText = null;
		for (int i = 0; i < 1; i++) {
			input[i] = new BufferedReader(new FileReader("resources/test" + frequency.getOriginalIndex() + ".txt");
				while((lineOfText = input[i].readLine()) != null) {
					phraseOneList.add(lineOfText);
				}
		}

	}
	
}

