package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.page.HomePage;
import com.walmart.ui.page.SearchResulstPage;
import com.walmart.ui.page.ShopPage;

public class SearchService {
	private final AppiumDriver driver;

	private SearchResulstPage searchResult;
	private ShopPage shopPage;

	public SearchService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public boolean isAbleToSearch(String name) {
		return new HomePage(driver).topMenu.doSearch(name).isPageOpens();
	}

	public SearchResulstPage doSearch(String name) {
		return new HomePage(driver).topMenu.doSearch(name);
	}

	public boolean isSearchAccesibleOnHomePage() {
		return new HomePage(driver).topMenu.isSearchItemExist();
	}


	// public ShopPage openCategory(String name) {
	// shopPage = new ShopPage(driver);
	// return shopPage.openCategory(name);
	// }

}
