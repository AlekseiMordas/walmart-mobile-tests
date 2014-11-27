package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class SearchResulstPage extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;
	
	@AndroidFindBy(id="com.walmart.android:id/left_button")
	private AppiumElement sortButton;

	@AndroidFindBy(id="com.walmart.android:id/right_button")
	private AppiumElement filterButton;
	
	public SearchResulstPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	public boolean isSortAndFilterButtonsExist() {
		return sortButton.isExists() && filterButton.isExists();
	}
	
	@Override
	public void checkPage() {
		menuBar.waitForElement(FIVE_SEC_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		System.out.println(menuBar.getText());
		return menuBar.getText().equals("Search Results");
	}

}
