package com.walmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.walmart.ui.bo.User;
import com.walmart.ui.factory.UserFactory;

public class AuthorizationTest extends BaseTest {

	@Test(description = "Sign up with email", priority = 0)
	public void signUpWithEmail() {
		final User user = UserFactory.getNewUser();
		Assert.assertTrue(service.isCreatingNewUserSuccessful(user),
				"Can't create new User with email");
	}

	@Test(description = "Login with registered user", priority = 1, enabled = true)
	private void loginWithRegisteredUser() throws Exception {
		service.doLogin(UserFactory.getRegistereUser());
		Assert.assertTrue(service.isLoggedIn(), "Log out button not visible");
	}

	@Test(description = "Login with incorrect credentials", priority = 2, enabled = false)
	private void loginWithIncorrectUser() throws Exception {
		service.doLogin(UserFactory.getIncorrectUser());
	}
}
