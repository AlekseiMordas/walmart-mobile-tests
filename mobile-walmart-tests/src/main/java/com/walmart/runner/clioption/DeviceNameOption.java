package com.walmart.runner.clioption;

import org.apache.commons.cli.Option;


import com.clioption.ICliOption;
import com.walmart.runner.DeviceConfig;
import com.walmart.runner.Devices;

public class DeviceNameOption implements ICliOption {
	
	private static final String DEFAULT_DEVICE = Devices.IPHONE.name(); 

	public String[] getDefaultValue() {

		return new String[] { DEFAULT_DEVICE };
	}

	public Option getOption() {
		return new Option("device", "device", true, "Device name");
	}

	public void parse(String[] values) {
		DeviceConfig.setDevice(values[0]);
	}
}
