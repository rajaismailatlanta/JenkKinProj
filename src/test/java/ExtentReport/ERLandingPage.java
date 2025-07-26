package ExtentReport;

import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ERLandingPage extends ERAbstractActions{
	
	WebDriver driver;
	public String message=null;
	public ERLandingPage(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	
	@FindBy(id="userEmail")
	WebElement LoginId;
	
	@FindBy(id="userPassword")
	WebElement Passwd;
	
	@FindBy(id="login")
	WebElement Login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement loginErrorMessage;
	
	public void loginToApplication(String email, String pwd){
		
				LoginId.sendKeys(email);
				Passwd.sendKeys(pwd);
				Login.click();
				
			/*
			 * 	checkIfLoginError();
				if(message.equalsIgnoreCase("Incorrect email or password.")) {
						System.out.println("Oh oh! Login Error");
						return false;
					}
				else
					return true;
							*/
	}
	
	public void goTo(String url) {
		driver.get(url);

	}
	/*
	public String checkIfLoginError(){
				
		waitForWebElementsToAppear(loginErrorMessage);
				
		if(loginErrorMessage!=null) {
		 message = loginErrorMessage.getText();
		}
		return message;
		
	}*/
	public String checkIfLoginError(){
		
		String message=null;
		waitForWebElementsToAppear(loginErrorMessage);
				
		if(loginErrorMessage!=null) {
		 message = loginErrorMessage.getText();
		}
		return message;

}
}
