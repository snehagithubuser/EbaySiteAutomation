package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GuestChkOutPg {

WebDriver driver;
	
	By Guestbtn = By.xpath(".//*[@id='gtChk']");
	
	public GuestChkOutPg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickGuestChkOutbtn()
	{
		driver.findElement(Guestbtn).click();
	}
}
