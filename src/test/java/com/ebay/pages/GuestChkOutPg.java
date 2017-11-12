package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GuestChkOutPg {

WebDriver driver;
	
	By guestbtn = By.xpath(".//*[@id='gtChk']");
	
	public GuestChkOutPg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickGuestChkOutbtn()
	{
		WebElement guestButton = driver.findElement(guestbtn);
		if(guestButton != null){
			guestButton.click();
		}
	}
}
