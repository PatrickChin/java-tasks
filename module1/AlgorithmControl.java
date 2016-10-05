package module1;

import java.math.BigDecimal;

public class AlgorithmControl {

	public static void loop() {
		for (int i = 0; i < 20; i++)
			System.out.println(i);
	}

	public static void decrement() {
		int i = 8;
		while (i >= -10)
			System.out.println(i--);
	}

	public static void increment() {

		// Use 10.9 as the upper bound instead of 10.8 
		// to avoid dealing with floating precision errors.
		for (double i = 1.2; i < 10.9; i+=0.4)
			System.out.println(i);

		// To print actual numbers between 1.2 and 10.8 in
		// steps of 0.4, use BigDecimal.
		/* for (BigDecimal i = new BigDecimal("1.2");
				i.compareTo(new BigDecimal("10.8")) < 0;
				i = i.add(new BigDecimal("0.4")))
			System.out.println(i);
		*/
	}

	public static int timer(long t, int n) {
		long t1 = System.currentTimeMillis();
		long t2 = t1 + t;
		for (int i = 0; ; i++) {
			if (System.currentTimeMillis() >= t2)
				return i;
			if (i % n == 0)
				System.out.println(i);
		}
	}

	public static void main(String[] args) {

		loop();
		System.out.println();

		decrement();
		System.out.println();

		increment();
		System.out.println();

		int n1 = timer(10000,500);   // more loops per second
		int n2 = timer(10000,50000); // less loops per second
		// Reasons:
		// printing takes time
		// branch prediction maybe?
		System.out.println("Total loops (print every 500): "+n1);
		System.out.println("Total loops (print every 50000): "+n2);
	}

}
