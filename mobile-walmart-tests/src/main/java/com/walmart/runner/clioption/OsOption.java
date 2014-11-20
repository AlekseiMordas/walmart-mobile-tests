package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;


import com.clioption.ICliOption;
import com.walmart.driver.OSType;
import com.walmart.runner.DeviceConfig;

public class OsOption implements ICliOption {

	private static final String DEFAULT_DEVICE = OSType.ANDROID.name();

	public String[] getDefaultValue() {

		return new String[] { DEFAULT_DEVICE };
	}

	public Option getOption() {
		return new Option("os", "os", true, "Operation system on device");
	}

	public void parse(String[] values) {
		DeviceConfig.setOs(values[0]);
	}

}