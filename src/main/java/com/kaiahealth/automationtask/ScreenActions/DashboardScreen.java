package com.kaiahealth.automationtask.ScreenActions;

import org.openqa.selenium.WebDriver;

import com.kaiahealth.automationtask.ScreenDSL.ScreenHelper;

public class DashboardScreen extends ScreenHelper {

	WebDriver driver;

	public DashboardScreen(WebDriver driver) {
		super(driver, "DashboardScreen");
		this.driver = driver;
	}
	
	
	
	public boolean IsUserPresentOnDashboardScreen() {
		return wait.waitForElementToBeVisible(element("Heading_Name")).isDisplayed();
	}

}
