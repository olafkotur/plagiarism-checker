import java.io.*;
import java.util.*;

public class Match {

	// New instances of classes used
	Frequency frequency = new Frequency();
	Load load = new Load();
	Load reload = new Load();

	// Initialising Arraylists
	ArrayList<Integer> matchList = new ArrayList<>();
	ArrayList<Integer> fileOneList = new ArrayList<>();
	ArrayList<Integer> fileTwoList = new ArrayList<>();

	// Compares each combination of available files, searches for phrases that appear in both files
	// Stores the results in an ArrayList in the form of Integers, can use getMatchList() function to
	// access from another class.
	public void comparePhrases() throws IOException {
		fileOneList.addAll(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 2, 2, 3));
		fileTwoList.addAll(Arrays.asList(1, 2, 3, 4, 2, 3, 4, 3, 4, 4));
		int counter = 0;
		// Compare each file against another using the given combination. 
		for (int i = 0; i < 10; i++) {
			load.toPhrase(fileOneList.get(i));
			reload.toPhrase(fileTwoList.get(i));
			int first = load.getPhrase().size();
			int second = reload.getPhrase().size();
			int loop = determineSize(first, second);
			counter = 0;
			// If phrase x of file on is repeated in file y then add to the counter
			for (int j = 0; j < loop; j++) {
				for (int k = 0; k < loop; k++) {
					if (load.getPhrase().get(k).toString().equals(reload.getPhrase().get(j).toString())) {
						counter++;
					}
				}
			}
			// (Counter / total words) * 100.
			matchList.add(Math.round((counter * 100.0f) / load.getPhrase().size()));
			System.out.println("\n-- Match found: " + matchList.get(i) + "%");
		}
	}

	// Orders the ArrayLists for matches, and file combinations, shows highest to lowest.
	// Can access the modified lists using the getFileList(index) accessor. Uses the insertion
	// sort algorithm. 
	public void rankMatches() {
		for (int i = 1; i < matchList.size(); i++) {
			int x = matchList.get(i);
			for (int j = i - 1; j >= 0 && matchList.get(j) > x; j--) {
				Collections.swap(matchList, j + 1, j);
				Collections.swap(fileOneList, j + 1, j);
				Collections.swap(fileTwoList, j + 1, j);
			}
		}
		Collections.reverse(matchList);
		Collections.reverse(fileOneList);
		Collections.reverse(fileTwoList);
	}

	// Determines the size of the ArrayList used for matching.
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

	// Accessor method for the matchList ArrayList returning the results of the phrase
	// comparisons, given in Integer, should be represented as percentages. 
	public ArrayList getMatchList() {
		return matchList;
	}

	// Accessor method for the File combination ArrayLists, where @param index desired list 
	// of files, either 1 or 2. Returns null if invalid index is given. 
	public ArrayList getFileList(int index) {
		ArrayList list = null;
		if (index == 1) {
			list = fileOneList;
		}
		if (index == 2) {
			list = fileTwoList;
		}
		return list;
	}
}