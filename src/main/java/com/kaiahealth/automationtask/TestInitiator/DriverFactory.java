package com.kaiahealth.automationtask.TestInitiator;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class DriverFactory {
	
	private  WebDriver getdriver_Android() {
		try {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setCapability("automationName", "Appium");
			cap.setCapability(MobileCapabilityType.UDID, "RZ8N90FLW7L");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
			cap.setCapability("appPackage", "com.kaiahealth");
			cap.setCapability("appActivity", "com.kaiahealth.login");
			cap.setCapability("autoGrantPermissions", "true");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 420);
			cap.setCapability(MobileCapabilityType.NO_RESET, false);
			return new AndroidDriver(new URL("http://localhost:4723/wd/hub"), cap);

		} catch (MalformedURLException ex) {
			System.out.println("Invalid URL Given in Config Properties");
			return null;
		}

	}
	
	private WebDriver getdriver_IOS() {
		try {
			DesiredCapabilities cap = new DesiredCapabilities();

			cap.setCapability("automationName", "Appium");
			cap.setCapability(MobileCapabilityType.UDID, "RZ8N90FLW7L");
			cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
			cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.0");
			cap.setCapability("appPackage", "com.kaiahealth");
			cap.setCapability("appActivity", "com.kaiahealth.login");
			cap.setCapability("autoGrantPermissions", "true");
			cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 420);
			cap.setCapability(MobileCapabilityType.NO_RESET, false);
			return new IOSDriver(new URL("http://localhost:4723/wd/hub"), null);
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL Given in Config Properties");
			return null;
		}
	}

	public WebDriver mobilegetDriver(String Platform) {
			if (Platform.equalsIgnoreCase("Android")) {
				return getdriver_Android();
			}
		 else if (Platform.equalsIgnoreCase("IOS")) {
			return getdriver_IOS();
		}
		 else
			 return null; // Command doesn't match with the type of driver to invoke. Therefore, returning
						// null
	}

}
