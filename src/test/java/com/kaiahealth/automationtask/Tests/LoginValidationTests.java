package com.kaiahealth.automationtask.Tests;

import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.kaiahealth.automationtask.ScreenActions.WelcomeScreen;
import com.kaiahealth.automationtask.ScreenActions.DashboardScreen;
import com.kaiahealth.automationtask.ScreenActions.LoginScreen;
import com.kaiahealth.automationtask.TestInitiator.MobileAppTestInitiator;
import static com.kaiahealth.automationtask.utilities.YamlReader.*;

public class LoginValidationTests {

	private String Platform;

	@Parameters("Platform")
	@BeforeClass
	public void Start_Test_Session(@Optional String Platform) {
		this.Platform = Platform;
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		Reporter.log("Starting test " + method.getName(), true);
	}

	@Test()
	public void Test01_Login_With_Invalid_Email() {
		
		 MobileAppTestInitiator test =  new MobileAppTestInitiator(Platform);
		 LoginScreen loginScreen =  test.getScreenObject().getLoginScreen();
		 WelcomeScreen landingScreen =  test.getScreenObject().getLandingScreen();
		
		landingScreen.navigateToLoginScreen();
		assertTrue(loginScreen.IsUserPresentOnLoginScreen(), "User doesn't navigate to login screen");
		Reporter.log("User Navigated to Login Screen", true);
		
		loginScreen.userLogin(getYamlValue("LoginTest.InvalidLogin.InvalidEmail.username")
				,getYamlValue("LoginTest.InvalidLogin.InvalidEmail.password"));
		assertTrue(loginScreen.verifyLoginError(), 
				"Invalid login Error Message doesn't displayed");
		Reporter.log("Invalid login Error Message displayed", true);
		
		

	}

	@Test()

	public void Test02_Login_With_ValidEmail_InvalidPassword() {

		MobileAppTestInitiator test =  new MobileAppTestInitiator(Platform);
		 LoginScreen loginScreen =  test.getScreenObject().getLoginScreen();
		 WelcomeScreen landingScreen =  test.getScreenObject().getLandingScreen();
		
		landingScreen.navigateToLoginScreen();
		assertTrue(loginScreen.IsUserPresentOnLoginScreen(), "User doesn't navigate to login screen");
		Reporter.log("User Navigated to Login Screen", true);
		
		loginScreen.userLogin(getYamlValue("LoginTest.InvalidLogin.InvalidPassword.username")
				,getYamlValue("LoginTest.InvalidLogin.InvalidPassword.password"));
		assertTrue(loginScreen.verifyLoginError(), 
				"Invalid login Error Message doesn't displayed");
		Reporter.log("Invalid login Error Message displayed", true);
	}

}
