package com.walmart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.walmart.ui.page.ShopPage;

public class SearchTest extends BaseTest {

	private static final String IPHONE_6_GREY_QUERY = "iphone 6";
	private static final String TEST_QUERY = "qweasdzxc";
	private static String EMPTY_MESSAGE = "We found 0 results for: %s\n\nPlease check your spelling or use different keywords and try again.";

	@Test(priority = 0, groups = "search")
	public void verifySearchBarAccessibility() {
		Assert.assertTrue(searchService.isSearchAccesibleOnHomePage(),
				"Search not accesible from Home page");
		ShopPage shopPage = homeService.openRollbackFromHome();
		Assert.assertTrue(shopPage.topMenu.isSearchItemExist(),
				"Search not accesible from Shop page");
		Assert.assertTrue(shopPage.openFirstRollbackCategory().topMenu
				.isSearchItemExist(), "Search not accesible from Rollout page");
		homeService.openHomePage().clickShopByDepartmentCell();
		Assert.assertTrue(shopService.openCategory("Auto & Tires").topMenu
				.isSearchItemExist(), "Search not accesible from Category page");
		Assert.assertTrue(
				shopService.openCategory("Tires").topMenu.isSearchItemExist(),
				"Search not accesible from SubCategory page");
	}

	@Test(priority = 1, groups = "search")
	public void checkMultiWordTerm() {
		Assert.assertTrue(searchService.doSearch(IPHONE_6_GREY_QUERY)
				.isSortAndFilterButtonsExist());

	}

	@DataProvider(name = "searchQuery")
	private Object[][] getQueries() {
		return new String[][] { { "qweasdzxc" }, { "!@#$%" } };
	}

	@Test(priority = 2, groups = "search", dataProvider = "searchQuery")
	public void checkSearchReturnsZero(String search) {
		Assert.assertEquals(searchService.doSearch(search)
				.getMessageWithZeroResultExist(), String.format(EMPTY_MESSAGE,
				search), "Messages are not equals");

	}

	@Test(priority = 2, groups = "search")
	public void checkSearchResultsSortByBestSellers() {
		Assert.assertTrue(searchService
				.isSearchResultsSortByBestSellers(IPHONE_6_GREY_QUERY),
				"Sort was not successful");

	}

}
