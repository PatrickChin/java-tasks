package module4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class WordCounter {
	
	public static BufferedReader brFromURL(String urlName) throws IOException {
		URL url = new URL(urlName);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		return new BufferedReader(isr);
	}
	
	public static BufferedReader brFromFile(String fileName) throws FileNotFoundException {
		FileReader fr = new FileReader(fileName);
		return new BufferedReader(fr);
	}
	
	public static int countWordsInResource(BufferedReader br) {
		int nlines = 0;
		String line;
		while ((line = br.readLine()))
	}

}
