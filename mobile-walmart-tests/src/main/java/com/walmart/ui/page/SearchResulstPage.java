package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;

public class SearchResulstPage extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	public SearchResulstPage(final AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
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
