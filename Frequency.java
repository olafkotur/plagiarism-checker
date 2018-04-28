import java.io.*;
import java.util.*;

public class Frequency {

	Load load = new Load();

	public void frequencyCheck() throws IOException {
		load.readParagraph();
		load.toWords(4);

	}

}