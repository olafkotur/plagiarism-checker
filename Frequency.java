import java.io.*;
import java.util.*;

public class Frequency {

	Load load = new Load();

	ArrayList <Integer> frequencyList = new ArrayList <Integer>();

	public void frequencyCheck() throws IOException {
		load.readParagraph();
		for (int i = 0; i < 5; i++) {
			int count = 0;
			load.toWords(i);
			for (int j = 0; j < load.getWords().size(); j++) {
				count = Collections.frequency(load.getWords(), load.getSingleWord(j));
				frequencyList.add(count);
			}
			System.out.println(frequencyList + "\n\n");
			frequencyList.clear();
			load.clearWordList();
		}
	}
}