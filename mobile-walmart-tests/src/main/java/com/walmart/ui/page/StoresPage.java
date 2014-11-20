package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class StoresPage extends BasePage {

	@AndroidFindBy(id = "com.walmart.android:id/search_field")
	private AppiumElement searchField;

	MainMenu mainMenu;
	TopMenu topMenu;

	public StoresPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	@Override
	public void checkPage() {
		searchField.waitForElement(FIVE_SEC_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		return topMenu.isTitleMatches("Stores");
	}

}
