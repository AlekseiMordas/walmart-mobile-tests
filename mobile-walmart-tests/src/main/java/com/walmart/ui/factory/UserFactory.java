package com.walmart.ui.factory;

import org.apache.commons.lang3.RandomStringUtils;

import com.walmart.ui.bo.User;

public class UserFactory {

	public static User getNewUser() {
		final User user = new User();
		user.setEmailAddress((RandomStringUtils.randomAlphabetic(5) + "@"
				+ RandomStringUtils.randomAlphabetic(4) + "." + RandomStringUtils
				.randomAlphabetic(3)).toLowerCase());
		user.setFirstName("Alex");
		user.setLastName("WalmartTest");
		user.setPassword("123456");
		return user;
	}

	public static User getRegistereUser() {
		final User user = getNewUser();
		user.setEmailAddress("kgvof@gmail.com");
		return user;
	}

	public static User getIncorrectUser() {
		final User user = getNewUser();
		user.setEmailAddress("incorrect@gmail.com");
		return user;
	}
}
