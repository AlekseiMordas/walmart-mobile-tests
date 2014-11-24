package com.walmart.ui.page;

import io.appium.java_client.android.AndroidKeyCode;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.browserlaunchers.Sleeper;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class HomePage extends BasePage {

	private static final String SHOP = "Shop";

	private static final String STORES = "Stores";

	@AndroidFindBy(xpath = "//android.view.View[1]/android.widget.LinearLayout[1]")
	private AppiumElement homeTitle;

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

	@AndroidFindBy(name = "Cancel")
	private AppiumElement cancelButton;

	@AndroidFindBy(id = "com.walmart.android:id/rollback")
	private AppiumElement rollbacksCell;

	@AndroidFindBy(id = "com.walmart.android:id/local_ad")
	private AppiumElement weeklyAdCell;

	@AndroidFindBy(id = "com.walmart.android:id/find_store")
	private AppiumElement findAStoreCell;

	@AndroidFindBy(id = "com.walmart.android:id/pharmacy")
	private AppiumElement pharmacyCell;

	@AndroidFindBy(name = "Get Google Play services")
	private AppiumElement getGooglePlayServices;

	public MainMenu mainMenu;
	public TopMenu topMenu;

	public HomePage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
		checkPage();
	}

	public boolean isShopPageOpen() {
		return menuBar.getText().equals(SHOP);
	}

	@SuppressWarnings("unchecked")
	public <T extends BasePage> T clickAdvertisment() {
		ad.click();
		if (cancelButton.isExists()) {
			cancelButton.click();
			driver.sendKeyEvent(AndroidKeyCode.BACK);
			Sleeper.sleepTight(1);
			driver.sendKeyEvent(AndroidKeyCode.BACK);
			Sleeper.sleepTight(2);
			if (!ad.isExists()) {
				driver.sendKeyEvent(AndroidKeyCode.BACK);
			}
			ad.click();
		}
		if (menuBar.getText().equals(SHOP)) {
			return (T) new ShopPage(driver);
		}
		if (menuBar.getText().equals(STORES)) {
			return (T) new StoresPage(driver);
		} else {
			return (T) new SearchResulstPage(driver);
		}
	}

	public HomePage clickSearchIcon() {
		searchButton.click();
		return new HomePage(driver);
	}

	public SearchResulstPage openSearchItem(String text) {
		searchField.type(text);
		Point point = ad.getLocation();
		Dimension dim = ad.getSize();
		driver.touchByCoordinates(point.getX() + dim.getWidth() / 2,
				point.getY() + dim.getHeight() / 2, text);
		return new SearchResulstPage(driver);
	}

	public CartPage clickCartIcon() {
		onlinebag.click();
		return new CartPage(driver);
	}

	public ShopPage clickShopByDepartmentCell() {
		shopByDepartmentCell.click();
		return new ShopPage(driver);
	}

	public StoresPage clickFindAStoreCell() {
		driver.customSwipe();
		findAStoreCell.click();
		if (cancelButton.isExists()) {
			cancelButton.click();
			driver.sendKeyEvent(AndroidKeyCode.BACK);
		}
		if (getGooglePlayServices.isExists()) {
			driver.sendKeyEvent(AndroidKeyCode.BACK);
		}
		return new StoresPage(driver);
	}

	public PharmacyPage clickPharmacyCell() {
		driver.customSwipe();
		pharmacyCell.click();
		return new PharmacyPage(driver);
	}

	public ShopPage clickRollbackCell() {
		rollbacksCell.click();
		return new ShopPage(driver);
	}

	public StoresPage clickWeelkyAdCell() {
		weeklyAdCell.click();
		if (cancelButton.isExists()) {
			cancelButton.click();
			driver.sendKeyEvent(AndroidKeyCode.BACK);
		}
		return new StoresPage(driver);
	}

	@Override
	public void checkPage() {
		homeTitle.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		return menuBar.getText().equals("Home");
	}
}
