package com.bruno.webapp.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumWebDriverConfig {

	private WebDriver driver;
	
	public SeleniumWebDriverConfig() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(false);
        options.addArguments("start-maximized");
        driver = new ChromeDriver(options);
    }

    public void close() {
        driver.close();
    }

    public WebDriver getDriver() {
        return driver;
    }
}
