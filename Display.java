import java.io.*;
import java.util.*;

public class Display {

	Load load = new Load();
	Frequency frequency = new Frequency();
	Match match = new Match();

	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();
	private String frequencyTableCells;
		
	// Generate a template HTML whilst calling populateTable method to find correct values
	public void generateHTML() throws IOException {
		File frequencyFile = new File("frequency.html");
		BufferedWriter writerOne = new BufferedWriter(new FileWriter(frequencyFile));

		File phraseFile = new File("phrase.html");
		BufferedWriter writerTwo = new BufferedWriter(new FileWriter(phraseFile));

		frequency.getUserRequest();
		int original = frequency.getOriginalIndex();
		int compare = frequency.getCompareIndex();

		load.readParagraph();
		frequency.frequencyCheck(original, compare);
		populateFrequencyTable(original);
		populateMatchTable();

		String title = "<div style=\"text-align: center; margin-top: 50px\"><h1>Plagiarism Checker Results</h1></div>";
		String tableHeader = "<tr><th>Word</th><th>File - " + (original+1) + "</th><th>File - " + (compare+1) + "</th>" + frequencyTableCells + "</tr>";
		String links = "<div style=\"text-align: center;\"><h2><a href=\"frequency.html\" style=\"display: inline; margin: 20px;\">Word Frequency</a><a href=\"phrase.html\" style=\"display: inline; margin: 20px;\">Phrase Matching</a></h2></div>";
		String tableMain = "<div style=\"margin: auto; width:50%;\">" + links + "<table border=\"1\"style=\"width: 100%; text-align: left;\">" + tableHeader + "</table></div>";

		String frequencyHTML = title + tableMain;
		String phraseHTML = title + links;

		writerOne.write(frequencyHTML);
		writerOne.close();

		writerTwo.write(phraseHTML);
		writerTwo.close();
	}

	// Find frequency values for each word mentioned in the files
	public void populateFrequencyTable(int original) {
		String resultsName;
		String resultsOriginal;
		String resultsCompare;
		load.toWords(original);
		copyList();
		frequency.rankWords(wordList);
		frequency.removeDuplicates(wordList);
		for (int i = 0; i < wordList.size(); i++) {
			resultsName = "<tr><td>" + wordList.get(i) + "</td>";
			resultsOriginal = "<td>" + frequency.getFrequencyOne(i) + "</td>"; 
			resultsCompare = "<td>" + frequency.getFrequencyTwo(i) + "</td></tr>"; 
			tempList.add(resultsName + resultsOriginal + resultsCompare);
		}
		frequencyTableCells = tempList.toString().replaceAll("[ |,|\\[|\\]]", "");
	}

	public void populateMatchTable() throws IOException {
		match.comparePhrases();
	}

	public void copyList() {
		for (int i = 0; i < load.getWords().size(); i++) {
			wordList.add(load.getSingleWord(i));
		}
	}
}







