import java.io.*;
import java.util.*;

public class Display {

	// New instances of classes used
	Load load = new Load();
	Frequency frequency = new Frequency();
	Match match = new Match();

	// Initialising Arraylists
	private ArrayList <String> tempList = new ArrayList <String>();
	private ArrayList <String> tableOne = new ArrayList <String>();
	private ArrayList <String> tableTwo = new ArrayList <String>();
	private ArrayList <String> tableThree = new ArrayList <String>();
	private ArrayList <String> wordList = new ArrayList <String>();

	// Initialising Strings
	private String frequencyTableCells;
	private String phraseDataCells;
		
	// Generate two HTML files with the provided template, uses populateFrequencyTable() and populateMatchTable() methods
	// to receive data on the files. 
	public void generateHTML() throws IOException {
		File frequencyFile = new File("frequency.html");
		BufferedWriter writerOne = new BufferedWriter(new FileWriter(frequencyFile));

		File phraseFile = new File("phrase.html");
		BufferedWriter writerTwo = new BufferedWriter(new FileWriter(phraseFile));

		frequency.getUserRequest();
		int original = frequency.getOriginalIndex();
		int compare = frequency.getCompareIndex();
		System.out.println("\n--------- Reading file " + (original + 1) + " and file " + (compare + 1));

		// Populate frequency and phrase tables
		load.readParagraph();
		frequency.frequencyCheck(original, compare);
		System.out.println("\n--------- Calculating Word Frequency");
		populateFrequencyTable(original);
		System.out.println("\n--------- Frequency table Complete");
		populateMatchTable();
		System.out.println("\n--------- Phrase Matching Complete");

		// Template
		String title = "<div style=\"text-align: center; margin-top: 50px\"><h1>Plagiarism Checker Results</h1></div>";
		String tableHeader = "<tr><th>Word</th><th>File - " + (original+1) + "</th><th>File - " + (compare+1) + "</th>" + frequencyTableCells + "</tr>";
		String links = "<div style=\"text-align: center;\"><h2><a href=\"frequency.html\" style=\"display: inline; margin: 20px;\">Word Frequency</a><a href=\"phrase.html\" style=\"display: inline; margin: 20px;\">Phrase Matching</a></h2></div>";
		String tableMain = "<div style=\"margin: auto; width:50%;\">" + links + "<table border=\"1\"style=\"width: 100%; text-align: left;\">" + tableHeader + "</table></div>";
		String phraseData = phraseDataCells;
		String phraseTable = "<div>" + phraseData + "</div>";

		// Constructing final strings containing HTML for frequency and phrase match tables
		String frequencyHTML = title + tableMain;
		String phraseHTML = title + links + phraseTable;


		// Write to and close both files.
		System.out.println("\n--------- Generating HTML file for Word Frequency results");
		writerOne.write(frequencyHTML);
		writerOne.close();
		System.out.println("\n--------- Generating HTML file for Phrase Match results");
		writerTwo.write(phraseHTML);
		writerTwo.close();
	}

	// Loads the words, ranks them accordingly without duplicates, produces HTML strings containing
	// relevant data.
	public void populateFrequencyTable(int original) {
		String resultsName;
		String resultsOriginal;
		String resultsCompare;
		load.toWords(original);
		copyList();
		frequency.rankWords(wordList);
		System.out.println("\n--------- Sorting words in order");
		frequency.removeDuplicates(wordList);
		System.out.println("\n--------- Removing duplicate values");

		// Produces a table row for each data point
		for (int i = 0; i < wordList.size(); i++) {
			resultsName = "<tr><td>" + wordList.get(i) + "</td>";
			resultsOriginal = "<td>" + frequency.getFrequencyOne(i) + "</td>"; 
			resultsCompare = "<td>" + frequency.getFrequencyTwo(i) + "</td></tr>"; 
			tempList.add(resultsName + resultsOriginal + resultsCompare);
		}
		frequencyTableCells = tempList.toString().replaceAll("[ |,|\\[|\\]]", "");
	}

	// Constructs a HTML string containing all phrase match results, compares the phrases
	// then constructs a table and a row for each given result from the files. 
	public void populateMatchTable() throws IOException {
		ArrayList filesOne = match.getFileList(1);
		ArrayList filesTwo = match.getFileList(2);
		System.out.println("\n--------- Comparing phrases from all available files\n");
		match.comparePhrases();
		tempList.clear();
		String stepOne = "</br><table border=\"1\" style=\"width: 20%; margin: auto; width:20%\"><tr style=\"text-align: left\"><th>Pair</th><th>Phrase Match</th></tr>";
		String stepThree = "</table></br>";

		// Produces two table for all files
		for (int i = 0; i < 10; i++) {
			int indexOne = Integer.parseInt(filesOne.get(i).toString()) + 1;
			int indexTwo = Integer.parseInt(filesTwo.get(i).toString()) + 1;
			String stepTwo = "<tr><td>File " + indexOne + " - File " + indexTwo + "</td><td>" + match.getMatchList().get(i) + "</td></tr>";
			if (i < 5) {
				tableOne.add(stepTwo);
			}
			if (i >= 5) {
				tableTwo.add(stepTwo);
			}
		}
		match.rankMatches();
		System.out.println("\n--------- Ranking match phrases");

		// Produces a table for the highest ranking files in the sequence.
		for (int i = 0; i < 5; i++) {
			int indexOne = Integer.parseInt(filesOne.get(i).toString()) + 1;
			int indexTwo = Integer.parseInt(filesTwo.get(i).toString()) + 1;
			String stepTwo = "<tr><td>File " + indexOne + " - File " + indexTwo + "</td><td>" + match.getMatchList().get(i) + "</td></tr>";
			tableThree.add(stepTwo);
		}
		tempList.add(stepOne + tableOne + stepThree + stepOne + tableTwo + stepThree + stepOne + tableThree + stepThree);
		phraseDataCells = tempList.toString().replaceAll("[,|\\[|\\]]", "");
	}

	// Copies a list accessed from another class, making it global for this class.
	public void copyList() {
		for (int i = 0; i < load.getWords().size(); i++) {
			wordList.add(load.getSingleWord(i));
		}
	}
}