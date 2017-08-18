package exam1;

import java.io.IOException;

/** 
 * Class only providing the main method, running the MLBAnalysis class on the url:
 *		http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/MLB2001Hitting.txt
 * and printing the results.
 */
public class MidTermExam {

	public static void main(String[] args) throws IOException {
		String url = "http://www.hep.ucl.ac.uk/undergrad/3459/exam-data/MLB2001Hitting.txt";
		MLBAnalysis analysis = new MLBAnalysis(url);
		System.out.println(analysis);
	}

}
