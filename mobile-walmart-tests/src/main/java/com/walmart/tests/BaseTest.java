package com.walmart.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.walmart.driver.IosDriverWrapper;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.exceptions.XmlParametersException;
import com.walmart.runner.DeviceConfig;
import com.walmart.runner.Devices;
import com.walmart.ui.service.AuthService;
import com.walmart.ui.service.HomeService;
import com.walmart.ui.service.SearchService;
import com.walmart.ui.service.ShopService;

/**
 * @author aleksei_mordas
 * 
 */
public class BaseTest {

	protected static final String HOST = DeviceConfig.getHost();

	protected static final String PORT = DeviceConfig.getPort();

	protected static final String DEVICE = DeviceConfig.getOs();

	protected AppiumDriver driver;

	protected AuthService service;
	protected HomeService homeService;
	protected ShopService shopService;
	protected SearchService searchService;

	@BeforeClass(description = "Init and check page")
	public void initPages() throws Exception {

		switch (Devices.valueOf(DEVICE)) {
		case ANDROID:
			driver = IosDriverWrapper.getAndroid(HOST, PORT);
			service = new AuthService(driver);
			homeService = new HomeService(driver);
			shopService = new ShopService(driver);
			searchService = new SearchService(driver);
			break;
		default:
			throw new XmlParametersException("Invalid device");
		}

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}
