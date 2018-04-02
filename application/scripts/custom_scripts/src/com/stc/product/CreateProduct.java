package com.stc.product;

import java.security.SecureRandom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.stc.core.TestManager;

public class CreateProduct extends TestManager {

	@Test
	public void create() throws Exception {
		
		Thread.sleep(3000);
		
		// Switch to header frame to search for product
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("sidebarframe");
		
		Thread.sleep(1000);
		
		WebElement collapseExpandButton = driver.findElement(By.id("collapseExpandButton"));
		collapseExpandButton.click();
		
		Thread.sleep(1000);				
		// Set the product name into the search field and click
		WebElement mySeasons = driver.findElement(By.linkText("My Seasons"));
		mySeasons.click();
		
		Thread.sleep(1000);
		WebElement lineSheet = driver.findElement(By.linkText("Line Sheet"));
		lineSheet.click();
		
		
		Thread.sleep(4000);
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("contentframe");
		WebElement linePlanActions = driver.findElement(By.name("linePlanActions"));
		linePlanActions.click();
		(new Select(linePlanActions)).selectByVisibleText("Create New: Product");
	
		Thread.sleep(1000);
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
		
		Thread.sleep(3000);
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("contentframe");
		WebElement viewProduct = driver.findElement(By.linkText("View Product"));
		viewProduct.click();
		

		Thread.sleep(3000);
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("contentframe");
		WebElement productDetails = driver.findElement(By.linkText("Details"));
		productDetails.click();
		
		Thread.sleep(4000);
		WebElement productSeasonActions = driver.findElements(By.id("prodseasonActions")).get(1);
		(new Select(productSeasonActions)).selectByVisibleText("Update Product");
		
		driver.switchTo().defaultContent();		
		driver.switchTo().frame("contentframe");
		productSaveButton = driver.findElement(By.id("saveButton"));
		productSaveButton.click();
		
		//Thread.sleep(3000);
		//product_Accessories.click();
		
	}
}
