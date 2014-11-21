package com.walmart.ui.page;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class PharmacyPage extends BasePage {

	public MainMenu mainMenu;
	public TopMenu topMenu;

	public PharmacyPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	@Override
	public void checkPage() {
		topMenu.checkPage();
	}

	@Override
	public boolean isPageOpens() {
		return topMenu.isTitleMatches("Pharmacy");
	}

}
