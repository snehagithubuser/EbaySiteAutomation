package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPg {

	WebDriver driver;
	
	By cartBtn = By.xpath(".//*[@id='isCartBtn_btn']");
	
	public CartPg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickCartbtn()
	{
		driver.findElement(cartBtn).click();
	}
	
}
