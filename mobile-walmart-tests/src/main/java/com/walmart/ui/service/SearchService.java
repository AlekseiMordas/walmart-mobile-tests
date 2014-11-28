package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.SortOption;
import com.walmart.ui.page.HomePage;
import com.walmart.ui.page.SearchResulstPage;
import com.walmart.ui.page.ShopPage;
import com.walmart.ui.page.module.MainMenu;

public class SearchService {
	private final AppiumDriver driver;

	private SearchResulstPage searchResult;
	private ShopPage shopPage;

	public SearchService(final AppiumDriver driver) {
		this.driver = driver;
	}

	public boolean isSearchResultsSortByBestSellers(String name) {
		return doSearch(name).sortByCryteria(SortOption.BEST_SELLERS)
				.isPageOpens();
	}

	public boolean isAbleToSearch(String name) {
		return new HomePage(driver).topMenu.doSearchWithWildCard(name)
				.isPageOpens();
	}

	public SearchResulstPage doSearchWithWildCard(String name) {
		return new HomePage(driver).topMenu.doSearchWithWildCard(name);
	}

	public SearchResulstPage doSearch(String name) {
		return new MainMenu(driver).open().clickHome().topMenu.doSearch(name);
	}

	public boolean isSearchAccesibleOnHomePage() {
		return new HomePage(driver).topMenu.isSearchItemExist();
	}

}
