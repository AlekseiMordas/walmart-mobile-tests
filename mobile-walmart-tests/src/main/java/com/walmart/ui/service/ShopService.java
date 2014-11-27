package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.page.HomePage;
import com.walmart.ui.page.SearchResulstPage;
import com.walmart.ui.page.ShopPage;

public class ShopService {
	private final AppiumDriver driver;

	private HomePage homePage;
	private ShopPage shopPage;

	public ShopService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public SearchResulstPage openRollbackPage() {
		shopPage = new ShopPage(driver);
		return shopPage.openFirstRollbackCategory();
	}

	public ShopPage openCategory(String name) {
		shopPage = new ShopPage(driver);
		return shopPage.openCategory(name);
	}

}
