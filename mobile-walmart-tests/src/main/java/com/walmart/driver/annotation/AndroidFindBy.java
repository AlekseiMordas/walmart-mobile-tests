package com.walmart.driver.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.TYPE })
public @interface AndroidFindBy {
	public abstract String uiAutomator() default "";

	public abstract String accessibility() default "";

	public abstract String id() default "";

	public abstract String name() default "";

	public abstract String className() default "";

	public abstract String tagName() default "";

	public abstract String xpath() default "";
}