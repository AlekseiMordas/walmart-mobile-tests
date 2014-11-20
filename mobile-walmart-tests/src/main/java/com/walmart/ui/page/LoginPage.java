package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.exceptions.ErrorMessagePresent;
import com.walmart.ui.bo.User;

public class LoginPage extends BasePage {

	@AndroidFindBy(id = "com.walmart.android:id/sign_in_create_account_button")
	private AppiumElement createAnAccountButton;

	@AndroidFindBy(id = "com.walmart.android:id/sign_in_error_info_text")
	private AppiumElement errorMessage;

	@AndroidFindBy(id = "com.walmart.android:id/sign_in_button")
	private AppiumElement signInButton;

	@AndroidFindBy(id = "com.walmart.android:id/sign_in_username")
	private AppiumElement emailField;

	@AndroidFindBy(id = "com.walmart.android:id/sign_in_password")
	private AppiumElement passwordField;

	public LoginPage(final AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public HomePage login(User user) {
		emailField.type(user.getEmailAddress());
		passwordField.type(user.getPassword());
		signInButton.click();
		checkNoErrorsOnPage();
		return new HomePage(driver);
	}

	public SignUpPage clickCreateAnAccount() {
		createAnAccountButton.click();
		return new SignUpPage(driver);
	}

	public void checkNoErrorsOnPage() {
		if (errorMessage.isExists()) {
			throw new ErrorMessagePresent("Error message appears: "
					+ errorMessage.getText());
		}

	}

	@Override
	public void checkPage() {
		emailField.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Not implemented");
	}

}
