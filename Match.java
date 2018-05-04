import java.io.*;
import java.util.*;

public class Match {

	Frequency frequency = new Frequency();
	Load load = new Load();
	Load reload = new Load();

	ArrayList<Float> matchList = new ArrayList<>();

	public void comparePhrases() throws IOException {
		int filesOne[] = {0, 0, 0, 0, 1, 1, 1, 2, 2, 3};
		int filesTwo[] = {1, 2, 3, 4, 2, 3, 4, 3, 4, 4};
		int counter = 0;
		for (int i = 0; i < 10; i++) {
			load.toPhrase(filesOne[i]);
			reload.toPhrase(filesTwo[i]);
			int first = load.getPhrase().size();
			int second = reload.getPhrase().size();
			int loop = determineSize(first, second);
			counter = 0;
			for (int j = 0; j < loop; j++) {
				for (int k = 0; k < loop; k++) {
					if (load.getPhrase().get(k).toString().equals(reload.getPhrase().get(j).toString())) {
						counter++;
					}
				}
			}
			matchList.add((counter * 100.0f) / load.getPhrase().size());
			System.out.println(matchList.get(i));
		}
	}

	public int determineSize(int first, int second) {
		int loop = 0;
		if (first > second) {
			loop = second;
		}
		else {
			loop = first;
		}
		return loop;
	}
}











