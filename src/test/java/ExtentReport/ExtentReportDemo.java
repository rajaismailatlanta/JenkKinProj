package ExtentReport;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentReportDemo extends ERBaseTest{//public static void main(String[] arg)throws InterruptedException, IOException{
	@Test(dataProvider="dataToBeUsed"/*, groups={"JsonUsedForSuccessValidatons"}*/)
	public  void successfulSubmitOrderValidation(HashMap<String, String> input)throws InterruptedException, IOException{
					
		ERLandingPage landingPage = new ERLandingPage(driver);
		ERProductCatalog products = new ERProductCatalog(driver);
		ERAbstractActions action = new ERAbstractActions(driver);
		ERCartElements cart = new ERCartElements(driver);
		ERPaymentElements payment = new ERPaymentElements(driver);
		EROrdersInfo orderedItems = new EROrdersInfo(driver);
			
		landingPage.goTo("https://www.rahulshettyacademy.com/client");
		landingPage.loginToApplication(input.get("email"), input.get("password"));
	
		products.getProductList();

		WebElement particularProduct=products.getParticularProduct(input.get("productName"));
		products.addProductToCart(particularProduct);
			
		action.clickCart();
			
		List<WebElement>cartProducts = cart.productsInCart();
			
		Boolean great = cartProducts.stream().anyMatch(existsInCart-> existsInCart.getText().contains(input.get("productName")));
		Assert.assertTrue(great);
			
		cart.checkOut();
			
			//In checkout page
			String countryOrderingFrom="Gibraltar";
			String searchChars="al";
			
			payment.searchCountries(searchChars);
						
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
				
			List<WebElement>countryList=payment.matchCountries();
													
			if(countryList != null) {
				System.out.println("Good");

			}
			else {
				System.out.println("Bad. No matching country!");
				
			}
			
			WebElement orderingCountry = countryList.stream().filter(a->a.getText().equalsIgnoreCase(countryOrderingFrom)).findFirst().orElse(null);
			
			if(orderingCountry!=null){orderingCountry.click();}
						
			payment.placeOrder();
			
			String thankUMessage=action.getMessage();
			String expectedMessage="THANKYOU FOR THE ORDER.";
			
			boolean result = thankUMessage.equalsIgnoreCase(expectedMessage);
			Assert.assertTrue(thankUMessage.equalsIgnoreCase("Thankyou for the order."));
		
			if(result){
				System.out.println("Everything went fine!");
				action.signOut();
				//driver.quit();
			}
												
	}
	
	
	@Test(/*dependsOnMethods={"successfulSubmitOrderValidation"},*/ dataProvider="dataToBeUsed", retryAnalyzer=Retry.class)
	public  void torderExistsValidation(HashMap<String, String> input)throws InterruptedException, IOException{

		ERLandingPage landingPage = new ERLandingPage(driver);
		ERAbstractActions action = new ERAbstractActions(driver);
		EROrdersInfo orderedItems = new EROrdersInfo(driver);
		
		landingPage.goTo("https://www.rahulshettyacademy.com/client");
		landingPage.loginToApplication(input.get("email"), input.get("password"));
	
		System.out.println("Before checking ordered item.");

		orderedItems.gotoOrderWithTryCatch();	
		orderedItems.getParticularOrderedItemName(input.get("verifyProductExists"));
		action.signOut();
			
		}
	}