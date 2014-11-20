package com.walmart.ui.service;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.tests.constants.Payment;
import com.walmart.ui.bo.User;
import com.walmart.ui.page.LoginPage;
import com.walmart.ui.page.SignUpPage;

import com.walmart.ui.page.HomePage;


public class TestService {
	private final AppiumDriver driver;

	private HomePage homePage;
	private LoginPage loginPage;
	private SignUpPage signUp;


	public TestService(final AppiumDriver driver) {
		this.driver = driver;

	}

	public void doLogin(final User user) {
		openLoginPage();
		loginPage.login(user).checkPage();
	}

	public void loginIfNeeded(final User user) {
		homePage = new HomePage(driver);
		if (homePage.mainMenu.isLogInButtonVisible()) {
			loginPage = homePage.mainMenu.clickLogIn();
			loginPage.checkPage();
			loginPage.login(user);
		} else {
			homePage.mainMenu.openLeftMenu();
		}
	}

	public void openLoginPage() {
		homePage = new HomePage(driver);
		loginPage = homePage.mainMenu.openLeftMenu().clickLogIn();
		loginPage.checkPage();
	}

	public boolean isCreatingNewUserSuccessful(final User user) {
		openLoginPage();
		signUp = loginPage.clickCreateAnAccount();
		signUp.checkPage();
		signUp.fillRegistrationData(user);
		homePage = signUp.clickContinue();
		return homePage.mainMenu.openLeftMenu().isLogOutButtonVisible();
	}

}
