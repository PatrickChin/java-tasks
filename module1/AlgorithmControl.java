package module1;

public class AlgorithmControl
{

	public static void loop()
	{
		for (int i = 0; i < 20; i++)
			System.out.println(i);
	}

	public static void decrement()
	{
		int i = 8;
		while (i >= -10)
			System.out.println(i--);
	}

	public static void increment()
	{
		// Note: I use 10.9 as the upper bound instead of 10.8 
		// to avoid dealing with floating precision errors.
		// i.e. `i` may reach 10.80000000001 and therefore not print 1.8
		for (double i = 1.2; i < 10.9; i+=0.4)
			System.out.println(i);
	}

	public static int timer(long t, int n)
	{
		long t1 = System.currentTimeMillis();
		long t2 = t1 + t;
		for (int i = 0; ; i++)
		{
			if (System.currentTimeMillis() < t2)
				return i;
			if (i % n == 0)
				System.out.println(i);
		}
	}

	public static void main(String[] args)
	{
		loop();
		decrement();
		increment();

		int n1 = timer(10000,500);   // more loops per second
		int n2 = timer(10000,50000); // less loops per second
		// Reasons:
		// printing takes time
		// branch prediction maybe?
		System.out.println("Total loops (print every 500): " n1);
		System.out.println("Total loops (print every 50000): " n2);
	}

}
