package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;


import com.clioption.ICliOption;
import com.walmart.runner.DeviceConfig;


public class ServerFlags implements ICliOption {
	private static final String DEFAULT_FLAG =
			 "/Users/aleksei_mordas/Downloads/swisstokandroidlp.apk";
	
	public String[] getDefaultValue() {
		return new String[] { DEFAULT_FLAG };
	}

	public Option getOption() {
		return new Option("sf", "serverFlags", true,
				"Start Appium server with flags");
	}

	public void parse(String[] values) {
		DeviceConfig.setFlags(values[0]);
	}
}
