package com.walmart.driver.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ java.lang.annotation.ElementType.FIELD })
public @interface IOSFindBy {

	public abstract String id() default "";	
	
	public abstract String xpath() default "";
	
	public abstract String className() default "";

	public abstract String name() default "";

	public abstract String linkText() default "";

	public abstract String iosUiAutomation() default "";
	
	public abstract String accessibilityId() default "";
	
	public abstract String partialLinkText() default "";


}
