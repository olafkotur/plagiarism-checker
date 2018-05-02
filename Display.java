import java.io.*;
import java.util.*;

public class Display {

	Load load = new Load();
	Frequency frequency = new Frequency();

	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();
	private String tableCells;
		
	// Generate a template HTML whilst calling populateTable method to find correct values
	public void generateHTML() throws IOException {
		File file = new File("results.html");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		frequency.getUserRequest();
		int original = frequency.getOriginalIndex();
		int compare = frequency.getCompareIndex();

		frequency.frequencyCheck(original, compare);
		load.readParagraph();
		populateTable();

		String title = "<div style=\"text-align: center; margin-top: 50px\"><h1>Plagiarism Checker Results</h1></div>";
		String tableHeader = "<tr><th>Word</th><th>File - " + (original+1) + "</th><th>File - " + (compare+1) + "</th>" + tableCells + "</tr>";
		String tableMain = "<div><h2>Word Frequency Analysis</h2><table style=\"width: 50%; text-align: left;\">" + tableHeader + "</table></div>";

		String html = title + tableMain;

		writer.write(html);
		writer.close();
	}

	// Find frequency values for each word mentioned in the files
	public void populateTable() throws IOException {
		String resultsName;
		String resultsOriginal;
		String resultsCompare;
		load.toWords(frequency.getOriginalIndex());
		copyList();
		frequency.rankWords(wordList);
		frequency.removeDuplicates(wordList);
		for (int i = 0; i < wordList.size(); i++) {
			resultsName = "<tr><td>" + wordList.get(i) + "</td>";
			resultsOriginal = "<td>" + frequency.getFrequencyOne(i) + "</td>"; 
			resultsCompare = "<td>" + frequency.getFrequencyTwo(i) + "</td></tr>"; 
			tempList.add(resultsName + resultsOriginal + resultsCompare);
		}
		tableCells = tempList.toString().replaceAll("[ |,|\\[|\\]]", "");
	}

	public void copyList() {
		for (int i = 0; i < load.getWords().size(); i++) {
			wordList.add(load.getSingleWord(i));
		}
	}
}