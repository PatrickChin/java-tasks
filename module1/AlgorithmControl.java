package module1;

// import java.math.BigDecimal;

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

		// Floating point errors propogated every loop
		//for (double i = 1.2; i <= 10.8; i+=0.4)
		//	System.out.println(i);

		// More accurate
		for (int i = 12; i <= 108; i+=4)
			System.out.println(i/10.0);

		/*
		// BigDecimal can also be used for arbitrary precision decimals
		for (BigDecimal d = new BigDecimal("1.2");
				d.compareTo(new BigDecimal("10.8")) <= 0;
				d = d.add(new BigDecimal("0.4")))
			System.out.println(d);
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
		// writing to the console takes time
		// branch prediction maybe?
		System.out.println("Total loops (print every 500): "+n1);
		System.out.println("Total loops (print every 50000): "+n2);
	}

}

