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

public class CreateColorwayAccessCheck extends TestManager {

	@Test
	public void create() throws Exception {
		
		//Thread.sleep(3000);
		
		 String PRODUCT_NO = "11701";
		
		//maximize the window
		driver.manage().window().maximize();
		
		
		
		
		try {
		
			// Switch to header frame to search for product
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("headerframe");
			
			WebElement searchBox = driver.findElement(By.id("searchField"));
			searchBox.clear();
			searchBox.sendKeys(PRODUCT_NO);
			driver.findElement(By.id("searchButton")).click();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Navigate to Details tab
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement productDetails = driver.findElement(By.linkText("Details"));
			productDetails.click();
			
			//check if Create access for Colorway is present
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			
			WebElement productSeasonActions = driver.findElements(By.id("prodseasonActions")).get(1);
			(new Select(productSeasonActions)).selectByVisibleText("Create Colorway");
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			if(driver.findElement(By.xpath("//div[@id='contentDiv']//table//tbody//tr//td[@class='PAGEHEADINGTITLE']")).isDisplayed()){
				System.out.println("Colorway Access is present");
			}else{
				throw new Exception("Colorway Access is not present");
			}
			
		
		} catch(Exception e) {
			System.out.println(e);
		}
		
		//driver.quit();

		
	}
}
