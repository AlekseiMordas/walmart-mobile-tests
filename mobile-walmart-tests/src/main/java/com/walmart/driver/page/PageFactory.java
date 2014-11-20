package com.walmart.driver.page;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.openqa.selenium.By;

import com.walmart.driver.OSType;
import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.annotation.AnnotationFactory;
import com.walmart.driver.annotation.IOSFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.driver.element.Element;


public class PageFactory {

	public static <T extends Page> T initElements(AppiumDriver driver,
			Class<T> pageClass, OSType os) {
		T page = instantiatePage(driver, pageClass);
		initElements(driver, page, os);
		return page;
	}

	private static <T extends Page> T instantiatePage(AppiumDriver driver,
			Class<T> pageClass) {
		try {
			try {
				Constructor<T> constructor = pageClass
						.getConstructor(new Class[] { AppiumDriver.class });
				return constructor.newInstance(driver);
			} catch (NoSuchMethodException e) {
				return pageClass.newInstance();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static <T extends Page> void initElements(AppiumDriver driver,
			T page, OSType os) {
		for (Field field : page.getClass().getDeclaredFields()) {
			try {
				@SuppressWarnings("rawtypes")
				Class fieldClass = field.getType();
				if (Element.class.isAssignableFrom(fieldClass)) {

					AndroidFindBy androidAnnotation = null;
					IOSFindBy iosAnnotation = null;
					By by = null;
					switch (os) {
					case IOS:
						iosAnnotation = (IOSFindBy) field.getAnnotation(IOSFindBy.class);
						by = AnnotationFactory.createBy(iosAnnotation);
						break;
					case ANDROID:
						androidAnnotation = (AndroidFindBy) field.getAnnotation(AndroidFindBy.class);
						by = AnnotationFactory.createBy(androidAnnotation);
						break;
					default:
						break;
					}
					Element element = new AppiumElement(driver,
							by);
					
					field.setAccessible(true);
					field.set(page, element);
				}
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
		}
	
}
