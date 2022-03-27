package com.kaiahealth.automationtask.ScreenDSL;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kaiahealth.automationtask.utilities.DynamicWait;

import static com.kaiahealth.automationtask.utilities.ObjectFileReader.getELementFromFile;

public class ScreenHelper {

	protected WebDriver webdriver;
	String pageName;
	protected DynamicWait wait;

	public ScreenHelper(WebDriver driver, String pageName) {

		this.webdriver = driver;
		this.pageName = pageName;
		this.wait = new DynamicWait(driver, 60);
	}

	protected WebElement element(String elementToken) {
		return element(elementToken, "");
	}

	protected WebElement element(String elementToken, String replacement) throws NoSuchElementException {
		WebElement elem = null;
		try {
			wait.visibilityOfElementLocated(getLocator(elementToken, replacement));
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));

		} catch (NoSuchElementException excp) {

		} catch (StaleElementReferenceException excp) {
			elem = wait.waitForElementToBeVisible(webdriver.findElement(getLocator(elementToken, replacement)));
		}

		return elem;
	}

	protected List<WebElement> elements(String elementToken, String replacement) {
		return wait.waitForElementsToBeVisible(webdriver.findElements(getLocator(elementToken, replacement)));
	}


	protected List<WebElement> elements(String elementToken) {
		return elements(elementToken, "");
	}

	protected void click(WebElement element) {
		try {
			wait.waitForElementToBeVisible(element);

			wait.waitForElementToBeClickable(element);
			element.click();
		} catch (Exception ex2) {
			System.out.println("Element " + element + " could not be clicked! " + ex2.getMessage());
		}
	}

	protected void SendText(WebElement element, String keysToSend) {
		try {
			element.click();
			element.clear();
			element.sendKeys(keysToSend);
		} catch (Exception e) {
			if (element.getText() != null) {
				element.clear();
			}
			element.sendKeys(keysToSend);
		}

	}

	protected boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public String getTextFromElement(String elementToken) {
		return element(elementToken).getText().trim();
	}

	protected By getLocator(String elementToken) {
		return getLocator(elementToken, "");
	}

	protected By getLocator(String elementToken, String replacement) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceAll("\\$\\{.+\\}", replacement);
		return getBy(locator[1].trim(), locator[2].trim());

	}

	protected By getLocator(String elementToken, String replacement1, String replacement2) {
		String[] locator = getELementFromFile(this.pageName, elementToken);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement1);
		locator[2] = locator[2].replaceFirst("\\$\\{.+?\\}", replacement2);
		return getBy(locator[1].trim(), locator[2].trim());
	}

	private By getBy(String locatorType, String locatorValue) {
		switch (Locators.valueOf(locatorType)) {
		case id:
			return By.id(locatorValue);
		case xpath:
			return By.xpath(locatorValue);
		case css:
			return By.cssSelector(locatorValue);
		case name:
			return By.name(locatorValue);
		case classname:
			return By.className(locatorValue);
		case linktext:
			return By.linkText(locatorValue);
		default:
			return By.id(locatorValue);
		}
	}

}
