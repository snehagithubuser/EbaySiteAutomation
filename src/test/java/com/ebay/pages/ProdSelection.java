package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProdSelection {
	
	WebDriver driver;
	
	By ProductSel = By.xpath(".//*[@id='item4b2b37a589']/h3/a");
	//By ItemCond = By.xpath(".//*[@id='mainContent']/form/div[1]/div[1]/div[1]");
	
	
	public  ProdSelection(WebDriver driver)
	{
		this.driver=driver;
	}

	public void RandomClickPrd()
	{
		driver.findElement(ProductSel).click();
	}
	
	
}


