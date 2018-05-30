package com.stc.product;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.testng.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stc.core.TestManager;
import com.stc.util.PropertyUtil;

public class UpdateAccessRemovedOnSKU extends TestManager {
	private String className = this.getClass().getName();
	@Test
	public void create() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
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
		
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			
			//select colorway
			
			WebElement colorwayDropdown = driver.findElement(By.id("contextSKUId"));
			colorwayDropdown.click();
			List<WebElement> option = colorwayDropdown.findElements(By.tagName("option"));
			ArrayList<String> list = new ArrayList<>();
			
			for (int i = 0; i < option.size(); i++) {				
				list.add(option.get(i).getText().trim());
			}
			Collections.reverse(list);
			String lastOption = list.get(0);
			new Select(colorwayDropdown).selectByVisibleText(lastOption);
			
			Thread.sleep(2000);
			
			//check if Update access for Colorway is present
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement productSeasonActions = driver.findElements(By.id("prodseasonActions")).get(1);
			(new Select(productSeasonActions)).selectByVisibleText("Update Colorway");
	
			if(driver.findElements(By.xpath("//div[@id='fileDropZone']//table//tbody//tr//td[contains(text(),'ColorwayIdentification')]")).size()!=0){
				//assertTrue(false, "Update Colorway Access is not Removed.");
				assertEquals(false, true, "Update Colorway Access is not Removed.");
				//throw new Exception("Update Colorway Access is not Removed.");
			}
			
		
		 } catch (AssertionError e) {
			//logger.fail("Assertion Failed--" + e.getMessage());
			Assert.fail("Assertion Failed--" + e.getMessage());
		} catch (Exception e) {
			//logger.fail("Exception Occured--" + e.getMessage());
			Assert.fail("Exception Occured--" + e.getMessage());
		}
		//driver.quit();

		
	}
}
