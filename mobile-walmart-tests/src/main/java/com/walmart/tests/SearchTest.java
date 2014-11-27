package com.walmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.walmart.ui.page.ShopPage;

public class SearchTest extends BaseTest {

	@Test(priority = 0, groups = "search")
	public void verifySearchBarAccessibility() {
		Assert.assertTrue(homeService.isSearchAccesibleOnHomePage(),
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

		Assert.assertTrue(homeService.isSearchAccesibleOnHomePage(),
				"Search not accesible from Home page");
	}

	@Test(groups = "search")
	public void checkMultiWordTerm() {
		Assert.assertTrue(homeService.doSearch("iphone 6 grey").isSortAndFilterButtonsExist());

	}

}
