package module8;

/**
 * Class providing a main method that invokes two threads, one that counts down from
 * a given number of seconds (10) and another that sequantially finds prime numbers.
 * On termination of the countdown timer, the prime number thread is terminated.
 * The results of the prime number finder will be printed out.
 */
public class ThreadsMain {

	public static void main(String[] args) throws InterruptedException {

		/** Number of seconds to count down from */
		long countdown = 10;
		
		CountdownTask countdownTask = new CountdownTask(countdown);
		PrimeNumberTask primeTask = new PrimeNumberTask();

		Thread countdownThread = new Thread(countdownTask);
		Thread primenumberThread = new Thread(primeTask);

		countdownThread.start();
		primenumberThread.start();

		countdownThread.join();
		primenumberThread.interrupt();

		System.out.println("Largest number checked if prime: " + primeTask.getLargestNumberChecked());
		System.out.println("Number of primes found: " + primeTask.getNumPrimes());
		System.out.println("Largest prime found: " + primeTask.getLargestPrime());

	}

}
