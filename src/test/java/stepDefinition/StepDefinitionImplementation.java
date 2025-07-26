package stepDefinition;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import ExtentReport.ERBaseTest;
import ExtentReport.ERCartElements;
import ExtentReport.ERLandingPage;
import ExtentReport.ERProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import ExtentReport.ERAbstractActions;
import ExtentReport.ERPaymentElements;

public class StepDefinitionImplementation{

	public ERLandingPage landingPage;
	public ERProductCatalog productCatalog;
	public ERAbstractActions action;
	public ERCartElements cart;
	public ERPaymentElements payment;
	
	public ERBaseTest base = new ERBaseTest();
	public WebDriver driver;
	
	@Given("I landed on the Ecommerce Page")
	public void I_landed_on_the_Ecommerce_Page() throws IOException {
		driver=base.initializeDriver();
		landingPage = new ERLandingPage(driver);
		landingPage.goTo("https://www.rahulshettyacademy.com/client");
	}
	
	@Given("^I logged in with username (.+) and password (.+)$")
	public void I_logged_in_with_username_and_password(String userName, String password) {
		System.out.println(userName+ "  "+password);

		landingPage.loginToApplication(userName, password);
	}
	
	@Given("^I add product (.+) to the cart$")
	public void I_add_product_to_the_cart(String productName) throws InterruptedException, IOException {
	ERProductCatalog products = new ERProductCatalog(driver);
	List<WebElement>allProducts=products.getProductList();
	System.out.println(productName);
	WebElement particularProduct=products.getParticularProduct(productName);
	products.addProductToCart(particularProduct);
	
	}
	
	@And("^checkout the (.+) and submit the order$")
	public void checkout_the_product_and_submit_the_order(String productName) throws IOException {
		action = new ERAbstractActions(driver);
		action.clickCart();
		cart=new ERCartElements(driver);
		
		List<WebElement>cartProducts = cart.productsInCart();
		
		Boolean great = cartProducts.stream().anyMatch(existsInCart-> existsInCart.getText().contains(productName));
		Assert.assertTrue(great);
		
		cart.checkOut();
		String countryOrderingFrom="Gibraltar";
		String searchChars="al";
		payment = new ERPaymentElements(driver);
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
	}
	
	@Then("^(.+) appears in the confirmation page$")
	public void Thank_you_for_the_order_appears_in_the_confirmation_page(String thankYouMessage) {
			
		String thanksMessage=action.getMessage();
		
		boolean result = thanksMessage.equalsIgnoreCase(thankYouMessage);
		Assert.assertTrue(thanksMessage.equalsIgnoreCase("Thankyou for the order."));
	
		if(result){
			System.out.println("Everything went fine!");
			action.signOut();
			driver.quit();
		}
	
	}
	
	@Then("^(.+) error message is displayed$")
	public void Incorrect_email_or_password_error_message_is_displayed(String errorMsg) {
		
		System.out.println(errorMsg);
		String appErrorMsg = landingPage.checkIfLoginError();
		System.out.println(appErrorMsg);
		Assert.assertTrue(appErrorMsg.equalsIgnoreCase(errorMsg));
		driver.quit();
	
	}
	
}
