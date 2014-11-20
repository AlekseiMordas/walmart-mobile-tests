package com.walmart.driver.appiumdriver;

import java.awt.Dimension;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.walmart.driver.annotation.IOSFindBy;
import com.walmart.utils.ScreenshotUtils;
import com.walmart.utils.wait.AppiumWait;
import com.walmart.utils.wait.Function;


public class AppiumDriver extends io.appium.java_client.AppiumDriver {

	private static final Logger LOGGER = Logger.getLogger(AppiumDriver.class);
	private RemoteWebDriver driver;

	public AppiumDriver(String url, DesiredCapabilities capabilities)
			throws MalformedURLException {
		super(new URL(url), capabilities);
		LOGGER.info("Connection url: " + url);
		super.manage().timeouts().implicitlyWait(5L, TimeUnit.SECONDS);
		setWebDriver(this);
	}

	private void setWebDriver(AppiumDriver appiumDriver) {
		this.driver = appiumDriver;

	}

	public void executeJs(String js, Object... args) {
		super.executeScript(js, args);
	}

	public void waitForElement(final By by, long timeOutInSeconds) {
		LOGGER.info("Waiting for element '" + by.toString()
				+ "' exists during " + timeOutInSeconds + "sec timeout ...");
		new WebDriverWait(this, timeOutInSeconds)
				.until(new ExpectedCondition<Boolean>() {
					@Override
					public Boolean apply(WebDriver d) {
						try {
							List<WebElement> elements = d.findElements(by);
							boolean result = false;
							if (!elements.isEmpty()) {
								result = elements.get(0).isDisplayed();
							}
							return result;
						} catch (NoSuchElementException e) {
							return false;
						}
					}
				});
	}

	public WebElement findElement(IOSFindBy by) {
		List<WebElement> elements = super.findElements((By) by);
		if (!elements.isEmpty()) {
			return elements.get(0);
		}
		throw new RuntimeException("Element with locator " + by.toString()
				+ " not found");
	}

	@Override
	public List<WebElement> findElements(By by) {
		return super.findElements(by);
	}

	public void waitImplicitly(int seconds) {
		super.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}

	public void takeScreenshot(String screenshotName) {
		ScreenshotUtils.makeScreenshot(this, screenshotName);
	}

