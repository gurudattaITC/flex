package com.stc.product;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import junit.framework.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stc.core.TestManager;
import com.stc.util.PropertyUtil;

public class UpdateAccessRemovedOnProduct extends TestManager {

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
			
			//check if Update access for Product is present
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			
			WebElement productSeasonActions = driver.findElements(By.id("prodseasonActions")).get(1);
			(new Select(productSeasonActions)).selectByVisibleText("Update Product");
	
			if(driver.findElements(By.xpath("//div[@id='fileDropZone']//table//tbody//tr//td[contains(text(),'Product Identification')]")).size()!=0){
				assertTrue(false, "Update Product Access is Enabled.");
				//throw new Exception("Update Product Access is Enabled.");
			}
			
		
		} catch(Exception e) {
			System.out.println(e);
			//throw e;
		}
		
		//driver.quit();

		
	}
}
