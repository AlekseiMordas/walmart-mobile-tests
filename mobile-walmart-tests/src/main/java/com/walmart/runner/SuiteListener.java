package com.walmart.runner;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.remote.SessionNotFoundException;
import org.testng.IConfigurationListener;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.walmart.driver.IosDriverWrapper;
import com.walmart.utils.HttpClient;



public class SuiteListener implements ISuiteListener, ITestListener,
		IConfigurationListener {

	private static final Logger LOGGER = Logger.getLogger(SuiteListener.class);
	private static final String URL = "http://%s:%s/wd/hub";

	@Override
	public void onTestFailure(ITestResult result) {
		IosDriverWrapper.getDriver().takeScreenshot("");
		LOGGER.info("================================== TEST "
				+ result.getName()
				+ " FAILED ==================================");
	}

	@Override
	public void onTestStart(ITestResult result) {

		LOGGER.info("================================== TEST "
				+ result.getName()
				+ " STARTED ==================================");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		LOGGER.info("================================== TEST "
				+ result.getName()
				+ " SUCCESS ==================================");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("================================== TEST "
				+ result.getName()
				+ " SKIPPED ==================================");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	@Override
	public void onFinish(ITestContext context) {

	}

	@Override
	public void onStart(ISuite suite) {

	}

	@Override
	public void onFinish(ISuite suite) {

		boolean isFailed = false;

		IResultMap failedConfigs;
		IResultMap failedTests;
		IResultMap skippedConfigs;
		IResultMap skippedTests;

		Map<String, ISuiteResult> suiteResults = suite.getResults();

		for (ISuiteResult res : suiteResults.values()) {
			failedConfigs = res.getTestContext().getFailedConfigurations();
			failedTests = res.getTestContext().getFailedTests();
			skippedConfigs = res.getTestContext().getSkippedConfigurations();
			skippedTests = res.getTestContext().getSkippedTests();

			if (failedConfigs.size() != 0 || failedTests.size() != 0
					|| skippedConfigs.size() != 0 || skippedTests.size() != 0) {
				isFailed = true;
				break;
			}
		}

		if (!isFailed && !suiteResults.isEmpty()) {
			BuildResult.setExitResult(BuildResult.SUCCESS);
		}
	}

	@Override
	public void onStart(ITestContext context) {
		LOGGER.info("================================== TEST "
				+ context.getName().toUpperCase()
				+ " STARTED ==================================");
	}

	@Override
	public void onConfigurationSuccess(ITestResult itr) {

	}

	@Override
	public void onConfigurationFailure(ITestResult itr) {
		String session;
		if (IosDriverWrapper.getDriver() != null) {
			try {
				Cookie cookie = IosDriverWrapper.getDriver().manage()
						.getCookieNamed("JSESSIONID");
				session = cookie.getValue();
			} catch (SessionNotFoundException e) {
				throw new RuntimeException(
						"Session not found. Seems appium not responds");
			}
		} else {
			throw new RuntimeException(
					"Driver is null. Seems appium not responds");
		}
		String url = String.format(URL, DeviceConfig.getHost(),
				DeviceConfig.getPort())
				+ String.format("/session/%s/screenshot", session);
		try {
			if (!((HttpClient.getInstance(url).getResponseCode()) == 404)) {
				IosDriverWrapper.getDriver().takeScreenshot("");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onConfigurationSkip(ITestResult itr) {

	}

}
