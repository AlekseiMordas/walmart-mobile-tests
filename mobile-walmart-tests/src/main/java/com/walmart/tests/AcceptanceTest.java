package com.walmart.tests;

import io.appium.java_client.android.AndroidKeyCode;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AcceptanceTest extends BaseTest {

	@Test(description = "Home Screen Links", priority = 0)
	public void checkHomeScreenLinks() {
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(homeService.isGlobalMenuExpanded(),
				"Global Menu not expanded");
		softAssert.assertTrue(homeService.isCartIconTakeToCartPage(),
				"Cart icon didn't take to cart page");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(searchService.isAbleToSearch("iphone 6"),
				"Not able to search");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(homeService.isBarcodeScannerExist(),
				"Barcode icon not exist");
		softAssert.assertTrue(homeService.isBannerTakeToConfiguredAction(),
				"Banner didn't take to newxt Action");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(homeService.isRollbacksTakeToRollbacPage(),
				"Rollback page not openned");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(
				homeService.isShopByDeptButtonTakeToListOfDepartments(),
				"List of Departments not exis");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(homeService.isWeeklyAdTakeToShopPage(),
				"Weekly ad didn't take to shop Page");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(homeService.isFindStoreTakeToStoresPage(),
				"Find store didn,t take to store page");
		driver.sendKeyEvent(AndroidKeyCode.BACK);
		softAssert.assertTrue(homeService.isPharmacyTakeTopharmacyPage(),
				"Pharmacy didn't take to pharmacy page");
		softAssert.assertAll();
	}

}
