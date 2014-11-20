package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;



import com.clioption.ICliOption;
import com.walmart.runner.DeviceConfig;

public class AppiumDirectory implements ICliOption {
	private static final String DEFAULT_DIR = "/Users/hudson/build/workspace/ios-ads-sdk-tests/appium";
	//private static final String DEFAULT_DIR = "/Users/aleksei_mordas/Documents/appium/";

	public String[] getDefaultValue() {
		return new String[] { DEFAULT_DIR };
	}

	public Option getOption() {
		return new Option("ad", "appiumDir", true,
				"Directory where Appium located");
	}

	public void parse(String[] values) {
		DeviceConfig.setAppiumDir(values[0]);
	}
}
