package Framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Connection {
	public WebDriver driver;
	public Properties prop;

	public WebDriver invokeBrowser() throws IOException {
		
		
				prop = new Properties();
		FileInputStream f = new FileInputStream(
				"C:\\Users\\thaku\\eclipse-workspace\\E2EProject\\Resourcess\\Data.poperties");
		prop.load(f);
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
		dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		
		//String browserName=System.getProperty("browser");
	  String browserName= prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\thaku\\Browserdriver\\chromedriver_win32\\chromedriver.exe");

			ChromeOptions co = new ChromeOptions();
			if(browserName.contains("headless")){
					co.addArguments("headless");
			}
			co.merge(dc);
			driver = new ChromeDriver(co);

		}
		if (browserName.equalsIgnoreCase("firefox")) {

			FirefoxOptions c = new FirefoxOptions();
			if(browserName.contains("headless")){
				c.addArguments("headless");
		      }
			c.merge(dc);
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\thaku\\Browserdriver\\geckodriver-v0.21.0-win64\\geckodriver.exe");
			driver = new FirefoxDriver(c);
		}
		if (browserName.equalsIgnoreCase("IE")) {

		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;

	}
}
