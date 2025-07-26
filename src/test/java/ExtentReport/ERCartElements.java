package ExtentReport;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ERCartElements {
	

	WebDriver driver;
	public ERCartElements(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> productsInCart;
	
	@FindBy(css=".totalRow button")
	WebElement checkOut;
	
	public List<WebElement> productsInCart() {
		
		return productsInCart;
		
	}
	
	public void checkOut() {
		
		checkOut.click();
	}
}
