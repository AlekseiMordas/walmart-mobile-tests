package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;


import com.clioption.ICliOption;
import com.walmart.runner.DeviceConfig;


public class DeviceHostOption implements ICliOption {
	private static final String DEFAULT_HOST = "0.0.0.0";

	public String[] getDefaultValue() {
		return new String[] { DEFAULT_HOST };
	}

	public Option getOption() {
		return new Option("h", "host", true, "Host where Appium Server located");
	}

	public void parse(String[] values) {
		DeviceConfig.setHost(values[0]);
	}
}
