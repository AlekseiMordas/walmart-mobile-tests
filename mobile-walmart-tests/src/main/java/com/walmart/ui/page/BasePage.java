package com.walmart.ui.page;

import org.openqa.selenium.JavascriptExecutor;

import com.walmart.driver.OSType;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.page.Page;
import com.walmart.driver.page.PageFactory;
import com.walmart.runner.DeviceConfig;
import com.walmart.utils.wait.Sleeper;



public abstract class BasePage extends Page {

	protected AppiumDriver driver;

	protected static final String LABEL_ATTRIBUTE = "label";

	protected static final int FIVE_SEC_TIMEOUT = 5;

	protected static final int WAIT_FOR_ELEMENT_TIMEOUT = 20;

	protected static final int SLEEP_TIMEOUT = 10000;

	protected static final int WAIT_WHILE_LOGIN = 30;

	protected static final long WAIT_SLEPPER_TIMEOUT = 5000;

	public BasePage(final AppiumDriver driver) {
		PageFactory.initElements(driver, this, OSType.valueOf(DeviceConfig.getOs()));
		this.driver = driver;
	}

	/**
	 * @param delay
	 *            in seconds
	 */
	public void pause(int delay) {
		Sleeper.SYSTEM_SLEEPER.sleep(delay);
	}

	protected void makeScreenshot() {
		driver.takeScreenshot("");
	}

	public void dragFirstSlider() {
		JavascriptExecutor js = driver;
		String script = "target.frontMostApp().mainWindow().tableViews()[0]."
				+ "dragInsideWithOptions({startOffset:{x:0.12, y:0.16}, endOffset:{x:0.50, y:0.16}, duration:1.4});";
		js.executeScript(script);
	}

	public void dragSecondsSlider() {
		JavascriptExecutor js = driver;
		String script = "target.frontMostApp().mainWindow().tableViews()[0]."
				+ "dragInsideWithOptions({startOffset:{x:0.12, y:0.31}, endOffset:{x:0.60, y:0.31}, duration:1.5});";
		js.executeScript(script);
	}

}