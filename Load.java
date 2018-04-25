import java.io.*;
import java.util.*;

public class Load {

	ArrayList <String> tempList = new ArrayList <String>();
	ArrayList <String> fileList = new ArrayList <String>();
	
	// Read each file and store into an array list
	public void loadFile() throws Exception {
		try {
			BufferedReader input[] = new BufferedReader[5];
			String lineOfText = null;
			for (int i = 0; i < 5; i++) {
				input[i] = new BufferedReader(new FileReader("resources/test" + (i + 1) + ".txt"));
				while ((lineOfText = input[i].readLine()) != null) {
					tempList.add(lineOfText);
				}
				fileList.add(tempList.toString());
				tempList.clear();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	// Retrieve the specified file
	public String getFile(int index) {
		return fileList.get(index).toString();
	}	
}