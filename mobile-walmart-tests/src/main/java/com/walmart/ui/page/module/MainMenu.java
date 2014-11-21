package com.walmart.ui.page.module;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.annotation.IOSFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.BasePage;
import com.walmart.ui.page.LoginPage;
import com.walmart.ui.page.HomePage;
import com.walmart.utils.wait.Sleeper;


public class MainMenu extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	@AndroidFindBy(name = "Sign In")
	private AppiumElement loginButton;
	
	@AndroidFindBy(name = "Sign Out")
	private AppiumElement logOutButton;
	
	@AndroidFindBy(name = "Shop")
	private AppiumElement shopTextView;

	public MainMenu(final AppiumDriver driver) {
		super(driver);
	}

	public MainMenu open() {
		if(!isPageOpens()) {
			menuBar.click();
		}
		return new MainMenu(driver);
	}

	public LoginPage clickLogIn() {
		if (isLogOutButtonVisible()) {
			logOutButton.click();
			new HomePage(driver).checkPage();
			menuBar.click();
		}
		loginButton.click();
		return new LoginPage(driver);
	}

	public boolean isLogOutButtonVisible() {
		return logOutButton.isExists();
	}
	
	public boolean isLogInButtonVisible() {
		return loginButton.isExists();
	}

	@Override
	public void checkPage() {
		shopTextView.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		// TODO Auto-generated method stub
		return shopTextView.isExists();
	}
}
