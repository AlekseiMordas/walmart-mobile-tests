package com.walmart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.walmart.ui.bo.User;
import com.walmart.ui.factory.UserFactory;



public class AcceptanceTest extends BaseTest {
	
	@Test(description = "Sign up with email", priority=0)
	public void signUpWithEmail() {
		final User user = UserFactory.getNewUser();
		Assert.assertTrue(service.isCreatingNewUserSuccessful(user),
				"Can't create new User with email");
	}

}
