package module4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 * Class providing functions to read from a file on disk or from a url connection
 * and a word counting function.
 *
 * The main function tests the word counting function.
 */
public class WordCounter {

	/**
	 * Returns a BufferedReader that reads from a connection to `urlName`. 
	 */
	public static BufferedReader brFromURL(String urlName) throws IOException {
		URL url = new URL(urlName);
		InputStreamReader isr = new InputStreamReader(url.openStream());
		return new BufferedReader(isr);
	}

	/**
	 * Returns a BufferedReader that reads from a file on disk.
	 */
	public static BufferedReader brFromFile(String fileName) throws FileNotFoundException {
		FileReader fr = new FileReader(fileName);
		return new BufferedReader(fr);
	}

	/**
	 * Returns the number of whitespace separated words in a BufferedReader.
	 */
	public static int countWordsInResource(BufferedReader br) {
		int nlines = 0;
		Scanner sc = new Scanner(br);
		while (sc.hasNext()) {
			sc.next(); // step forward to end of next whitespace delimited word
			nlines++;
		}
		sc.close();
		return nlines;
	}

	public static void main(String[] args) {
		final String txturl = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt";
		try {
			// Open url connection to read from
			BufferedReader br = brFromURL(txturl);
			int count = countWordsInResource(br);
			System.out.println("Word count in \"module4_text.txt\": " + count);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
