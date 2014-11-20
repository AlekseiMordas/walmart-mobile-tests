package com.walmart.driver.element;

import java.awt.Rectangle;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.utils.wait.AppiumWait;
import com.walmart.utils.wait.Function;


public class AppiumElement implements Element {

	private static final Logger LOGGER = Logger.getLogger(AppiumElement.class);

	private final AppiumDriver driver;

	private final By by;

	public AppiumElement(final AppiumDriver driver, final By by) {

		this.driver = driver;
		this.by = by;
	}

	@Override
	public void waitForElement(final long timeoutSeconds) {
		LOGGER.info("Waiting element '" + by.toString()
				+ "'. Timeout in seconds: " + timeoutSeconds);
		try {
			new AppiumWait(driver, timeoutSeconds)
					.until(new Function<AppiumDriver, Boolean>() {
						@Override
						public Boolean apply(final AppiumDriver d) {
							try {
								return d.findElement(by).isDisplayed();
							} catch (final Exception e) {
								return false;
							}
						}
					});
		} catch (final NoSuchElementException e) {
			driver.quit();
			throw new RuntimeException("Failed to wait element "
					+ e.getMessage());
		}

	}

	public void touch() {
		LOGGER.info("Touching element '" + by.toString() + "' ...");
		driver.findElement(by).click();
		LOGGER.info("Element '" + by.toString() + "' touched Successfully");
	}

	@Override
	public void type(final String text) {
		driver.findElement(by).sendKeys(text);
		LOGGER.info("Type text '" + text + "' ...");
	}

	public void typeWithUIAutomation(final String text) {
		driver.typeTextUsingAppleInstruments(text);
		LOGGER.info("Type text '" + text + "' ...");
	}

	@Override
	public void clear() {
		driver.findElement(by).clear();
		LOGGER.info("field '" + by.toString() + "' cleared");
	}

	@Override
	public void click() {
		LOGGER.info("Clicking element '" + by.toString() + "' ...");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(by));
			driver.findElement(by).click();
		} catch (TimeoutException e) {
			driver.findElement(by).click();
		}

		LOGGER.info("Element '" + by.toString() + "' clicked Successfully");
	}

	@Override
	public String getText() {
		return driver.findElement(by).getText();
	}

	public Dimension getSize() {
		final WebElement el = driver.findElement(by);
		final org.openqa.selenium.Dimension elSize = el.getSize();
		final Dimension dimension = new Dimension(elSize.width, elSize.height);
		return dimension;
	}

	public Point getLocation() {
		final WebElement el = driver.findElement(by);
		return el.getLocation();
	}

	public String getAttribute(final String attribute) {
		return driver.findElement(by).getAttribute(attribute);
	}

	@Override
	public boolean isVisible() {
		try {
			return driver.findElement(by).isDisplayed();
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	public boolean isChecked() {
		try {
			return driver.findElement(by).isSelected();
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	public boolean isEnabled() {
		try {
			return driver.findElement(by).isEnabled();
		} catch (final Exception e) {
			return false;
		}
	}

	@Override
	public boolean isExists() {
		boolean isExist = !driver.findElements(by).isEmpty();
		LOGGER.info("Check is element exist '" + by.toString() + ". Result: "
				+ isExist);
		return isExist;
	}

	@Override
	public void waitForChild(final Element ccButtonOn, final int i) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void dragHorizontally(final float[] offset) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void scrollUpToLabel(final String label, final int timeout) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public void dragTo(final Element element) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public String getFoundBy() {
		return this.by.toString();
	}

	public By getdBy() {
		return this.by;
	}

	@Override
	public boolean isChildExists(final Element child) {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public Rectangle getFrame() {
		throw new RuntimeException("Method not supported");
	}

	@Override
	public Rectangle getFrameWithoutPadding() {
		throw new RuntimeException("Method not supported");
	}

}
