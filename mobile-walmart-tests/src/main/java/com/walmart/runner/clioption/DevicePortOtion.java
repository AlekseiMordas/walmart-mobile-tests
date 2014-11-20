package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;


import com.clioption.ICliOption;
import com.walmart.runner.DeviceConfig;


public class DevicePortOtion implements ICliOption{
	private static final String DEFAULT_PORT = "4723";

	public String[] getDefaultValue() {
		return new String[] { DEFAULT_PORT };
	}

	public Option getOption() {
		return new Option("p", "port", true, "Port where Appium server located");
	}

	public void parse(String[] values) {
		DeviceConfig.setPort(values[0]);
	}
}
