package com.walmart.utils.wait;

import java.util.concurrent.TimeUnit;

public interface Sleeper {

	public static final Sleeper SYSTEM_SLEEPER = new Sleeper() {
		@Override
		public void sleep(Duration duration) {
			try {
				Thread.sleep(duration.in(TimeUnit.MILLISECONDS));
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}

		@Override
		public void sleep(long milliseconds) {
			try {
				Thread.sleep(milliseconds);
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
	};

	/**
	 * Sleeps for the specified duration of time.
	 * 
	 * @param duration
	 *            How long to sleep.
	 * @throws InterruptedException
	 *             If hte thread is interrupted while sleeping.
	 */
	void sleep(Duration duration);

	/**
	 * Sleeps for the specified duration of time.
	 * 
	 * @param milliseconds
	 *            How long to sleep.
	 * @throws InterruptedException
	 *             If hte thread is interrupted while sleeping.
	 */
	void sleep(long milliseconds);
}
