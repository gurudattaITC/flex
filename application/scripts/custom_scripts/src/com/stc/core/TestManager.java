package com.stc.core;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

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
import com.stc.util.Restcall1;

public class TestManager {
	
	protected static WebDriver driver;
	protected static List<TreeMap<String,String>> listOfFailures = null;
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

	/*@AfterMethod
	public void afterMethod(Method method) {
		MessageUtil.setMessage("End method : " + method.getName());
	}*/

	
	@AfterMethod
	public void AfterMehtod(ITestResult testResult){
		if (testResult.getMethod().isTest() && testResult.getStatus() == ITestResult.FAILURE) {
			TreeMap<String,String> failure = new TreeMap<String,String>();
			failure.put("1_Summary", "[UI - Issue] Failed Test -  " + testResult.getMethod().getMethodName());
			failure.put("2", "===============================================");
			failure.put("3_className", 		"Class  ---- "+testResult.getTestClass().getName());
			failure.put("4_methodName", 	"Method ---- "+testResult.getMethod().getMethodName());
			failure.put("5_ErrorMessage", 	"Error  ---- "+testResult.getThrowable().getMessage());
			failure.put("6", "===============================================");

			StackTraceElement[] stackTrace = testResult.getThrowable().getStackTrace();
			int i =0;
			for (StackTraceElement stackTraceElement : stackTrace) {
				failure.put("7_StackTraceElement_"+i, stackTraceElement.toString());
				i=i+1;
			}
			failure.put("8", "===============================================");
			listOfFailures.add(failure);
		}
	}	
	@BeforeClass
	public void beforeClass() {
		MessageUtil.setMessage("Start class : " + this.getClass());
		
	}

	/*@AfterClass
	public void afterClass() {
		MessageUtil.setMessage("End class : " + this.getClass());
		driver.quit();	
	}*/

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
		listOfFailures = new ArrayList<TreeMap<String,String>>();
	}

/*	@AfterSuite
	public void afterSuite(XmlTest xml) {
		MessageUtil.setMessage("End Suite : " + xml.getSuite().getName());
		driver.quit();		
	}*/
	
	@AfterSuite
	public void afterSuite(ITestContext conf) throws Exception {
		System.out.println("List of Failure" + listOfFailures.size());
	if(listOfFailures.size()>0){  
		
        for(int i = 0; i<listOfFailures.size();i++){
              //mapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);

              TreeMap<String, String> hashMap = listOfFailures.get(i);
              String summary = hashMap.get("1_Summary");
              String description = "   Issue Log    \n";
              
              Set<String> keys = hashMap.keySet();
              for (String key : keys) {
                     description = description + hashMap.get(key) + " \n ";
                     
              }
            
             // RestCall.jiraRestCall.createIssue(summary, description);
              //System.out.println("Invoked Jira create");
        }      
 }else{
 	System.out.println("No Issue Found");
      //  logger.info("No Issue Found");
 }
 
	//MessageUtil.setMessage("End Suite : " + xml.getSuite().getName());
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
