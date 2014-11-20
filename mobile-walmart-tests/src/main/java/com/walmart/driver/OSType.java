package com.walmart.driver;
public enum OSType {
	ANDROID("Android"), IOS("iOS");

	private String os;

	private OSType(String os) {
		this.os = os;
	}

	public String getOs() {
		return os;
	}
}