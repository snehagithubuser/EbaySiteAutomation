package com.ebay.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPg {

	WebDriver driver;
	
	By DispTech = By.xpath(".//*[@id='AnswersModule']/div/div[2]/nav/ul/li[2]/a/div");
	By Formatbtn = By.xpath(".//*[@id='cbelm']/div[1]/div[2]/a[2]");
	
	public ProductPg(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public void clickDispTech(){
		driver.findElement(DispTech).click();
	}
	
	public void clickFormat()
	{
		driver.findElement(Formatbtn).click();
	}
	
}
