package com.kaiahealth.automationtask.ScreenActions;

import org.openqa.selenium.WebDriver;

import com.kaiahealth.automationtask.ScreenDSL.ScreenHelper;

public class LoginScreen extends ScreenHelper {

	WebDriver driver;

	public LoginScreen(WebDriver driver) {
		super(driver, "LoginScreen");
		this.driver = driver;
	}
	
	
	public void userLogin(String userName,String password) {
		element("input_Username").sendKeys(userName);
		element("input_password").sendKeys(password);
		click(element("button_LoginWithEmail"));
	}
	
	public boolean IsUserPresentOnLoginScreen() {
		return wait.waitForElementToBeVisible(element("heading_Login")).isDisplayed();
	}
	
	public boolean verifyLoginError() {
		
		return wait.waitForElementToBeVisible(element("text_errorMessage")).isDisplayed();
	}

}