	public boolean scrollDown() {
		try {
			super.executeScript("UIATarget.localTarget().frontMostApp().mainWindow().scrollViews()[0].scrollDown()");
			LOGGER.info("Scrolled down");
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public boolean scrollToTop() {
		return scrollUp();
	}

	public boolean scrollUp() {

		try {
			super.executeScript("UIATarget.localTarget().frontMostApp().mainWindow().scrollViews()[0].scrollUp()");
			LOGGER.info("Scrolled up");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public boolean waitForText(String text, int minNumberOfMatches,
			long timeOut, boolean doScroll, boolean onlyVisible) {
		throw new RuntimeException("Method not supported");

	}

	public void clickLong(float x, float y) {
		throw new RuntimeException("Method not supported");

	}

	public void dragTo(float fromX, float fromY, float toX, float toY, int steps) {
		throw new RuntimeException("Method not supported");

	}

	public Dimension getScreenSize() {
		org.openqa.selenium.Dimension windowSize = super.manage().window()
				.getSize();
		Dimension dimension = new Dimension(windowSize.width, windowSize.height);
		return dimension;
	}

	public boolean searchText(String text) {
		throw new RuntimeException("Method not supported");

	}

	public String findLogMessage(String[] logCmd, String messageToFind,
			int timeoutSeconds) {
		throw new RuntimeException("Method not supported");

	}

	public void swipeRight() {
		throw new RuntimeException("Method not supported");

	}

	public void swipeLeft() {
		throw new RuntimeException("Method not supported");

	}

	public boolean scrollToBottom() {
		return scrollDown();
	}

	public void flick(float fromX, float fromY, float toX, float toY) {
		throw new RuntimeException("Method not supported");

	}

	public void touch(float x, float y) {
		clickWithOptions(1, 1, 0.5, x, y);
	}

	public void touchByCoordinates(double x, double y, String nameVariable) {
		HashMap<String, Double> tapObject = new HashMap<String, Double>();
		tapObject.put("x", Double.valueOf(x));
		tapObject.put("y", Double.valueOf(y));
		JavascriptExecutor js = this.driver;
		js.executeScript("mobile: tap", new Object[] { tapObject });
		LOGGER.info("Element '" + nameVariable
				+ "' touched successfully by coordinates");
	}

	public boolean waitForLogMessage(String[] logCmd, String messageToWait,
			int timeoutSeconds) {
		throw new RuntimeException("Method not supported");

	}

	@Override
	public void quit() {
		LOGGER.info("Quit driver");
		super.quit();
	}

	@Override
	public String getPageSource() {
		return super.getPageSource();
	}

	public void executeKeycode(int keyValue) {
		HashMap<String, Integer> keycode = new HashMap<String, Integer>();
		keycode.put("keycode", keyValue);
		executeJs("mobile: keyevent", keycode);
	}

	public void closeKeyboard(String button) {
		HashMap<String, String> keyboardObject = new HashMap<String, String>();
		keyboardObject.put("keyName", button);
		executeJs("mobile: hideKeyboard", keyboardObject);
	}

	public WebDriver getWebDriver() {
		return driver;
	}

	public void clickOnText(String text) {
		throw new RuntimeException("Method not supported");

	}

	public void goBack() {
		throw new RuntimeException("Method not supported");

	}

	public void clickOnImage(int index) {
		throw new RuntimeException("Method not supported");

	}

	public void clickOnTextView(int index) {
		throw new RuntimeException("Method not supported");

	}

	public void switchOn(String name) {
		this.switchTo().window(name);
	}

	public boolean searchText(String text, int numberOfMatches, boolean scroll) {
		throw new RuntimeException("Method not supported");
	}

	public boolean waitForText(final String message, int startAddTimeout) {
		return new AppiumWait(this, startAddTimeout)
				.until(new Function<AppiumDriver, Boolean>() {
					@Override
					public Boolean apply(AppiumDriver d) {
						try {
							return d.findElement(By.xpath(message))
									.isDisplayed();
						} catch (Exception e) {
							return false;
						}
					}
				});
	}

	public void waitForTextNotVisible(final String message, int addTimeout) {
		new AppiumWait(this, addTimeout)
				.until(new Function<AppiumDriver, Boolean>() {
					@Override
					public Boolean apply(AppiumDriver d) {
						try {
							return !d.findElement(By.xpath(message))
									.isDisplayed();
						} catch (Exception e) {
							return true;
						}
					}
				});
	}

	public void swipe(int touchCount, double duration, double startX,
			double startY, double endX, double endY) {
		HashMap<String, Object> swipeObject = new HashMap<String, Object>();
		swipeObject.put("startX", startX);
		swipeObject.put("startY", startY);
		swipeObject.put("endX", endX);
		swipeObject.put("endY", endY);
		swipeObject.put("touchCount", touchCount);
		swipeObject.put("duration", duration);
		executeJs("mobile: swipe", swipeObject);
	}

	public void typeTextUsingAppleInstruments(String text) {
		JavascriptExecutor js = driver;
		String script = "var vKeyboard = target.frontMostApp().keyboard();"
				+ "vKeyboard.setInterKeyDelay(0.3);"
				+ "vKeyboard.typeString(\"" + text + "\");";
		js.executeScript(script);
		LOGGER.info("Text: " + text + " sent using Apple Instruments");
	}

	public void clickWithOptions(int tapCount, int touchCount, double duration,
			double x, double y) {
		HashMap<String, Object> tapObject = new HashMap<String, Object>();
		tapObject.put("x", x);
		tapObject.put("y", y);
		tapObject.put("tapCount", tapCount);
		tapObject.put("touchCount", touchCount);
		tapObject.put("duration", duration);
		executeJs("mobile: tap", tapObject);
	}
}
