package module4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

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
		Scanner sc = new Scanner(br);
		while (sc.hasNext()) {
			sc.next();
			nlines++;
		}
		sc.close();
		return nlines;
	}

	public static void main(String[] args) {
		final String txturl = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module4/module4_text.txt";
		final String txtfile = "/home/patrick/university/scientific computing/workspace/PHAS3459/src/module4/module4_text.txt";
		try {
			 BufferedReader br = brFromURL(txturl);
			int count = countWordsInResource(br);
			System.out.println("Word count in \"module4_text.txt\": " + count);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
