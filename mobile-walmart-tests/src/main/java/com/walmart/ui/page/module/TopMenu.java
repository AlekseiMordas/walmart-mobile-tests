package com.walmart.ui.page.module;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.BasePage;
import com.walmart.ui.page.CartPage;

public class TopMenu extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement titlePage;

	@AndroidFindBy(id = "com.walmart.android:id/online_cart_icon")
	private AppiumElement onlinebag;
	

	@AndroidFindBy(id = "com.walmart.android:id/barcode_icon")
	private AppiumElement barCodeIcon;


	public TopMenu(final AppiumDriver driver) {
		super(driver);
	}

	public boolean isTitleMatches(String title) {
		return titlePage.getText().contains(title);
	}
	
	public CartPage clickCartIcon() {
		onlinebag.click();
		return new CartPage(driver);
	}

	public boolean isBarCodeIconExist() {
		return barCodeIcon.isExists();
	}

	@Override
	public void checkPage() {
		titlePage.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		// TODO Auto-generated method stub
		return titlePage.isExists();
	}
}
