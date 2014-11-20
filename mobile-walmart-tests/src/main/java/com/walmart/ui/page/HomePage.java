package com.walmart.ui.page;

import io.appium.java_client.AndroidKeyCode;

import org.openqa.selenium.Point;
import org.openqa.selenium.browserlaunchers.Sleeper;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class HomePage extends BasePage {

	@AndroidFindBy(id = "com.walmart.android:id/online_cart_icon")
	private AppiumElement onlinebag;

	@AndroidFindBy(id = "com.walmart.android:id/menu_item_search")
	private AppiumElement searchButton;

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	@AndroidFindBy(id = "com.walmart.android:id/shop")
	private AppiumElement shopByDepartmentCell;

	@AndroidFindBy(id = "com.walmart.android:id/search_src_text")
	private AppiumElement searchField;

	@AndroidFindBy(id = "com.walmart.android:id/view_pager")
	private AppiumElement ad;

	@AndroidFindBy(id = "com.walmart.android:id/barcode_icon")
	private AppiumElement barCodeIcon;

	@AndroidFindBy(name = "Cancel")
	private AppiumElement cancelButton;

	@AndroidFindBy(id = "com.walmart.android:id/rollback")
	private AppiumElement rollbacksCell;

	@AndroidFindBy(id = "com.walmart.android:id/local_ad")
	private AppiumElement weeklyAdCell;

	@AndroidFindBy(id = "com.walmart.android:id/find_store")
	private AppiumElement findAStoreCell;
	
	@AndroidFindBy(name="Get Google Play services")
	private AppiumElement getGooglePlayServices;

	public MainMenu mainMenu;
	public TopMenu topMenu;

	public HomePage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
		checkPage();
	}

	public boolean isBarCodeIconVisible() {
		return barCodeIcon.isExists();
	}

	public boolean isShopPageOpen() {
		return menuBar.getText().equals("Shop");
	}

	public SearchResulstPage clickAdvertisment() {
		ad.click();
		if (cancelButton.isExists()) {
			driver.sendKeyEvent(AndroidKeyCode.BACK);
			driver.sendKeyEvent(AndroidKeyCode.BACK);
			Sleeper.sleepTight(5);
			ad.click();
		}
		return new SearchResulstPage(driver);
	}

	public HomePage clickSearchIcon() {
		searchButton.click();
		return new HomePage(driver);
	}

	public SearchResulstPage openSearchItem(String text) {
		searchField.type(text);
		Point point = ad.getLocation();
		driver.touchByCoordinates(point.getX() / 2, point.getY() / 2, text);
		return new SearchResulstPage(driver);
	}

	public CartPage clickCartIcon() {
		onlinebag.click();
		return new CartPage(driver);
	}

	public HomePage clickShopByDepartmentCell() {
		shopByDepartmentCell.click();
		return new HomePage(driver);
	}
	
	public HomePage clickFindAStoreCell() {
		findAStoreCell.click();
		return new HomePage(driver);
	}

	public ShopPage clickRollbackCell() {
		rollbacksCell.click();
		return new ShopPage(driver);
	}

	public ShopPage clickWeelkyAdCell() {
		weeklyAdCell.click();
		return new ShopPage(driver);
	}

	@Override
	public void checkPage() {
		onlinebag.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		return menuBar.getText().equals("Home");
	}
}
