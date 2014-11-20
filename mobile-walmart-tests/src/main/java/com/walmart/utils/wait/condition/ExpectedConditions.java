package com.walmart.utils.wait.condition;


import org.apache.log4j.Logger;

import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.Element;


public class ExpectedConditions {
	private static final Logger log = Logger.getLogger(ExpectedConditions.class
			.getName());

	public static IExpectedCondition<Boolean> isVisible(final Element element) {
		return new IExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(AppiumDriver driver) {
				log.info("Is '" + element.getFoundBy() + "' element visible");
				return element.isVisible();
			}

			@Override
			public String toString() {
				return "Is '" + element.getFoundBy() + "' element visible";
			}
		};

	}

	public static IExpectedCondition<Boolean> isNotVisible(final Element element) {
		return new IExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(AppiumDriver driver) {
				log.info("Is '" + element.getFoundBy()
						+ "' element not visible");
				return !element.isVisible();
			}

			@Override
			public String toString() {
				return "Is '" + element.getFoundBy() + "' element not visible";
			}
		};

	}

}
