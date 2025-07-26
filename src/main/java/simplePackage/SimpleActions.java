
package simplePackage;
import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SimpleActions{
	
	WebDriver driver;
	public SimpleActions(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;
	
	@FindBy(css="h1[class*='hero-primary']")
	WebElement thanksMessage;
	
	@FindBy(css="i[class*='sign-out']")
	WebElement signOut;

	
	public void waitForElementsToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		//wait.until(null)
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementsToAppear(WebElement errorMsg) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		try {
			wait.until(ExpectedConditions.visibilityOf(errorMsg));
			}
		catch (NoSuchElementException e) {
		      System.out.println("Element not found: " + e.getMessage());
		    }
		
	}
	public void waitForElementToDisAppear(WebElement ele)throws InterruptedException {
		
		Thread.sleep(2000);
		//WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public void clickCart() {
		
		cartButton.click();
	}
	
	public String getMessage() {
		
		String message=thanksMessage.getText();
		return message;
		
	}
	
	public void signOut() {
		System.out.println("Signing out of the app.");
		signOut.click();
		
	}

}

