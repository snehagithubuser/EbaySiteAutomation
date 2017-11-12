package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckOutPg {

	WebDriver driver;
	
	By CheckOutbtn = By.xpath(".//*[@id='ptcBtnBottom']");
	
	public CheckOutPg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickCheckOutbtn()
	{
		driver.findElement(CheckOutbtn).click();
	}
}
