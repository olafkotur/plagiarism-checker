import java.io.*;
import java.util.*;

public class Display {

	Load load = new Load();
	Frequency frequency = new Frequency();

	private ArrayList <String> tempList = new ArrayList <String>();
	private String tableCells;
		
	// Generate a template HTML whilst calling populateTable method to find correct values
	public void generateHTML() throws IOException {
		File file = new File("results.html");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		frequency.getUserRequest();
		frequency.frequencyCheck(frequency.getOriginalIndex(), frequency.getCompareIndex());
		load.readParagraph();
		populateTable();

		String tableHeader = "<tr><th>Word</th><th>Original</th><th>Comparison</th>" + tableCells + "</tr>";
		String tableMain = "<div><h2>Word Frequency</h2><table style=\"width: 50%; text-align: left;\">" + tableHeader + "</table></div>";
		String title = "<div style=\"text-align: center; margin-top: 50px\"><h1>Plagiarism Checker Results</h1></div>";

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
		for (int i = 0; i < load.getWords().size(); i++) {
			resultsName = "<tr><td>" + load.getSingleWord(i) + "</td>";
			resultsOriginal = "<td>" + frequency.getFrequencyOne(i) + "</td>"; 
			resultsCompare = "<td>" + frequency.getFrequencyTwo(i) + "</td></tr>"; 
			tempList.add(resultsName + resultsOriginal + resultsCompare);
		}
		tableCells = tempList.toString().replaceAll("[ |,|\\[|\\]]", "");
	}
}