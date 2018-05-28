package com.stc.product;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stc.core.TestManager;
import com.stc.util.PropertyUtil;

public class CreateSeasonAccessCheck extends TestManager {

	@Test
	public void create() throws Exception {
		
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("sidebarframe");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
			
			WebElement collapseExpandButton = driver.findElement(By.id("collapseExpandButton"));
			collapseExpandButton.click();
			
			// Navigate to Libraries
			WebElement libraries = driver.findElement(By.linkText("Libraries"));
			libraries.click();
			
			WebElement season = driver.findElement(By.linkText("Season"));
			season.click();
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			
			//check for New button
			if(driver.findElements( By.linkText("New") ).size() != 0){
				System.out.println("Create Access for Season is Enabled.");		
			}
			else{
				throw new Exception("Create Access for Season is not Enabled.");
			}				
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//driver.quit();

		
	}
}
