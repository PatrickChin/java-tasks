package module1;

public class Simple {

	public static void main(String[] args) {

		double doubVar = 20;
		float floatVar = 20;
		int intVar = 20;
		long longVar = 20;
		byte byteVar = 20;

		System.out.println(doubVar);
		System.out.println(floatVar);
		System.out.println(intVar);
		System.out.println(longVar);
		System.out.println(byteVar);
		System.out.println();

		doubVar *= doubVar;
		floatVar *= floatVar;
		intVar *= intVar;
		longVar *= longVar;
		byteVar *= byteVar;

		System.out.println(doubVar);
		System.out.println(floatVar);
		System.out.println(intVar);
		System.out.println(longVar);
		System.out.println(byteVar);
		System.out.println("Note: Byte has overflowed");
		System.out.println();

		// Implicit conversion of 10 to char
		char charVar = 'a'+10;
		System.out.println(charVar);
		System.out.println("ASCII table used when printing characters");
		System.out.println();

		// Error on lossy conversion from int to char
		// char charVar2 = 'a' + 1000000;

		// System.out.println();
		// System.out.println("Type conversion documentation can be found here:");
		// System.out.println("\thttp://docs.oracle.com/javase/specs/jls/se7/html/jls-5.html");

		System.out.println(longVar + intVar);
		System.out.println("Implicit conversion of intVar to long");
		System.out.println();

		System.out.println(doubVar + intVar);
		System.out.println("Implicit conversion of intVar to double");
		System.out.println();

		System.out.println("stringgg+" + intVar);
		System.out.println("`Integer.toString` is called (probably?) to append integer to string");
		System.out.println();

		int j; double d;
		// System.out.println(j);
		// System.out.println(d);
		System.out.println("Can't use uninitalised variables.");

		int i = (int) 12.99;
		System.out.println(i);
		System.out.println("Explicit conversion from double to int.\n" +
				   "Decimal digits are lost in conversion.");
		System.out.println();

		// Implicit conversion not allowed
		//int k = 123.32;
		
		// `i+3.2` return a double
		// Implicit conversion to int not allowed
		// i = i + 3.2;
		// Allowed here for some reason though
		i += 3.2;
    }

}

