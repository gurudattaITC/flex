package com.stc.product;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;




import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.stc.core.TestManager;
import com.stc.util.PropertyUtil;

public class UpdateAccessRemovedOnProduct extends TestManager {
	private String className = this.getClass().getName();
	@SuppressWarnings("deprecation")
	@Test
	public void AccessRemovedOnProduct() throws Exception {
		String methodName = new Object() {}.getClass().getEnclosingMethod().getName();
		String testName = className.substring(className.lastIndexOf(".") + 1) + "-" + methodName;
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
			System.out.println("reached ");
			if(driver.findElements(By.xpath("//div[@id='fileDropZone']//table//tbody//tr//td[contains(text(),'Product Identification')]")).size()!=0){
				System.out.println("reached incheckside");
				assertTrue((2>3), "Update Product Access is not Removed.");
				System.out.println("reached inside");
				//assertEquals(false, true, "Update Product Access is Enabled.");
				//throw new Exception("Update Product Access is Enabled.");
			}
		 } catch (AssertionError e) {
				//logger.fail("Assertion Failed--" + e.getMessage());
				Assert.fail("Assertion Failed--" + e.getMessage());
			} catch (Exception e) {
				//logger.fail("Exception Occured--" + e.getMessage());
				Assert.fail("Exception Occured--" + e.getMessage());
			}
	}
}
