package com.walmart.ui.page;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.annotation.IOSFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.bo.User;


public class SignUpPage extends BasePage {

	@AndroidFindBy(id = "com.walmart.android:id/create_account_first_name")
	private AppiumElement fisrtNameField;

	@AndroidFindBy(id = "com.walmart.android:id/create_account_last_name")
	private AppiumElement lastNameField;

	@AndroidFindBy(id = "com.walmart.android:id/create_account_email")
	private AppiumElement emailAddressField;

	@AndroidFindBy(id = "com.walmart.android:id/create_account_password")
	private AppiumElement passwordField;

	@AndroidFindBy(id = "com.walmart.android:id/create_account_create_button")
	private AppiumElement continueButton;
	
	// @IOSFindBy(xpath = "//UIAButton[@name='Dismiss']")
	// private AppiumElement dismissButton;

	public SignUpPage(final AppiumDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void fillRegistrationData(final User user) {
		fisrtNameField.click();
		fisrtNameField.type(user.getFirstName());
		lastNameField.click();
		lastNameField.type(user.getLastName());
		emailAddressField.click();
		emailAddressField.type(user.getEmailAddress());
		passwordField.click();
		passwordField.type(user.getPassword());
	}

	public HomePage clickContinue() {
		continueButton.click();
		return new HomePage(driver);
	}
	

//	@Override
//	public HomePage clickDismissButton() {
//		dismissButton.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
//		dismissButton.click();
//		return new HomePage(driver);
//	}


	@Override
	public void checkPage() {
		fisrtNameField.waitForElement(WAIT_FOR_ELEMENT_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		// TODO Auto-generated method stub
		return false;
	}

}
