package com.bruno.webapp.pages;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.bruno.webapp.config.SeleniumWebDriverConfig;


public class SchedulingTest{
	
	private static final String BASE_URL = "http://localhost:4200/";
	private static final String CORRECT_CUSTOMER_NAME = "Bruno";
	private static final String INCORRECT_CUSTOMER_NAME = "Br";
	private static final String CORRECT_ADDRESS = "135 Montclair St";
	private static final String CORRECT_EMAIL = "bruno@gmail.com";
	private static final String INCORRECT_EMAIL = "brunogmail.com";
	private static final String CORRECT_PHONE = "973-123-4567";
	private static final String CORRECT_DATE = "09252022";
	private static final String CORRECT_DESCRIPTION = "Yard Work";


	private SeleniumWebDriverConfig seleniumConfig;
	private WebDriver driver;
	private WebDriverWait wait;

	@FindBy(id="job-form")
	private WebElement jobForm;

	@FindBy(id="customer")
	private WebElement customer;

	@FindBy(id="address")
	private WebElement address;

	@FindBy(id="email")
	private WebElement email;

	@FindBy(id="phone")
	private WebElement phone;
	
	@FindBy(id="date-time")
	private WebElement date;
	
	@FindBy(id="description")
	private WebElement description;
	
	@FindBy(id="submit-button")
	private WebElement submitButton;

	@FindBy(id="reset-button")
	private WebElement resetButton;
	
	//////////////////////////////////////////////////////////////
    
	@FindBy(id="job-list")
   	private WebElement list;

	@FindBy(id="job-id")
	private WebElement id;
	
    @FindBy(id="job-customer-name")
	private WebElement jobCustomerName;

	@FindBy(id="job-address")
	private WebElement jobAddress;

	@FindBy(id="job-email")
	private WebElement jobEmail;

	@FindBy(id="job-phone")
	private WebElement jobPhone;
	
	@FindBy(id="job-description")
	private WebElement jobDescription;

	@FindBy(id="edit-button")
	private WebElement editButton;

	@FindBy(id="delete-button")
	private WebElement deleteButton;
  
	@BeforeTest
	public void beforeTest() {
		driver.navigate().to(BASE_URL + "scheduling/");
		wait = new WebDriverWait(driver, 100);
		//clear();
	}

	@BeforeSuite
	public void beforeSuite() {
		seleniumConfig = new SeleniumWebDriverConfig();
		driver = seleniumConfig.getDriver();
		PageFactory.initElements(driver, this); 
	}

	@AfterSuite
	public void afterSuite() {
		driver.close();
		driver.quit();
	}

	/************************* Testing for Adding *************************/
	
	@Test
	public void givenSchedulingPage_whenAllInputsValid_thenAddJobToList() {
		clear();
		assertFalse(submitButton.isEnabled(), "Button should be disabled at this point");
		
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, CORRECT_PHONE, CORRECT_DATE, CORRECT_DESCRIPTION);
		
