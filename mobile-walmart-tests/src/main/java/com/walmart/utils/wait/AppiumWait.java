package com.walmart.utils.wait;



import java.util.concurrent.TimeUnit;

import com.walmart.driver.appiumdriver.AppiumDriver;


public class AppiumWait extends FluentWait<AppiumDriver>{
	private static final long DEFAULT_POLLING_TIMEOUT = 2000;

	private static final long DEFAULT_TIMEOUT_SEC = 30;

	public AppiumWait(final AppiumDriver driver, final long timeoutSec, final long pollingTimeoutMillis) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(pollingTimeoutMillis, TimeUnit.MILLISECONDS);
	}

	public AppiumWait(final AppiumDriver driver, final long timeoutSec) {
		super(driver);
		withTimeout(timeoutSec, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}

	public AppiumWait(final AppiumDriver driver) {
		super(driver);
		withTimeout(DEFAULT_TIMEOUT_SEC, TimeUnit.SECONDS).pollingEvery(DEFAULT_POLLING_TIMEOUT, TimeUnit.MILLISECONDS);
	}

}
