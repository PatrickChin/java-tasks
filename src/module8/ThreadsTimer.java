package module8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Class with a main method that calculated and prints out stats comparing
 * single threaded and multi threaded monte carlo methods for calculating pi
 */
public class ThreadsTimer {

	/** Single threaded monte carlo method for calculating pi */
	public static Double calcPi(long nPoints) {
		MonteCarloPiCalculatorTask task = new MonteCarloPiCalculatorTask(nPoints);
		return task.call();
	}

	/** Multi threaded monte carlo method for calculating pi */
	public static Double calcPiMultithreaded(long nPoints, int nThreads)
			throws InterruptedException, ExecutionException {

		// Create thread pool to organise execution of threads
		ExecutorService threadPool = Executors.newFixedThreadPool(nThreads);
		List<Future<Double>> futures = new ArrayList<Future<Double>>(nThreads);

		// Initialise thread tasks
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			MonteCarloPiCalculatorTask task =
				new MonteCarloPiCalculatorTask(nPoints/nThreads);
			Future<Double> future = threadPool.submit(task);
			futures.add(future);
		}

		// Wait for, and get the values of pi for all threads to average
		double sum = 0.0;
		for (int iThread = 0; iThread < nThreads; ++iThread) {
			double result = futures.get(iThread).get();
			sum += result;
		}

		threadPool.shutdown();
		return sum/nThreads;
	}

	public static void main(String[] args) throws
			InterruptedException, ExecutionException {

		/** Number of random points to generate */
		final long nPoints = 10_000_000L;
		final int nThreads = 4;

		/*
		 * Calculating pi using single-threaded calculator
		 */
		{
			System.out.println("running the single threaded monte carlo pi calculator \n" +
				"using with "+nPoints+" random points ...");
			long start = System.currentTimeMillis();
			double pi = calcPi(nPoints);
			long end = System.currentTimeMillis();
			System.out.println("done.");
			System.out.println("That took "+(end-start)+" ms to complete");
			System.out.println("The calculated value of pi: "+pi);
		}

		System.out.println();
		System.out.println();
		System.out.println();

		/*
		 * Calculating pi using multi-threaded calculator
		 */
		{
			System.out.println("running the multi-threaded monte carlo pi calculator \n" +
				"using with "+nPoints+" random points and "+nThreads+" parallel threads  ...");
			long start = System.currentTimeMillis();
			double pi = calcPiMultithreaded(nPoints, nThreads);
			long end = System.currentTimeMillis();
			System.out.println("done.");
			System.out.println("That took "+(end-start)+" ms to complete");
			System.out.println("The calculated value of pi: "+pi);
		}

	}

}
