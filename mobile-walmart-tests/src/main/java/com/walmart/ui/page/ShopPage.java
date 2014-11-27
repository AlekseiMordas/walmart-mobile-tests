package com.walmart.ui.page;

import org.openqa.selenium.By;

import com.walmart.driver.annotation.AndroidFindBy;
import com.walmart.driver.appiumdriver.AppiumDriver;
import com.walmart.driver.element.AppiumElement;
import com.walmart.ui.page.module.MainMenu;
import com.walmart.ui.page.module.TopMenu;

public class ShopPage extends BasePage {

	@AndroidFindBy(id = "android:id/action_bar_title")
	private AppiumElement menuBar;

	@AndroidFindBy(xpath = "//android.view.View[2]/android.widget.FrameLayout[1]/android.widget.ListView[1]/android.widget.LinearLayout[2]")
	private AppiumElement firstCategory;

	public ShopPage(final AppiumDriver driver) {
		super(driver);
		mainMenu = new MainMenu(driver);
		topMenu = new TopMenu(driver);
	}

	public SearchResulstPage openFirstRollbackCategory() {
		firstCategory.click();
		return new SearchResulstPage(driver);
	}
	
	public ShopPage openCategory(String name) {
		driver.findElement(By.name(name)).click();
		return new ShopPage(driver);
	}

	@Override
	public void checkPage() {
		menuBar.waitForElement(FIVE_SEC_TIMEOUT);
	}

	@Override
	public boolean isPageOpens() {
		return topMenu.isTitleMatches("Shop");
	}

}
