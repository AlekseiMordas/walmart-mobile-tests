package com.walmart.ui.page;

import org.openqa.selenium.By;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.SortOption;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class SearchResulstPage extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	@AndroidFindBy(id = "com.walmart.android:id/left_button")
	private AppiumElement sortButton;

	@AndroidFindBy(id = "com.walmart.android:id/right_button")
	private AppiumElement filterButton;

	@AndroidFindBy(id = "com.walmart.android:id/message_page_text")
	private AppiumElement resultMessage;

	private static String RADIO_BUTTON = "//android.widget.CheckedTextView[@text='%s']";

	public SearchResulstPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	public SearchResulstPage sortByCryteria(SortOption sort) {
		sortButton.click();
		new AppiumElement(driver, By.xpath(String.format(RADIO_BUTTON,
				sort.toString()))).click();
		return new SearchResulstPage(driver);
	}

	public String getMessageWithZeroResultExist() {
		return resultMessage.getText();

	}

	public boolean isSortAndFilterButtonsExist() {
		return sortButton.isExists() && filterButton.isExists();
	}

	@Override
	public void checkPage() {
		menuBar.waitForElement(FIVE_SEC_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		System.out.println(menuBar.getText());
		return menuBar.getText().equals("Search Results");
	}

}
