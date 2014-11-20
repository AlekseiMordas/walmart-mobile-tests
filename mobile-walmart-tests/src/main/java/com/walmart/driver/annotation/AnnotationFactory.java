package com.walmart.driver.annotation;

import io.appium.java_client.MobileBy;
import org.openqa.selenium.By;

public class AnnotationFactory {

	private final static String DEFAULT_VALUE = "";

	public static By createBy(IOSFindBy iFindBy) {
		if (!iFindBy.xpath().equals(DEFAULT_VALUE)) {
			return By.xpath(iFindBy.xpath());
		}
		if (!iFindBy.id().equals(DEFAULT_VALUE)) {
			return By.id(iFindBy.id());
		}
		if (!iFindBy.name().equals(DEFAULT_VALUE)) {
			return By.name(iFindBy.name());
		}
		if (!iFindBy.className().equals(DEFAULT_VALUE)) {
			return By.className(iFindBy.className());
		}
		if (!iFindBy.linkText().equals(DEFAULT_VALUE)) {
			return By.linkText(iFindBy.linkText());
		}
		if (!iFindBy.partialLinkText().equals(DEFAULT_VALUE)) {
			return By.partialLinkText(iFindBy.partialLinkText());
		}
		if (!iFindBy.iosUiAutomation().equals(DEFAULT_VALUE)) {
			return MobileBy.IosUIAutomation(iFindBy.iosUiAutomation());
		}
		if (!iFindBy.accessibilityId().equals(DEFAULT_VALUE)) {
			return MobileBy.AccessibilityId(iFindBy.accessibilityId());
		}
		throw new AnnotationAttributeException("Invalid annotation attribute");
	}

	public static By createBy(AndroidFindBy androidFindBy) {
		if (!androidFindBy.xpath().equals(DEFAULT_VALUE)) {
			return By.xpath(androidFindBy.xpath());
		}
		if (!androidFindBy.id().equals(DEFAULT_VALUE)) {
			return By.id(androidFindBy.id());
		}
		if (!androidFindBy.name().equals(DEFAULT_VALUE)) {
			return By.name(androidFindBy.name());
		}
		if (!androidFindBy.className().equals(DEFAULT_VALUE)) {
			return By.className(androidFindBy.className());
		}
		if (!androidFindBy.uiAutomator().equals(DEFAULT_VALUE)) {
			return MobileBy.AndroidUIAutomator(androidFindBy.uiAutomator());
		}
		if (!androidFindBy.accessibility().equals(DEFAULT_VALUE)) {
			return MobileBy.AccessibilityId(androidFindBy.accessibility());
		}
		throw new AnnotationAttributeException("Invalid annotation attribute");
	}
}
