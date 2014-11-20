package com.walmart.utils.wait;



import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.TimeUnit;

import com.walmart.utils.wait.exception.TimeoutException;


public class FluentWait<T> implements Wait<T> {

	public static Duration FIVE_HUNDRED_MILLIS = new Duration(500,
			TimeUnit.MILLISECONDS);

	private final T input;
	private final Clock clock;
	private final Sleeper sleeper;

	private Duration timeout = FIVE_HUNDRED_MILLIS;
	private Duration interval = FIVE_HUNDRED_MILLIS;
	private String message = null;

	/**
	 * @param input
	 *            The input value to pass to the evaluated conditions.
	 */
	public FluentWait(T input) {
		this(input, new Clock(), Sleeper.SYSTEM_SLEEPER);
	}

	/**
	 * @param input
	 *            The input value to pass to the evaluated conditions.
	 * @param clock
	 *            The clock to use when measuring the timeout.
	 * @param sleeper
	 *            Used to put the thread to sleep between evaluation loops.
	 */
	public FluentWait(T input, Clock clock, Sleeper sleeper) {
		this.input = Preconditions.checkNotNull(input);
		this.clock = Preconditions.checkNotNull(clock);
		this.sleeper = Preconditions.checkNotNull(sleeper);
	}

	/**
	 * Sets how long to wait for the evaluated condition to be true. The default
	 * timeout is {@link #FIVE_HUNDRED_MILLIS}.
	 * 
	 * @param duration
	 *            The timeout duration.
	 * @param unit
	 *            The unit of time.
	 * @return A self reference.
	 */
	public FluentWait<T> withTimeout(long duration, TimeUnit unit) {
		this.timeout = new Duration(duration, unit);
		return this;
	}

	/**
	 * Sets how often the condition should be evaluated.
	 * 
	 * <p>
	 * In reality, the interval may be greater as the cost of actually
	 * evaluating a condition function is not factored in. The default polling
	 * interval is {@link #FIVE_HUNDRED_MILLIS}.
	 * 
	 * @param duration
	 *            The timeout duration.
	 * @param unit
	 *            The unit of time.
	 * @return A self reference.
	 */
	public FluentWait<T> pollingEvery(long duration, TimeUnit unit) {
		this.interval = new Duration(duration, unit);
		return this;
	}

	/**
	 * Repeatedly applies this instance's input value to the given predicate
	 * until the timeout expires or the predicate evaluates to true.
	 * 
	 * @param isTrue
	 *            The predicate to wait on.
	 * @throws TimeoutException
	 *             If the timeout expires.
	 */
	public void until(final Predicate<T> isTrue) {
		until(new Function<T, Boolean>() {
			@Override
			public Boolean apply(T input) {
				return isTrue.apply(input);
			}
		});
	}

	/**
	 * Repeatedly applies this instance's input value to the given function
	 * until one of the following occurs:
	 * <ol>
	 * <li>the function returns neither null nor false,</li>
	 * <li>the function throws an unignored exception,</li>
	 * <li>the timeout expires,
	 * <li>
	 * <li>the current thread is interrupted</li>
	 * </ol>
	 * 
	 * @param isTrue
	 *            the parameter to pass to the {@link ExpectedConditions}
	 * @param <V>
	 *            The function's expected return type.
	 * @return The functions' return value if the function returned something
	 *         different from null or false before the timeout expired.
	 * @throws TimeoutException
	 *             If the timeout expires.
	 */
	@Override
	public <V> V until(Function<? super T, V> isTrue) {
		long end = clock.laterBy(timeout.in(TimeUnit.MILLISECONDS));
		Throwable lastException = null;
		while (true) {

			V value = isTrue.apply(input);
			if (value != null && Boolean.class.equals(value.getClass())) {
				if (Boolean.TRUE.equals(value)) {
					return value;
				}
			} else if (value != null) {
				return value;
			}

			// Check the timeout after evaluating the function to ensure
			// conditions
			// with a zero timeout can succeed.
			if (!clock.isNowBefore(end)) {
				String toAppend = message == null ? " waiting for "
						+ isTrue.toString() : ": " + message;

				String timeoutMessage = String.format(
						"Timed out after %d seconds%s",
						timeout.in(TimeUnit.SECONDS), toAppend);
				throw new TimeoutException(timeoutMessage, lastException);
			}

			sleeper.sleep(interval);

		}
	}

	public <V> V until(Function<? super T, V> isTrue,
			Class<? extends RuntimeException> exceptionClass,
			String exceptionMessage) {
		long end = clock.laterBy(timeout.in(TimeUnit.MILLISECONDS));

		while (true) {

			V value = isTrue.apply(input);
			if (value != null && Boolean.class.equals(value.getClass())) {
				if (Boolean.TRUE.equals(value)) {
					return value;
				}
			} else if (value != null) {
				return value;
			}

			// Check the timeout after evaluating the function to ensure
			// conditions
			// with a zero timeout can succeed.
			if (!clock.isNowBefore(end)) {
				String toAppend = message == null ? " waiting for "
						+ isTrue.toString() : ": " + message;

				String timeoutMessage = String.format(exceptionMessage
						+ " due to : Timed out after %d seconds%s",
						timeout.in(TimeUnit.SECONDS), toAppend);
				RuntimeException ex = buildException(exceptionClass,
						timeoutMessage);

				throw ex;
			}

			sleeper.sleep(interval);

		}
	}

	private RuntimeException buildException(
			Class<? extends RuntimeException> exceptionClass, String message) {
		Constructor<? extends RuntimeException> exConstructor;
		try {
			exConstructor = exceptionClass.getConstructor(String.class);
		} catch (SecurityException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		RuntimeException ex;
		try {
			ex = exConstructor.newInstance(message);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InstantiationException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e.getMessage(), e);
		}

		return ex;
	}

}
