import java.io.*;
import java.util.*;

public class Frequency {
	
	public void frequencyCheck() throws Exception {
		Load L = new Load();
		L.loadFile();
		System.out.println(L.getFile(1));
	}

	// public int getFrequency() {

	// }

}