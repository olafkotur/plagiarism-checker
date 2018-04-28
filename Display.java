import java.io.*;

public class Display {
	
	public void generateHTML() throws IOException {
		File file = new File("test.html");
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));

		String title = "<div style=\"text-align: center; margin-top: 50px\"><h1>Plagiarism Checker Results</h1></div>";
		String tableMain = "<div><h2>Word Frequency</h2><table style=\"width: 50%; text-align: left;\"><tr>" + </tr>";

		writer.write(title);
		writer.write(tableMain);
		writer.close();
	}
}