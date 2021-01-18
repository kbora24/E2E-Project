package testSuite;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import org.testng.annotations.Test;

import Framework.Connection;
import PageObjectRepository.HomePage;


public class LandingPageTest extends Connection {

	public static Logger log = LogManager.getLogger(LandingPageTest.class.getName());
	public WebDriver driver;
	public HomePage ho;
	
	@BeforeTest
	public void homePageNavigation() throws IOException {
	driver=invokeBrowser();
    log.info ("Bowser Initailaized:" +prop.getProperty("browser"));
	driver.get(prop.getProperty("Url"));
	log.info("Url passsed in browser: "+prop.getProperty("Url"));
	}

	@Test
    public void ForgotPasswordDisplay() {
    	log.info("Navigated to Login Page");
 	    ho=new HomePage(driver);
    	Assert.assertTrue(ho.getForgotPassword().isDisplayed());
       }
   
	@Test
	public void loginPage() {
		
		ho.getLocation().click();
		Select dp = new Select(ho.getLocation());
		dp.selectByValue("4");
		ho.getUsername().sendKeys("reg");
		ho.getPassword().sendKeys("admin");
		ho.getSignIn();
		log.debug("Clicked on Login Button");
	}
     
    @AfterTest
	public void closeDriver() {
	
		driver.close();
	}

}
