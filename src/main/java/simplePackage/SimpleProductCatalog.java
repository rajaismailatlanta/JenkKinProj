package simplePackage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SimpleProductCatalog extends SimpleActions{

	WebDriver driver;
	WebElement particularProduct;
	
	By productsBy = By.cssSelector(".card-body");
	By addToCartBy = By.cssSelector(".card-body button:last-of-type");
	
	public SimpleProductCatalog(WebDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
		
	@FindBy(css=".card-body")
	List <WebElement> products;
		
	@FindBy(css=".ng-animating")
	WebElement spinner;
	
	public List<WebElement> getProductList(){
		waitForElementsToAppear(productsBy);
		return products;
	}
	
	public WebElement getParticularProduct(String productName){
		
				
		WebElement aProduct= getProductList().stream().filter(product -> product.findElement(By.cssSelector("b")).getText().contains(productName)).findFirst().orElse(null);
		return aProduct;
	}
	
	
	public void addProductToCart(WebElement particularProduct) throws InterruptedException{
		
		particularProduct.findElement(addToCartBy).click();
		waitForElementToDisAppear(spinner);
		
	}
	
}
