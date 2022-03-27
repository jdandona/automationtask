package com.kaiahealth.automationtask.ScreenActions;

import org.openqa.selenium.WebDriver;

import com.kaiahealth.automationtask.ScreenDSL.ScreenHelper;

public class WelcomeScreen extends ScreenHelper {
	
	WebDriver driver;

	public WelcomeScreen(WebDriver driver) {
		super(driver, "WelcomeScreen");
		this.driver = driver;
	}
	
	
	public void navigateToLoginScreen() {
		
		click(element("button_Login"));
	}
	
	public void navigateToCreateAcountScreen() {
		click(element("button_createAccount"));
	}
	
	

}
