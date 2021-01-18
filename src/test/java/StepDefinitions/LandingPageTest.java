package StepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import Framework.Connection;

import PageObjectRepository.HomePage;
import PageObjectRepository.WorkSpace;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LandingPageTest extends Connection {
	public static Logger log = LogManager.getLogger(LandingPageTest.class.getName());
	public WebDriver driver;
	public HomePage ho;
	public WorkSpace wo;

	@Given("User open the Browser")
	public void user_open_the_browser() throws IOException {
		driver = invokeBrowser();
		log.info("Bowser Initailaized:" + prop.getProperty("browser"));
	}

	@When("pass the link {string}")
	public void pass_the_link(String string) {
		driver.get(prop.getProperty("Url"));
		log.info("Url passsed in browser: " + prop.getProperty("Url"));
	}

	@Then("Home page is displayed")
	public void home_page_is_displayed() {
		log.info("Navigated to Login Page");
		ho = new HomePage(driver);
		Assert.assertTrue(ho.getForgotPassword().isDisplayed());
	}

	@Then("close browser")
	public void close_browser() {

		driver.quit();
	}

	@When("clicks loin button after enter the username {string} and password {string}")
	public void clicks_loin_button_after_enter_the_username_and_password(String string, String string2) {
		// Write code here that turns the phrase above into concrete actions
		ho = new HomePage(driver);
		ho.getLocation().click();
		Select dp = new Select(ho.getLocation());
		dp.selectByValue("4");
		ho.getUsername().sendKeys(string);
		ho.getPassword().sendKeys(string2);
		wo=ho.getSignIn();
		log.debug("Clicked on Login Button");
	}

	@Then("User should be able to login successful")
	public void user_should_be_able_to_login_successful() {
		// Write code here that turns the phrase above into concrete actions
		int count= wo.getTabList().size();
		if (count > 0) {
			Assert.assertTrue(true);
		} else
			Assert.assertTrue(false);
	}

}
