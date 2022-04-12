package com.bruno.webapp.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.bruno.webapp.config.SeleniumWebDriverConfig;

public class LoginTest {
	
	private static final String BASE_URL = "http://localhost:8080/ScheduleNow/";
	private static final String CORRECT_USER = "Bruno";
	private static final String CORRECT_PASS = "pass1234";
	private static final String INCORRECT_USER = "Br";
	private static final String INCORRECT_PASS = "pass";

	private SeleniumWebDriverConfig seleniumConfig;
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id="username")
	private WebElement username;
	
	@FindBy(id="password")
	private WebElement password;

	@FindBy(id="login-button")
	private WebElement loginButton;

	@FindBy(id="error-message")
	private WebElement errorMessage;

	@FindBy(id="welcome-message")
	private WebElement welcomeMsg;
	
	@FindBy(id="welcome-div")
	private WebElement welcomeDiv;
	
	@BeforeSuite
	public void beforeSuite() {
		seleniumConfig = new SeleniumWebDriverConfig();
		driver = seleniumConfig.getDriver();
		wait = new WebDriverWait(driver, Duration.ofSeconds(100));
		PageFactory.initElements(driver, this);
	}
	
	@AfterSuite
	public void afterSuite() {
		driver.close();
		driver.quit();
	}
	
	@BeforeMethod
	public void beforeTest() {
		driver.navigate().to(BASE_URL);
		assertEquals(driver.getCurrentUrl(), BASE_URL, "Actual url is not the same as expected");
		assertFalse(errorMessage.isDisplayed(), "Error Message should not be displayed");

	}
	
	
	
	@Test
	public void givenLoginForm_whenValidUserInvalidPass_thenErrorOnLoginPage() {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));

		fillOutLogin(CORRECT_USER, INCORRECT_PASS);
		
		assertEquals(driver.getCurrentUrl(), BASE_URL, "Actual url is not the same as expected");
		assertTrue(errorMessage.isDisplayed(), "Error Message is not being displayed");
		
		String actualMsg = errorMessage.getText();
		String expectedMsg = "Username or password is not long enough";
		
		assertEquals(actualMsg, expectedMsg, "Actual error message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginForm_whenInvalidUserValidPass_thenErrorOnLoginPage() {
		fillOutLogin(INCORRECT_USER, CORRECT_PASS);
		
		assertEquals(driver.getCurrentUrl(), BASE_URL, "Actual url is not the same as expected");
		assertTrue(errorMessage.isDisplayed(), "Error Message is not being displayed");
		
		String actualMsg = errorMessage.getText();
		String expectedMsg = "Username or password is not long enough";
		
		assertEquals(actualMsg, expectedMsg, "Actual error message is not the same as the expected message");
	}
	
	@Test
	public void givenLoginForm_whenInvalidUserInvalidPass_thenErrorOnLoginPage() {
		fillOutLogin(INCORRECT_USER, INCORRECT_PASS);
		
		assertEquals(driver.getCurrentUrl(), BASE_URL, "Actual url is not the same as expected");
		assertTrue(errorMessage.isDisplayed(), "Error Message is not being displayed");
		
		String actualMsg = errorMessage.getText();
		String expectedMsg = "Username or password is not long enough";
		
		assertEquals(actualMsg, expectedMsg, "Actual error message is not the same as the expected message");
	}

	@Test
	public void givenLoginForm_whenUserAndPass_thenGoToSchedulingPage() {
		fillOutLogin(CORRECT_USER, CORRECT_PASS);
		
		assertEquals(driver.getCurrentUrl(), BASE_URL + "scheduling/", "Actual url is not the same as expected for correct input");
		assertTrue(welcomeDiv.isDisplayed(), "");
		
		String actualMsg = welcomeMsg.getText();
		String expectedMsg = "Welcome " + CORRECT_USER + ", Work Scheduling Form";
		
		assertEquals(actualMsg, expectedMsg);
	}
	
	private void fillOutLogin(String username, String password) {
		if (username != null) {
			this.username.sendKeys(username);
		}
		if (password != null) {
			this.password.sendKeys(password);
		}

		loginButton.click();
	}

}
