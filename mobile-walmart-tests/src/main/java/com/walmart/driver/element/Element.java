package com.walmart.driver.element;

import java.awt.Rectangle;

public interface Element {
	boolean isExists();

	void waitForElement(long timeoutSeconds);

	void click();

	void clear();

	void type(String test);

	String getText();

	void waitForChild(Element ccButtonOn, int i);

	boolean isVisible();

	boolean isChecked();

	boolean isEnabled();

	void dragHorizontally(float[] offset);

	void scrollUpToLabel(String label, int timeout);

	void dragTo(Element element);

	String getFoundBy();

	boolean isChildExists(Element child);

	Rectangle getFrame();

	Rectangle getFrameWithoutPadding();

}