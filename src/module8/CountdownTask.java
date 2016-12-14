package module8;

/**
 * Class that implementing the Runnable interface that
 * when run will count down for a given length of time
 * printing the time left in the countdown in one second
 * intervals.
 */
public class CountdownTask implements Runnable {

	/** countdown and print intervals in milliseconds */
	public final long countdown, interval = 1000;

	/**
	 * Constructor initialising the countdown time in seconds,
	 * first converting it to milliseconds.
	 */
	public CountdownTask(long countdownsec) {
		this.countdown = countdownsec * 1000;
	}

	@Override
	public void run() {
		long tnow  = System.currentTimeMillis();
		long tend  = tnow + countdown; // time that the countdown should end

		// The time of the next time, at which the time left should be printed
		long tnext = tnow;

		while (tnow <= tend) {
			if (tnow >= tnext) {
				long timeleft = (tend - tnow) / 1000;
				System.out.println("Time left: "+timeleft+" s");
				tnext += interval; // update next time
			}
			tnow = System.currentTimeMillis();
		}
	}

}