		assertTrue(submitButton.isEnabled(), "Button should be enabled at this point");
		submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("job-list")));
		
		assertTrue(list.isDisplayed(), "List of Jobs does not exist");
		
		assertEquals(jobCustomerName.getText(), "Bruno", "Job Customer Name is not correct");
		assertEquals(jobAddress.getText(), "135 Montclair St", "Job Address is not correct");
		assertEquals(jobEmail.getText(), "bruno@gmail.com", "Job Email Name is not correct");
		assertEquals(jobPhone.getText(), "973-123-4567", "Job Phone Name is not correct");
		assertEquals(jobDescription.getText(), "Yard Work", "Job Description Name is not correct");

		assertFalse(submitButton.isEnabled(), "Button should be disabled at this point");

		deleteButton.click();
		
	}
	/************************* Testing for Editing *************************/

	@Test
	public void givenSchedulingPage_whenJobExistsEditValues_thenJobIsUpdated() {
		clear();
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, CORRECT_PHONE, CORRECT_DATE, CORRECT_DESCRIPTION);
		submit();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("edit-button")));
		
		editButton.click();
		clear();
		fillOutForm(CORRECT_CUSTOMER_NAME + " Rebaza", CORRECT_ADDRESS + "reet", "rebaza." + CORRECT_EMAIL, "(973)123-4567", "09152022", CORRECT_DESCRIPTION + " and Pool Work");

		assertTrue(submitButton.isEnabled(), "Button should be enabled at this point");
		assertEquals(submitButton.getAttribute("value"), "Update", "Button should say 'Update'");
		submit();
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("job-customer-name")));
		assertEquals(jobCustomerName.getText(), "Bruno Rebaza", "Job has not been updated successfully");
		assertEquals(jobAddress.getText(), "135 Montclair Street", "Job has not been updated successfully");
		assertEquals(jobEmail.getText(), "rebaza.bruno@gmail.com", "Job has not been updated successfully");
		assertEquals(jobPhone.getText(), "(973)123-4567", "Job has not been updated successfully");
		assertEquals(jobDescription.getText(), "Yard Work and Pool Work", "Job has not been updated successfully");

		deleteButton.click();
	}
	
	/************************* Testing for Required Fields *************************/
	@Test
	public void givenSchedulingPage_whenInvalidCustomerName_thenButtonDisabled() {
		clear();
		fillOutForm(INCORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, CORRECT_PHONE, CORRECT_DATE, CORRECT_DESCRIPTION);
		assertEquals(customer.getAttribute("value"), "Br", "Field text is incorrect");

		assertFalse(submitButton.isEnabled(), "Button should not be enabled at this point");
	}
	
	@Test
	public void givenSchedulingPage_whenEmptyPhone_thenButtonDisabled() {
		clear();
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, "", CORRECT_DATE, CORRECT_DESCRIPTION);
		assertEquals(phone.getAttribute("value"), "", "Field should be empty");

		assertFalse(submitButton.isEnabled(), "Button should not be enabled at this point");
	}
	
	@Test
	public void givenSchedulingPage_whenEmptyDate_thenButtonDisabled() {
		clear();
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, CORRECT_PHONE, "", CORRECT_DESCRIPTION);
		assertEquals(date.getAttribute("value"), "", "Field should be empty");
		
		System.out.println("HERE "+ submitButton.getAttribute("disabled"));
		assertFalse(submitButton.isEnabled(), "Button should not be enabled at this point");
	}

	@Test
	public void givenSchedulingPage_whenEmptyDescription_thenButtonDisabled() {
		clear();
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, CORRECT_EMAIL, CORRECT_PHONE, CORRECT_DATE, "");
		assertEquals(description.getAttribute("value"), "", "Field should be empty");

		assertFalse(submitButton.isEnabled(), "Button should not be enabled at this point");
	}
	
	/*******************************************************************************/
	
	@Test
	public void givenSchedulingPage_whenInvalidEmail_thenInputValidator() {
		clear();
		assertTrue(driver.findElement(By.className("ng-valid")).isDisplayed(), "Email input should be valid at this point");
		fillOutForm(CORRECT_CUSTOMER_NAME, CORRECT_ADDRESS, INCORRECT_EMAIL, CORRECT_PHONE, CORRECT_DATE, CORRECT_DESCRIPTION);
		assertEquals(email.getAttribute("value"), "brunogmail.com", "Field text is incorrect");
		assertTrue(driver.findElement(By.className("ng-invalid")).isDisplayed(), "Email input should be invalid at this point");
		
		assertFalse(submitButton.isEnabled(), "Button should not be enabled at this point");
	}
	
	/*******************************************************************************/
	
	
	public void fillOutForm(String customerName, String address, String email, String phone, String date, String description) {
		if(jobForm.isDisplayed()) {
			if(customerName != null) {
				this.customer.sendKeys(customerName);
			}
			if(address != null) {
				this.address.sendKeys(address);
			}
			if(email != null) {
				this.email.sendKeys(email);
			}
			if(phone != null) {
				this.phone.sendKeys(phone);
			}
			if(date != null) {
				this.date.sendKeys(date);
			}
			if(description != null) {
				this.description.sendKeys(description);
			}
		}
	}
	
	public void submit() {
		submitButton.click();
	}
	
	public void clear() {
		customer.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
		address.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
		email.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
		phone.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
		date.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
		description.sendKeys(Keys.chord(Keys.LEFT_CONTROL,"a", Keys.DELETE));
	}
	
	
}
