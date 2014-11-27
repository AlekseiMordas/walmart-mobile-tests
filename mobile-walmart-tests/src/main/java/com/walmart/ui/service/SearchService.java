package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.page.SearchResulstPage;
import com.walmart.ui.page.ShopPage;

public class SearchService {
	private final AppiumDriver driver;

	private SearchResulstPage searchResult;
	private ShopPage shopPage;

	public SearchService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public boolean isSortAndFilterButtonsExist() {
		searchResult = new SearchResulstPage(driver);
		return searchResult.isSortAndFilterButtonsExist();
	}

	public ShopPage openCategory(String name) {
		shopPage = new ShopPage(driver);
		return shopPage.openCategory(name);
	}

}
