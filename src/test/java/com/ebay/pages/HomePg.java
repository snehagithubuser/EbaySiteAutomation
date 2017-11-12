/**
 * 
 */
package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @author Admin
 *
 */
public class HomePg {
	
WebDriver driver;
	
	By inputtab = By.xpath(".//*[@id='gh-ac']");
	By submitbtn = By.xpath(".//*[@id='gh-btn']");
	
	public HomePg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void enterProductName(String PRD) 
	{
		
		driver.findElement(inputtab).sendKeys(PRD);
		driver.findElement(submitbtn).click();
	}

}
