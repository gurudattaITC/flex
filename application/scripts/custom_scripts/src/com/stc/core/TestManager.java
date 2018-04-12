package com.stc.core;

import java.lang.reflect.Method;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.IClass;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.xml.XmlTest;

import com.stc.util.MessageUtil;
import com.stc.util.PropertyUtil;

public class TestManager {
	
	protected static WebDriver driver;
	
	static {
		
		try {
			
			// Initialize properites
			PropertyUtil.getInstance();
			
			// Start Message
			MessageUtil.setMessage("\n\n###################################################\nExecution Starts\n###################################################\n\n");
			
			// Set the driver executable path
			System.setProperty("webdriver.chrome.driver", PropertyUtil.getProperty("DRIVE_EXECUTABLE_PATH"));
			
			// Instantiate driver
			driver = new ChromeDriver();
			
			// Setup the URL
			driver.get(PropertyUtil.getProperty("MAIN_URL"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		MessageUtil.setMessage("Start method : " + method.getName());
	}

	@AfterMethod
	public void afterMethod(Method method) {
		MessageUtil.setMessage("End method : " + method.getName());
	}

	@BeforeClass
	public void beforeClass() {
		MessageUtil.setMessage("Start class : " + this.getClass());
		
	}

	@AfterClass
	public void afterClass() {
		MessageUtil.setMessage("End class : " + this.getClass());
	}

	@BeforeTest
	public void beforeTest(ITestContext testContext) {
		MessageUtil.setMessage("Start Test : " + testContext.getName());
		
		
		// Switch to header frame to search for product
		driver.switchTo().defaultContent();
		
	}

	@AfterTest
	public void afterTest(XmlTest xml) {
		MessageUtil.setMessage("End Test : " + xml.getName());
		refresh();
	}

	@BeforeSuite
	public void beforeSuite(final ITestContext testContext) {
		MessageUtil.setMessage("Start Suite : " + testContext.getSuite().getName());
		
	}

	@AfterSuite
	public void afterSuite(XmlTest xml) {
		MessageUtil.setMessage("End Suite : " + xml.getSuite().getName());
		driver.quit();		
	}
	
	public void init(String title) {
		
	}

	public void refresh() {
		driver.switchTo().window(driver.getWindowHandles().iterator().next());
		driver.switchTo().defaultContent();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
}
