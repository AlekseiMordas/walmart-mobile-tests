package com.walmart.driver;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.walmart.runner.Devices;
import com.walmart.utils.ApplicationStorage;

/**
 * @author aleksei_mordas
 * 
 */
public class CapabilitiesFactory {

	public static DesiredCapabilities capabilities = new DesiredCapabilities();

	private static final Logger LOGGER = Logger
			.getLogger(CapabilitiesFactory.class);

	public static DesiredCapabilities createIOSCapabilities(final Devices device) {
		capabilities
				.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				MobilePlatform.IOS);
		capabilities.setCapability("deviceName", "iPhone Retina (4-inch)");
		capabilities.setCapability(MobileCapabilityType.APP, new File(
				ApplicationStorage.getDefaultPathToApp()).getAbsolutePath());
		LOGGER.info("CAPABILITY PATH: "
				+ new File(ApplicationStorage.getDefaultPathToApp())
						.getAbsolutePath());
		return capabilities;
	}

	public static DesiredCapabilities createAndroidCapabilities(
			final Devices device) {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "nexu4");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				"Appium");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				MobilePlatform.ANDROID);
	//	capabilities.setCapability("newCommandTimeout", 120);
		capabilities.setCapability(MobileCapabilityType.APP, new File(
				ApplicationStorage.getDefaultPathToApp()).getAbsolutePath());
		LOGGER.info("CAPABILITY PATH: "
				+ new File(ApplicationStorage.getDefaultPathToApp())
						.getAbsolutePath());
		return capabilities;
	}

	public static DesiredCapabilities createIphoneCapabilities() {
		return createIOSCapabilities(Devices.IPHONE);
	}

}
