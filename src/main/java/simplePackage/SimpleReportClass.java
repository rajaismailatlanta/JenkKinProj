package simplePackage;

import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class SimpleReportClass {
	public ExtentReports extentReporter;
	public ExtentSparkReporter reporter;
	public Properties properties = new Properties();
	//public FileInputStream propertyFile = new FileInputStream((System.getProperty("user.dir"))+"//src//test//java//AmeriQA//ExtentReport//globalData.properties");
	//properties.load(propertyFile);
	public WebDriver driver;
		
	@BeforeTest
	public void reportMethod(){
			reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//src//main//java//simplePackage//Report.html");
			reporter.config().setReportName("AmeriQA Autmation Report");
			reporter.config().setDocumentTitle("Test Results");
			
			extentReporter = new ExtentReports();
			extentReporter.attachReporter(reporter);
			extentReporter.setSystemInfo("Tester: ", "Raja Ismail");
			System.out.println("Inside reportMethod");
		}
	
	@Test
	public void reportDemo() throws InterruptedException{
		
			extentReporter.createTest("Test Report Demo");
			//WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.drver", "C:\\Users\\Raja\\eclipse\\chromedriver");
			ChromeOptions options = new ChromeOptions();

	      options.addArguments("ignore-certificate-errors");
			options.setAcceptInsecureCerts(true);
			//options.setBrowserVersion("138");
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
			SimpleLandingPage landingPage = new SimpleLandingPage(driver);
			landingPage.goTo("https://www.rahulshettyacademy.com/client");
			landingPage.loginToApplication("atlantaraja@gmail.com", "Allahu123");
				
			SimpleProductCatalog products = new SimpleProductCatalog(driver);
			products.getProductList();

			WebElement particularProduct=products.getParticularProduct("ZARA COAT 3");
			products.addProductToCart(particularProduct);
			
			SimpleActions action = new SimpleActions(driver);
			System.out.println("Everything went fine!");
			action.signOut();
			//driver.quit();
			extentReporter.flush();
			
			
			
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		System.out.println("Hi. This is a message to test github - jenkins webhook trigger");
		
		System.out.println("Closing the Webdriver.");
		System.out.println("=======================");
		driver.quit();
	
	}
	
}


