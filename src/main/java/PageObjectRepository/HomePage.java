package PageObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	public WebDriver driver;
	
	@FindBy (xpath="//select[@id='locationRID']")
	private WebElement Location;
	
	@FindBy (id="userName")
	private WebElement Username;
	
	@FindBy(id="password")
	private WebElement Password;
	
	@FindBy(id="login")
	private WebElement SignIn;
	
	@FindBy(xpath="//td[@class='label']/a")
	private WebElement ForgotPassword;
	
	public HomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public WebElement getLocation() {
		return Location;
	}
	public WebElement getUsername() {
		return Username;
	}
	public WebElement getPassword() {
		return Password;
	}
	public WorkSpace getSignIn() {
		SignIn.click();
		WorkSpace ws= new WorkSpace(driver);
		return ws;
	}
	public WebElement getForgotPassword() {
		return ForgotPassword;	
	}
	 
	 
	
}
