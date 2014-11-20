package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class ShopPage extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	public MainMenu mainMenu;
	public TopMenu topMenu;

	public ShopPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	@Override
	public void checkPage() {
		menuBar.waitForElement(FIVE_SEC_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		return topMenu.isTitleMatches("Shop");
	}

}
