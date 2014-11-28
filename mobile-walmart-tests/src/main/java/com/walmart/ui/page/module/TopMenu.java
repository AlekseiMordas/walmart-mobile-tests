package com.walmart.ui.page.module;

import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.Point;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.BasePage;
import com.walmart.ui.page.CartPage;
import com.walmart.ui.page.SearchResulstPage;

public class TopMenu extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement titlePage;

	@AndroidFindBy(id = "com.walmart.android:id/online_cart_icon")
	private AppiumElement onlinebag;

	@AndroidFindBy(id = "com.walmart.android:id/barcode_icon")
	private AppiumElement barCodeIcon;

	@AndroidFindBy(id = "com.walmart.android:id/search_src_text")
	private AppiumElement searchField;

	@AndroidFindBy(id = "com.walmart.android:id/menu_item_search")
	private AppiumElement searchButton;

	public TopMenu(final AppiumDriver driver) {
		super(driver);
	}

	private void clickSearchIcon() {
		searchButton.click();
	}

	public SearchResulstPage doSearchWithWildCard(String text) {
		clickSearchIcon();
		searchField.type(text);
		Point point = searchField.getCenter();
		driver.touchByCoordinates(point.getX(), point.getY() + 25,
				"searchResult");
		return new SearchResulstPage(driver);
	}

	public SearchResulstPage doSearch(String text) {
		clickSearchIcon();
		searchField.type(text);
		driver.sendKeyEvent(AndroidKeyCode.ENTER);
		return new SearchResulstPage(driver);
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

	public boolean isSearchItemExist() {
		return searchButton.isExists();
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
