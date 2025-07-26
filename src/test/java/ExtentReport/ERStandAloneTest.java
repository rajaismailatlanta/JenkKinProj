package ExtentReport;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ExtentReport.ERAbstractActions;
import ExtentReport.ERCartElements;
import ExtentReport.ERLandingPage;
import ExtentReport.ERProductCatalog;
import ExtentReport.ERPaymentElements;
import ExtentReport.ERBaseTest;

import org.testng.annotations.DataProvider;


public class ERStandAloneTest extends ERBaseTest {

	//public static void main(String[] arg)throws InterruptedException, IOException{
		@Test(dataProvider="dataToBeUsed")
		public  void standAloneTest(HashMap<String, String> input)throws InterruptedException, IOException{

				//BaseTest basics = new BaseTest();
				//WebDriver driver = basics.initializeDriver();
				
				ERLandingPage landingPage = new ERLandingPage(driver);
				ERProductCatalog products = new ERProductCatalog(driver);
				ERAbstractActions action = new ERAbstractActions(driver);
				ERCartElements cart = new ERCartElements(driver);
				ERPaymentElements payment = new ERPaymentElements(driver); 
				
				landingPage.goTo("https://www.rahulshettyacademy.com/client");
				landingPage.loginToApplication(input.get("email"), input.get("password"));
				//if(landingPage.loginToApplication(input.get("email"), input.get("password"))!=false) {
					
				products.getProductList();

				WebElement particularProduct=products.getParticularProduct(input.get("productName"));
				products.addProductToCart(particularProduct);
				
				action.clickCart();
				
				List<WebElement>cartProducts = cart.productsInCart();
				
				Boolean great = cartProducts.stream().anyMatch(existsInCart-> existsInCart.getText().contains(input.get("productName")));
				Assert.assertTrue(great);
				
				cart.checkOut();
				
				// In checkout page
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
					System.out.println("Bad");
					driver.close();

				}
				
				WebElement orderingCountry = countryList.stream().filter(a->a.getText().equalsIgnoreCase(countryOrderingFrom)).findFirst().orElse(null);
				
				if(orderingCountry!=null){orderingCountry.click();}
							
				payment.placeOrder();
				
				String thankUMessage=action.getMessage();
				String expectedMessage="THANKYOU FOR THE ORDER.";
				
				boolean result = thankUMessage.equalsIgnoreCase(expectedMessage);
				
				if(result){
					System.out.println("This is StandAlone submit order.");
					System.out.println("Everything went fine!");
					action.signOut();
					//driver.quit();
				}
								
				//AssertJUnit.assertTrue(thankUMessage.equalsIgnoreCase("Thankyou for the order."));
					
				}
				
				
		}
	