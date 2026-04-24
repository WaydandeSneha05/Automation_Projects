package com.baseUtilites;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseUtility {

	public static void main(String[] args) {

		System.out.println("starts");

		System.out.println("ends");

	}

	public WebDriver startup(String bName, String url) {

		WebDriver driver = null; // declared here so we can use his obj in below conditions easily

		if (bName.equalsIgnoreCase("Chrome") || bName.equalsIgnoreCase("ch")) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();

		} else if (bName.equalsIgnoreCase("edge")) {

			System.setProperty("webdriver.edge.driver", "./drivers/msedgedriver.exe");
			driver = new EdgeDriver();

//	             driver.manage().window().maximize();
//	             driver.manage().window().minimize();

			driver.manage().window().setSize(new Dimension(400, 400)); //

		} else {
			System.out.println("Invalid System output");
		}

		driver.manage().window().maximize();

		// implicit wait:not-recommended beacuase slow down system process
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(25));

		driver.get(url);
		return driver;
	}

	// ---------------------------------------------------------------------------------------------------------

	public void waitForVisibilityOfElementLocatedByType(WebDriver driver, long time, String type, String expression) {

//		WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
//				(By.xpath("//button[@data-testid='popup_menu_button_profile']")));

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));

		if (type.equalsIgnoreCase("id")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id(expression)));

		} else if (type.equalsIgnoreCase("xpath")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(expression)));

		} else if (type.equalsIgnoreCase("css")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(expression)));
		} else if (type.equalsIgnoreCase("className")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className(expression)));
		} else if (type.equalsIgnoreCase("linkText")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.linkText(expression)));
		} else if (type.equalsIgnoreCase("partialLinkText")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.partialLinkText(expression)));
		} else if (type.equalsIgnoreCase("name")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name(expression)));
		} else if (type.equalsIgnoreCase("tagName")) {
			wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.tagName(expression)));
		} else {
			System.out.println("Invalid ");
		}

	}
	
	
	// ---------------------------------------------------------------------------------------------------------

	public void waitForVisibilityOfElementLocatedByType(WebDriver driver, long time, By obj) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(20));

//		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy
//				(By.xpath("//button[@data-testid='popup_menu_button_profile']")));

		wt.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(obj));
	}

	
	// Used for Login_page and Dashboard_page validation
	public void waitForTitleContains(WebDriver driver, long time, String partialTitle) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
		wt.until(ExpectedConditions.titleContains(partialTitle));

	}

	
	public void waitForUrlContains(WebDriver driver, long time, String partialUrl) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
		wt.until(ExpectedConditions.urlContains(partialUrl));

	}
	
	

//	-----------------------------------------------------------------------------------------------------------
	// Used for page scroll
	public void scrollByusingPageDown(WebDriver driver, int numberOfScrolls) {
		WebElement ele = driver.findElement(By.tagName("body"));
		for (int i = 0; i < numberOfScrolls; i++) {
			ele.sendKeys(Keys.PAGE_DOWN);
		}
	}

	public void scrollByusingPageUp(WebDriver driver, int numberOfScrolls) {
		WebElement ele = driver.findElement(By.tagName("body"));
		for (int i = 0; i < numberOfScrolls; i++) {
			ele.sendKeys(Keys.PAGE_UP);
		}
	}

	// ---------------------------------------------------------------------------------------------------------
	// Same scroll methods by using js
	public void scrollByJs(WebDriver driver, WebElement ele) {

		JavascriptExecutor js = (JavascriptExecutor) driver; // is an interface
		js.executeScript("argument[0].scrollIntoView[0]", ele);
	}

	public void clickByJs(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("argument[0].click()", ele);
	}

	// Generic method for wait which we apply on alert
	public boolean isAlertPresent(WebDriver driver, long time) {

//		WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(20));
//		wt.until(ExpectedConditions.alertIsPresent());

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));

		try {

			wt.until(ExpectedConditions.alertIsPresent());
			return true;

		} catch (Exception e) { // TimeoutException

			e.printStackTrace();
			return false;
		}

	}

	// Action class generic methods:

	// To scroll on a page
	public void scrollByActions(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.scrollToElement(ele).click();
	}

	// To click on any element
	public void clickByActions(WebDriver driver, WebElement ele) {

		Actions act = new Actions(driver);
		act.click().perform();
	}

	// To send data to input lables
	public void sendKeysByActions(WebDriver driver, WebElement ele, String text) {

		Actions act = new Actions(driver);
		act.sendKeys(ele, text).perform();

	}

	// to

	public ArrayList<String> getTextOfAllElementItems(List<WebElement> elements) {

		ArrayList<String> list = new ArrayList<>();

		for (WebElement ele : elements) {
			list.add(ele.getText().trim());
		}

		return list;
	}

	// ---------------------------------------------------------------------------------------------------------
	public void switchToFrame(WebDriver driver, long time, WebElement ele) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
		wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(ele));

	}
	

	// ---------------------------------------------------------------------------------------------------------
	public void switchToFrame2(WebDriver driver, long time, By obj) {

		WebDriverWait wt = new WebDriverWait(driver, Duration.ofSeconds(time));
		wt.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(obj));

	}

	public void waitForVisibilityOfElementLocatedByType(WebDriver driver, int time, String type, By xpath) {
		// TODO Auto-generated method stub
		
	}

}
