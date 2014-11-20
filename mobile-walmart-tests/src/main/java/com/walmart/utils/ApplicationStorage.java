package com.walmart.utils;

public class ApplicationStorage {

	private static final String AUT_PROPERTIES = "local.properties";

	private static final String DEFAULT_PATH_TO_APP = "default_path_to_app";

	public static String getDefaultPathToApp() {
		return getFromFile(DEFAULT_PATH_TO_APP);
	}

	private static String getFromFile(String key) {
		String autName = PropertyReader.getInstance().getProperty(
				AUT_PROPERTIES, key);
		return autName;
	}

}
