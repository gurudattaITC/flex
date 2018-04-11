package com.stc.product;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.stc.core.TestManager;

public class CreateProduct extends TestManager {

	@Test
	public void create() throws Exception {
		
		//Thread.sleep(3000);
		
		// Switch to header frame to search for product
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("sidebarframe");
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		try {
		
			WebElement collapseExpandButton = driver.findElement(By.id("collapseExpandButton"));
			collapseExpandButton.click();
			
			// Set the product name into the search field and click
			WebElement mySeasons = driver.findElement(By.linkText("My Seasons"));
			mySeasons.click();
			
			WebElement lineSheet = driver.findElement(By.linkText("Line Sheet"));
			lineSheet.click();
			
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement linePlanActions = driver.findElement(By.name("linePlanActions"));
			linePlanActions.click();
			(new Select(linePlanActions)).selectByVisibleText("Create New: Product");
		
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement product_Accessories = driver.findElement(By.linkText("Accessories"));
			System.out.println("product_Accessories : "  + product_Accessories.getAttribute("id"));
			product_Accessories.click();
			
			//WebElement productNameLabel = driver.findElement(By.xpath("//*[contains(text(), '*Product Name')]"));
			//productNameLabel.
			
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement productName = driver.findElement(By.id("ptc_str_1"));
			SecureRandom random = new SecureRandom();
			int num = random.nextInt(100000);
			String formatted = String.format("%05d", num); 
			System.out.println(formatted);
			productName.sendKeys(formatted);
			
			WebElement productSaveButton = driver.findElement(By.id("saveButton"));
			productSaveButton.click();
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement viewProduct = driver.findElement(By.linkText("View Product"));
			viewProduct.click();
			
	
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			WebElement productDetails = driver.findElement(By.linkText("Details"));
			productDetails.click();
			
			//WebDriverWait wait=new WebDriverWait(driver, 30);
			while(driver.findElements(By.id("prodseasonActions")).size()==1) {
				
			}
			
			WebElement productSeasonActions = driver.findElements(By.id("prodseasonActions")).get(1);
			(new Select(productSeasonActions)).selectByVisibleText("Update Product");
			
			driver.switchTo().defaultContent();		
			driver.switchTo().frame("contentframe");
			productSaveButton = driver.findElement(By.id("saveButton"));
			productSaveButton.click();
		
		} catch(Exception e) {
			System.out.println(e);
		}
		
		driver.quit();

		
	}
}
