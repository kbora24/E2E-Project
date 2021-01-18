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
import PageObjectRepository.WorkSpace;


public class OPDRegTest extends Connection {
	public WebDriver driver;
	public Logger log = LogManager.getLogger(OPDRegTest.class.getName());

	@BeforeTest
	public void InvokeBrowser() throws IOException {

		driver = invokeBrowser();
		log.info("Bowser Initailaized:" + prop.getProperty("browser"));
		driver.get(prop.getProperty("Url"));
		log.info("Url passsed in browser: " + prop.getProperty("Url"));

	}

	@Test
	public void OutpatientVisitsLinkDisplayed() {
		HomePage h = new HomePage(driver);
		h.getLocation().click();
		Select dp = new Select(h.getLocation());
		dp.selectByValue("4");
		h.getUsername().sendKeys("reg");
		h.getPassword().sendKeys("admin");
		WorkSpace ws = h.getSignIn();
		int count = ws.getTabList().size();
		String Status ="Fail";

		for (int i = 0; i < count; i++) {
			String onClickValue = ws.getTabList().get(i).getAttribute("onclick");
			if (onClickValue.contains("Reports")) {
				Status="Pass";
				log.info("Outpatient Visits Link is Displayed");
				ws.getTabList().get(i).click();
				break;
			}

		}
		if(Status.equalsIgnoreCase("pass")) {
			Assert.assertTrue(true);
		}
		else Assert.assertTrue(false);
	}

	@AfterTest
	public void closeDriver() {

		driver.close();
	}

}
