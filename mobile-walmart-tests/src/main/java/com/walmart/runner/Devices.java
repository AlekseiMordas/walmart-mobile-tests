package com.walmart.runner;

public enum Devices {

	IPHONE("iPhone Simulator"), ANDROID("android"), WEB("web"), ;

	private final String text;

	private Devices(final String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return text;
	}
}
