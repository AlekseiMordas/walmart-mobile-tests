package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;



public class HomePage extends BasePage {

	@AndroidFindBy(id = "com.walmart.android:id/online_cart_icon")
	private AppiumElement onlinebag;
	
	@AndroidFindBy(id = "com.walmart.android:id/menu_item_search")
	private AppiumElement searchButton;

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;
	
	public MainMenu mainMenu;

	public HomePage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		checkPage();
	}

	
	@Override
	public void checkPage() {
		onlinebag.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		// TODO Auto-generated method stub
		return menuBar.getText().equals("Home");
	}
}
