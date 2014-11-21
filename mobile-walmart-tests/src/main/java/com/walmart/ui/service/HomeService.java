package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;

import com.walmart.ui.page.HomePage;

public class HomeService {
	private final AppiumDriver driver;

	private HomePage homePage;

	public HomeService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public boolean isGlobalMenuExpanded() {
		homePage = new HomePage(driver);
		return homePage.mainMenu.open().isPageOpens();
	}

	public boolean isCartIconTakeToCartPage() {
		return new HomePage(driver).topMenu.clickCartIcon().isPageOpens();
	}

	public boolean isAbleToSearch() {
		return new HomePage(driver).clickSearchIcon().openSearchItem("Barcode")
				.isPageOpens();
	}

	public boolean isBarcodeScannerExist() {
		return new HomePage(driver).topMenu.isBarCodeIconExist();
	}

	public boolean isShopByDeptButtonTakeToListOfDepartments() {
		return new HomePage(driver).clickShopByDepartmentCell().isPageOpens();
	}

	public boolean isBannerTakeToConfiguredAction() {
		return new HomePage(driver).clickAdvertisment().isPageOpens();
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
