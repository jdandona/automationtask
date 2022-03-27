package com.kaiahealth.automationtask.TestInitiator;


import org.openqa.selenium.WebDriver;

import com.kaiahealth.automationtask.ScreenActions.WelcomeScreen;
import com.kaiahealth.automationtask.ScreenActions.DashboardScreen;
import com.kaiahealth.automationtask.ScreenActions.LoginScreen;



public class ScreenObjectProvider {

	private WebDriver driver;

	public ScreenObjectProvider(WebDriver driver) {
		this.driver = driver;

	}
	
	public LoginScreen getLoginScreen() {
		return new LoginScreen(driver);
	}
	
	public WelcomeScreen getLandingScreen() {
		return new WelcomeScreen(driver);
	}
	
	public DashboardScreen getDashboardScreen() {
		return new DashboardScreen(driver);
	}
	
	


	
}
