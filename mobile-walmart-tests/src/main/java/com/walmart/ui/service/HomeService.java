package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.page.HomePage;
import com.walmart.ui.page.SearchResulstPage;
import com.walmart.ui.page.ShopPage;
import com.walmart.ui.page.module.MainMenu;

public class HomeService {
	private final AppiumDriver driver;

	private HomePage homePage;

	public HomeService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public void openShopPage() {
		homePage = new HomePage(driver);
		homePage.clickRollbackCell();
	}

	public HomePage openHomePage() {
		return new MainMenu(driver).open().clickHome();
	}

	public boolean isGlobalMenuExpanded() {
		homePage = new HomePage(driver);
		return homePage.mainMenu.open().isPageOpens();
	}

	public boolean isCartIconTakeToCartPage() {
		return homePage.topMenu.clickCartIcon().isPageOpens();
	}

	public boolean isAbleToSearch(String name) {
		return new HomePage(driver).clickSearchIcon().openSearchItem(name)
				.isPageOpens();
	}

	public SearchResulstPage doSearch(String name) {
		return new HomePage(driver).clickSearchIcon().openSearchItem(name);
	}

	public boolean isSearchAccesibleOnHomePage() {
		return new HomePage(driver).topMenu.isSearchItemExist();
	}

	public boolean isBarcodeScannerExist() {
		return new HomePage(driver).topMenu.isBarCodeIconExist();
	}

	public ShopPage openShopByDeptPage() {
		return new HomePage(driver).clickShopByDepartmentCell();
	}

	public boolean isShopByDeptButtonTakeToListOfDepartments() {
		return openShopByDeptPage().isPageOpens();
	}

	public boolean isBannerTakeToConfiguredAction() {
		return new HomePage(driver).clickAdvertisment().isPageOpens();
	}

	public ShopPage openRollbackFromHome() {
		return new HomePage(driver).clickRollbackCell();
	}

	public boolean isRollbacksTakeToRollbacPage() {
		return new HomePage(driver).clickRollbackCell().isPageOpens();
	}

	public boolean isWeeklyAdTakeToShopPage() {
		return new HomePage(driver).clickWeelkyAdCell().isPageOpens();
	}

	public boolean isFindStoreTakeToStoresPage() {
		return new HomePage(driver).clickFindAStoreCell().isPageOpens();
	}

	public boolean isPharmacyTakeTopharmacyPage() {
		return new HomePage(driver).clickPharmacyCell().isPageOpens();
	}

}
