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

public class LoginTests {

	private MobileAppTestInitiator test;
	private LoginScreen loginScreen;
	private WelcomeScreen landingScreen;
	private DashboardScreen dashboardScreen;
	
	@Parameters("Platform")
	@BeforeClass
	public void Start_Test_Session(@ Optional String Platform) {
		test = new MobileAppTestInitiator(Platform);
		loginScreen = test.getScreenObject().getLoginScreen();
		landingScreen = test.getScreenObject().getLandingScreen();
		dashboardScreen = test.getScreenObject().getDashboardScreen();
	}

	@BeforeMethod
	public void handleTestMethodName(Method method) {
		Reporter.log("Starting test " + method.getName(), true);
	}

	@Test(description = "when user tap the Login button on the welcome screen then the login form "
			+ "is presented.")
	public void Test01_Navigate_To_LoginForm() {
		landingScreen.navigateToLoginScreen();
		assertTrue(loginScreen.IsUserPresentOnLoginScreen(), "User doesn't navigate to login screen");
		Reporter.log("User Navigated to Login Screen", true);

	}
	
	@Test (description = "when you enter a valid email address and a correct password then tapping on the login button\r\n" + 
			"logs you in",dependsOnMethods = "Test01_Navigate_To_LoginForm")
	
	public void Test02_Login_With_ValidUser() {
		loginScreen.userLogin(getYamlValue("LoginTest.ValidUser.username"),getYamlValue("LoginTest.ValidUser.password"));
		assertTrue(dashboardScreen.IsUserPresentOnDashboardScreen(), "User doesn't navigate to Dashboard screen");
		Reporter.log("User Navigated to Dashboard Screen", true);
	}
	
	

}
