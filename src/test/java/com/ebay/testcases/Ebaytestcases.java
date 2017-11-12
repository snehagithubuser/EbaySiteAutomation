package com.ebay.testcases;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ebay.model.Product;
import com.ebay.pages.CartPg;
import com.ebay.pages.CheckOutPg;
import com.ebay.pages.GuestChkOutPg;
import com.ebay.pages.HomePg;
import com.ebay.pages.ProdSelection;
import com.ebay.pages.ProductPg;

import junit.framework.Assert;
import junit.framework.AssertionFailedError;
import library.Utility;


public class Ebaytestcases {
	Properties prop = loadProperties();
	public WebDriver driver;
	private static final String BY_ID = "BY_ID";
	private static final String BY_XPATH = "BY_XPATH"; 
	private static final String BY_CLASS = "BY_CLASS";
	private final String WEBSITE_URL = prop.getProperty("website");
	private final String SEARCH_KEYWORD = prop.getProperty("search");
	private Product purchasingProduct = new Product();

	@Test(priority = 1)
	public void launchBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\Admin\\Desktop\\Testing\\testing softwares\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@Test(priority = 2)
	public void launchHomepage() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(WEBSITE_URL);

	}

	@Test(priority = 3)
	public void EnterProductdetail() {

		HomePg Home = new HomePg(driver);
		Home.EnterProductName(SEARCH_KEYWORD);
	}

	@Test(priority = 4)
	public void ChecklistProd() {
		WebDriverWait myWaitVar = new WebDriverWait(driver, 15);
		myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("lvtitle")));
		List<WebElement> linkElements = driver.findElements(By.className("lvtitle"));
		String[] linkTexts = new String[linkElements.size()];
		System.out.println("-----List of Sony Tvs------");
		for (WebElement e : linkElements) {
			if (e.getText().toUpperCase().contains("TV") && e.getText().toUpperCase().contains("SONY")) {
				System.out.println(e.getText());
			} else
				Assert.fail("Wrong Tv Listed");
		}

	}

	@Test(priority = 5)
	public void DispTechno() {

		ProductPg Prod = new ProductPg(driver);
		Prod.clickDispTech();
		Utility.captureScreenshot(driver, "DisplaybyTechnology");
		Prod.clickFormat();

	}

	@Test(priority = 6)
	public void FilterProduct() {
		WebDriverWait myWaitVar = new WebDriverWait(driver, 5);
		myWaitVar.until(ExpectedConditions.visibilityOfElementLocated(By.className("vip")));
		List<WebElement> linkElements2 = driver.findElements(By.className("vip"));
		String[] linkTexts2 = new String[linkElements2.size()];
		System.out.println("----List of LED TVs------");
		for (WebElement e : linkElements2) {
			if (e.getText().toUpperCase().contains("LED"))
				System.out.println(e.getText());
		}

	}

	@Test(priority = 7)
	public void Verifyinfo() {
		/* random item selection */
		Random r = new java.util.Random();
		List<WebElement> linkElements3 = driver.findElements(By.className("vip"));
		WebElement randomElement = linkElements3.get(r.nextInt(linkElements3.size()));
		randomElement.click();

		/* itemCondition check */
		WebElement itemCond = checkWaitRetrieveElement("vi-itm-cond", BY_ID);
		if (itemCond != null) {
			String itemCondVal = itemCond.getText();
			purchasingProduct.setItemCondition(itemCondVal);
		} else{
			Assert.fail("Item condition could not be retrieved");
		}
			

		/* time left check */
		WebElement time = checkWaitRetrieveElement("vi-cdown_timeLeft", BY_ID);
		if (time != null) {
			String currTime = time.getText();
			String re1 = "(\\d+)"; // Integer Number 1
			String re2 = "((?:[dhm]))"; // Variable Names can only be d for day, h for hours, m for minutes
			String re3 = "(\\s+)"; // White Space 1
			String re4 = "(\\d+)"; // Integer Number 2
			String re5 = "((?:[hms]))"; // Variable Names can only be s for seconds, h for hours, m for minutes

			Pattern p = Pattern.compile(re1 + re2 + re3 + re4 + re5, Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
			Matcher m = p.matcher(currTime);

			if (m.find()) {
				assertTrue(m.find(), "Valid Time");
				System.out.println("Found a valid Time");
			} else {
				Assert.fail("Invalid time format : Given Time is - " + currTime);
			}
		}
	}

	@Test(priority = 8)
	public void captureProdDetails() {
		
		/* Extracting and setting Price */
		WebElement price = checkWaitRetrieveElement(".//*[@id='prcIsum']", BY_XPATH);
		if (price != null && price.getAttribute("content") != null) {
			System.out.println("price:" + price.getAttribute("content"));
			purchasingProduct.setPrice(price.getAttribute("content"));

		}
		/* Extracting and setting Seller */
		WebElement seller = checkWaitRetrieveElement(".//*[@id='mbgLink']/span", BY_XPATH);
		if (seller != null) {
			System.out.println("Seller:" + seller.getText());
			purchasingProduct.setSellerName(seller.getText());
		}
		/* Extracting and setting Product Name */
		WebElement productName = checkWaitRetrieveElement(".//*[@id='itemTitle']", BY_XPATH);
		if (productName != null) {
			System.out.println("productName:" + productName.getText());
			purchasingProduct.setName(productName.getText());
		}

	}

	@Test(priority = 9)
	public void addToCart() {
		CartPg Prod = new CartPg(driver);
		Prod.clickCartbtn();
		WebElement closeButton = checkWaitRetrieveElement("csclose", BY_ID);
		if (closeButton != null) {	
			closeButton.click();
			System.out.println("Item sucessfully addedd to cart and poppup closed");
		} else {
			System.out.println("Cart Popup didnt popup, item might or might not have been added");
		}
		
		//verifying that shopping cart page is shown
		WebElement shoppingCartPageTitle = checkWaitRetrieveElement(".//*[@id='PageTitle']/h1", BY_XPATH);
		if(shoppingCartPageTitle != null){
			System.out.println("Page Title : "+ shoppingCartPageTitle.getText());
			Assert.assertEquals("Your eBay Shopping Cart".toUpperCase(),shoppingCartPageTitle.getText().toUpperCase());
		} else {
			Assert.fail("Not on Shooping Cart Page");
		}
	}
	
	@Test(priority = 10)
	public void verifyCartProductInformation() {
		
		CheckOutPg ShopProd = new CheckOutPg(driver);
		ShopProd.clickCheckOutbtn();
		
		WebElement signinTitlePg = driver.findElement(By.xpath("/html/head/title"));
		if(signinTitlePg != null){
			System.out.println("Signin Page Title : "+ signinTitlePg.getText());
			Assert.assertEquals("Sign in, Register or Go to checkout".toUpperCase(),signinTitlePg.getText().toUpperCase());
		} else {
			Assert.fail("Not on Sign in  Page");
		}
		
	}

	
	@Test(priority = 11)
	public void proceedAsGuest()
	{
		GuestChkOutPg RevItem = new GuestChkOutPg(driver);
		RevItem.clickGuestChkOutbtn();
		
		WebElement guestChkoutTitle = checkWaitRetrieveElement("/html/body/h1", BY_XPATH);
		if(guestChkoutTitle != null){
			System.out.println("Guest Checkout Page Title : "+ guestChkoutTitle.getText());
			Assert.assertEquals("Guest checkout".toUpperCase(),guestChkoutTitle.getText().toUpperCase());
		} else {
			Assert.fail("Not on Guest Check out  Page");
		}
		
		
	}
	
	 @Test(priority =12)
	 public void prodInfoReview()
	 {
		//product name in Guest chkout page
		        WebElement CartProdName = checkWaitRetrieveElement(".//*[@id='s_0']/div/div[3]/div/div/div/div/div[2]/div[1]/div[1]",BY_XPATH);
				if(CartProdName != null){
					System.out.println("CartProductName : "+ CartProdName.getText());
					Assert.assertEquals(purchasingProduct.getName().toUpperCase(), CartProdName.getText().toUpperCase());
				} else {
					Assert.fail("Product did not match");
				}
				
		//Seller name in Guest chkout page
				WebElement cartSellerName = checkWaitRetrieveElement(".//*[@id='seller-info']/div[1]/span",BY_XPATH);
				if(cartSellerName != null){
					System.out.println("CartSellerName : "+ cartSellerName.getText());
					String sellerNameOnCOP = cartSellerName.getText().toUpperCase().split(":")[1].trim();
					Assert.assertEquals(purchasingProduct.getSellerName().toUpperCase(), sellerNameOnCOP);
				} else {
					Assert.fail("Seller did not match");
				}
				
		//Price in Guest Chkout page
				WebElement cartPrice = checkWaitRetrieveElement(".//*[@id='s_0']/div/div[3]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/span",BY_XPATH);
				if(cartPrice != null){
					System.out.println("CartPrice : "+ cartPrice.getText());
					Assert.assertEquals(purchasingProduct.getSellerName().toUpperCase(), cartPrice.getText().toUpperCase());
				} else {
					Assert.fail("Price did not match");
				}
						
				
		 //Order total in Guest Chkout page
				WebElement TotalPrice = checkWaitRetrieveElement(".//*[@id='s_0']/div/div[3]/div/div/div/div/div[2]/div[1]/div[2]/div[1]/span",BY_XPATH);
				if(TotalPrice != null){
					System.out.println("OrderTotal : "+ TotalPrice.getText());
					Assert.assertEquals(purchasingProduct.getSellerName().toUpperCase(), TotalPrice.getText().toUpperCase());
				} else {
					Assert.fail("Order total did not match");
				}
				
	}
	
	
	

	private WebElement checkWaitRetrieveElement(String elementPath, String searchUsing) {
		WebElement webElement = null;
		try {
			WebDriverWait myWaitVar2 = new WebDriverWait(driver, 5);
			if (searchUsing.equalsIgnoreCase(BY_ID)) {
				myWaitVar2.until(ExpectedConditions.visibilityOfElementLocated(By.id(elementPath)));
				webElement = driver.findElement(By.id(elementPath));
			} else if (searchUsing.equalsIgnoreCase(BY_XPATH)) {
				myWaitVar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
				webElement = driver.findElement(By.xpath(elementPath));
			} else if (searchUsing.equalsIgnoreCase(BY_CLASS)) {
				myWaitVar2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(elementPath)));
				webElement = driver.findElement(By.className(elementPath));
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Element Doesnt Exist");
		} catch (org.openqa.selenium.TimeoutException e) {
			System.out.println("Element Doesnt Exist, couldnt find element under the allocated time");
		}
		return webElement;

	}

	private Properties loadProperties(){
		Properties prop = new Properties();
		InputStream input = null;
		try {
			String filename = "config.properties";
			input = Ebaytestcases.class.getClassLoader().getResourceAsStream(filename);
			if(input==null){
				System.out.println("Sorry, unable to find " + filename);
				return null;
			}
			prop.load(input);
			return prop;
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
