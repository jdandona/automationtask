package com.kaiahealth.automationtask.TestInitiator;

import org.openqa.selenium.WebDriver;
import static com.kaiahealth.automationtask.utilities.YamlReader.*;

public class MobileAppTestInitiator {

	protected WebDriver mobiledriver;
	private final DriverFactory driverFactory;
	private ScreenObjectProvider screenObjectProvider;

	public MobileAppTestInitiator(String Platform) {
		setYamlFilePath();
		driverFactory = new DriverFactory();
		_configureDevicePlatform(Platform);
		screenObjectProvider = new ScreenObjectProvider(mobiledriver);

	}

	private void _configureDevicePlatform(String Platform) {

		mobiledriver = driverFactory.mobilegetDriver(Platform);

	}

	public ScreenObjectProvider getScreenObject() {
		return screenObjectProvider;
	}

}
