package ExtentReport;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EROrdersInfo extends ERBaseTest{
	
	WebDriver driver;
	By isCheckOutButtonThereBy = By.cssSelector("li[class='totalRow'] button[type='button']");
	
	public EROrdersInfo(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[routerlink*='myorders']")
	WebElement ordersButton;
	
	@FindBy(css="tr td:nth-child(3)")
	List <WebElement> orderedItemNames;
	/*
	public void gotoOrders(){
		
		ordersButton.click();
	}*/
	public void gotoOrderWithTryCatch(){
		ERAbstractActions action= new ERAbstractActions(driver);
		try {
		ordersButton.click();
		System.out.println("Orders button clicked, no issues.");
		 action.waitForElementsToAppear(isCheckOutButtonThereBy);
		 
		}catch(Exception e) {
			System.out.println("Inside catch. Order button issue noted.");
			//System.out.println(e.getMessage());
			if(e.getMessage().contains("is not clickable at point")){
				action.tab(driver, ordersButton);
			}
		}
	}
		
	public List<WebElement> getOrderedItems(){
		return orderedItemNames;
	}
	
	public void getParticularOrderedItemName(String orderedItemName) throws IOException{
		long matchItem=0;
				
		matchItem = orderedItemNames.stream().filter(itemNames-> itemNames.getText().equalsIgnoreCase(orderedItemName)).count();
		Assert.assertTrue(matchItem>0);
				
	}
	
	
}


