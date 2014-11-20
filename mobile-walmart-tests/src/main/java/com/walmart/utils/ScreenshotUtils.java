package com.walmart.utils;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionNotFoundException;

public class ScreenshotUtils {

	private static final String SCREENSHOTS = "screenshots";

	private static final Logger LOGGER = Logger
			.getLogger(ScreenshotUtils.class);

	private static final String DATE_FORMAT = "dd_MMM_yyyy__hh_mm_ssaa_SSS";

	private static String fileSeparator = System.getProperty("file.separator");

	public synchronized static void makeScreenshot(
			final RemoteWebDriver driver, String screenshotName) {
		try {
			// final WebDriver augmentedDriver = new
			// Augmenter().augment(driver);
			final Date date = new Date();
			final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

			File directory = new File("");
			directory = new File(directory.getAbsolutePath() + fileSeparator
					+ "test-output" + fileSeparator + "html" + fileSeparator
					+ SCREENSHOTS + fileSeparator);

			directory.mkdirs();

			screenshotName = screenshotName + "_" + dateFormat.format(date);

			final File f = new File(directory, screenshotName + ".png");
			if (!f.exists()) {
				f.createNewFile();
			}
			byte[] scrFile;
			try {
				scrFile = ((TakesScreenshot) driver)

				.getScreenshotAs(OutputType.BYTES);
			} catch (final SessionNotFoundException e) {
				throw new RuntimeException(e.getMessage()
						+ "Seems ios simulator didn't respond", e);
			}

			try {

				FileUtils.writeByteArrayToFile(f, scrFile);
			} catch (final IOException e) {
				throw new RuntimeException(e.getMessage(), e);
			}

			final String newFileNamePath = "<a href=\"" + SCREENSHOTS + "/"
					+ screenshotName + ".png" + "\">screenshot-"
					+ screenshotName + "</a>";

			LOGGER.info(newFileNamePath);

		} catch (final IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
}
