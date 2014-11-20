package com.walmart.driver;

import java.net.MalformedURLException;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.runner.Devices;
import com.walmart.utils.HttpClient;



/**
 * @author aleksei_mordas
 * 
 */
public class IosDriverWrapper {

	private static final String SESSION_ID_MATCHER = "sessionId";

	private static final String STATUS_APPIUM = "/status";

	private static final String URL = "http://%s:%s/wd/hub";

	private static AppiumDriver instance;

	public static AppiumDriver getIphone(String host, String port) {
		if (!isSessionExist(host, port)) {
			try {
				instance = new AppiumDriver(String.format(URL, host, port),
						CapabilitiesFactory.createIphoneCapabilities());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}

	public static AppiumDriver getAndroid(String host, String port) {
		if (!isSessionExist(host, port)) {
			try {
				instance = new AppiumDriver(String.format(URL, host, port),
						CapabilitiesFactory.createAndroidCapabilities(Devices.ANDROID));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	public static AppiumDriver getDriver() {
		return instance;
	}

	public static boolean isSessionExist(String host, String port) {
		return HttpClient
				.getInstance(String.format(URL, host, port) + STATUS_APPIUM)
				.getRequest().contains(SESSION_ID_MATCHER);
	}
}
