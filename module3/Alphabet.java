package module3;

import java.util.Random;

public class Alphabet {
	
	private static Random rand = new Random();
	
	public static char randomCharacter() {
		return (char) (rand.nextInt(127));
	}
	
	public static void main(String args[]) {
		
		StringBuilder sb = new StringBuilder(400);
		int total = 0, nExceptions = 0;
		
		for (int i = 0; i < 400; i++) {
			char c = randomCharacter();
			if (Character.isDigit(c) || Character.isLetter(c)) {
				sb.append(c);
				try {
					int j = Integer.parseInt(Character.toString(c));
					total += j;
				} catch (NumberFormatException e) {
					nExceptions++;
				}
			}
		}

		System.out.println(sb.toString());
		System.out.println(total);
		System.out.println(nExceptions);
		
	}

}
