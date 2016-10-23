package module3;

import java.util.Random;

public class Alphabet {

	/** Global random number generator. */
	private static Random rand = new Random();
	
	/** Create random value in the range [0-127) and convert it to a character. */
	public static char randomCharacter() {
		return (char) (rand.nextInt(127));
	}

	public static void main(String args[]) {

		// Initalize StringBuilder with capacity of 400
		StringBuilder sb = new StringBuilder(400);

		// Sum of all the numbers in the random sting
		int total = 0;

		// Number of times the character failed to parse as an int
		// i.e. numberof non-digits in random string
		int nExceptions = 0;

		for (int i = 0; i < 400; i++) {

			char c = randomCharacter();
			if (Character.isDigit(c) || Character.isLetter(c)) {
				sb.append(c);
				try {
					// Parse character as integer and add to total
					total += Integer.parseInt(Character.toString(c));
				} catch (NumberFormatException e) {
					// If character parsing failes increment and continue.
					nExceptions++;
				}
			}
		}

		System.out.println("Random string: " + sb.toString());
		System.out.println("Sum of all digits in random string: " + total);
		System.out.println("Number of exceptions caught: " + nExceptions);
		
	}

}
