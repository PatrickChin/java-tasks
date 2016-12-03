package module6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class TestDataPoints {
	
	public static ArrayList<DataPoint> dataFromURL(String url) throws MalformedURLException, IOException {
		ArrayList<DataPoint> ret = new ArrayList<>();
		try (BufferedReader br = 
				new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
			String line;
			while ((line = br.readLine()) != null) {
				Scanner sc = new Scanner(line);
				double x = sc.nextDouble();
				double y = sc.nextDouble();
				double ey = sc.nextDouble();
				DataPoint p = sc.hasNext() ? 
						new LabelledDataPoint(x, y, ey, sc.next()) :
						new DataPoint(x, y, ey);
				sc.close();
				ret.add(p);
			}
		}
		return ret;
	}

	public static void main(String[] args) throws MalformedURLException, IOException {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/data/module6/module6-data.txt";
		ArrayList<DataPoint> data = dataFromURL(url);
		for (DataPoint dp : data) {
			System.out.println(dp);
		}
	}

}
