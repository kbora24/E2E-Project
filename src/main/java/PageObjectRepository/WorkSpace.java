package PageObjectRepository;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class WorkSpace {

public WebDriver driver;

	
	@FindBy (xpath="//select[@id='locationRID']")
	private WebElement Location;
	
    @FindBy (css="div.menuNew.featureGroupNew.cb")
	private List<WebElement> TabList;
	
	
	public WorkSpace(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	public List<WebElement> getTabList() {
		return TabList;
	}
	
}
