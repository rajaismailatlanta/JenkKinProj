package ExtentReport;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ERPaymentElements extends ExtentReport.ERAbstractActions {
	
	By startLocationToSearchCoutnriesBy = By.cssSelector("span[class='ng-star-inserted']");

	WebDriver driver;
	public ERPaymentElements(WebDriver driver) {
		
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country'")
	WebElement searchCountry;
	
	@FindBy(css="a[class*='btnn action__submit']")
	WebElement placeOrder;
	
	@FindBy(css="span[class='ng-star-inserted']")
	List<WebElement> countries;
	
	public void searchCountries(String searchCriteria) {
		
		searchCountry.sendKeys(searchCriteria);
	}
	
	public void placeOrder() {
		
		placeOrder.click();
	}
	
	public List<WebElement> matchCountries(){
		waitForElementsToAppear(startLocationToSearchCoutnriesBy);
		return countries;
	}
		
}