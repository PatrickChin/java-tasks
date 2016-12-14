package module8;

import java.util.ArrayList;

/**
 * Class that sequentially finds and stores prime numbers to an array list
 */
public class PrimeNumberTask implements Runnable {

	/** List of primes found */
	private ArrayList<Integer> primes = new ArrayList<>();

	/** The current number to check if prime or not */
	private int currentNumber = 3;

	/** Constructor that initialises the list of primes to contain { 2 } */
	public PrimeNumberTask() {
		primes.add(2);
	}

	/**
	 * Checks if the argument `n` is prime or not.
	 * This will only work for values less than the square of the maximum
	 * prime in the `primes` array. Since this class searches primes 
	 * sequentially, this is not a problem.
	 */
	private boolean isPrime(int n) {
		for (int prime : primes) {
			if (prime * prime > n) // no factors found less than the sqrt of n
				break;
			if (n % prime == 0) // n is divisible `prime`
				return false;
		}
		return true;
	}

	/** Get the largest found prime */
	public int getLargestPrime() {
		return primes.get(primes.size() - 1);
	}

	/** Get the number of primes found */
	public int getNumPrimes() {
		return primes.size();
	}

	/** get the largest number that was checked */
	public int getLargestNumberChecked() {
		return currentNumber;
	}

	@Override
	public void run() {
		for (; currentNumber <= Integer.MAX_VALUE; currentNumber+=2) {
			if (isPrime(currentNumber))
				primes.add(currentNumber);

			// return if thread was interrupted
			if (Thread.currentThread().isInterrupted())
				return;
		}
	}

}
