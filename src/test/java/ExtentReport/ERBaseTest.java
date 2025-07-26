package ExtentReport;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterMethod;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
//import io.github.bonigarcia.wdm.WebDriverManager;


public class ERBaseTest {
	public WebDriver driver;
	
	
	@BeforeMethod(alwaysRun=true)
	public WebDriver initializeDriver() throws IOException {
		//public WebDriver ERBaseTest() throws IOException {
		Properties properties = new Properties();
		FileInputStream propertyFile = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//ExtentReport//globalData.properties");
		properties.load(propertyFile);
		
		String browserName = System.getProperty("browser")!= null ? System.getProperty("browser"): properties.getProperty("browser");
		System.out.println("Initializing Web Driver");
		if(browserName.contains("Chrome")) {
			//WebDriverManager.chromedriver().setup();
			//System.setProperty("webdriver.chrome.drver", "C:\\Users\\Raja\\eclipse\\chromedriver");
			ChromeOptions options = new ChromeOptions();

	        options.addArguments("ignore-certificate-errors");
			options.setAcceptInsecureCerts(true);
			//options.setBrowserVersion("138");
			if(browserName.contains("headless")){
			options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			
		}
		else if(browserName.equalsIgnoreCase("firefox")){
		System.setProperty("webdriver.gecko.driver","C://Users//Raja//eclipse//geckodriver.exe");
		driver= new FirefoxDriver();
			
		}
		else if (browserName.equalsIgnoreCase("Edge")){
			
			System.setProperty("webdriver.chrome.drver", "C:\\Users\\Raja\\eclipse\\edgeDriver");
			WebDriver driver = new EdgeDriver();
		}
		
	
	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().setSize(new Dimension(1920,1080));
		//driver.manage().window().maximize();
		
		
		return driver;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		
		System.out.println("Closing the Webdriver.");
		System.out.println("=======================");
		driver.quit();
	
	}
	
	public List<HashMap<String, String>> dataReaderFromJson(String filePath) throws IOException {
			
			File file = new File(filePath);
			
			//String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"/src//main//java//DataPackage//data.json"));
			String jsonContent=FileUtils.readFileToString(file, StandardCharsets.UTF_8);
			
			ObjectMapper mapper = new ObjectMapper();
			
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			return data;
			
			}
			
	/*
	@BeforeTest(groups={"JsonUsedForSuccessValidatons"})
	public void reportConfig() {	
		reporter = new ExtentSparkReporter(System.getProperty("user.dir")+"//src//test//java//AmeriQA//ExtentReport//Report.html");
		reporter.config().setReportName("AmeriQA Autmation Report");
		reporter.config().setDocumentTitle("Test Results");
		
		extentReporter = new ExtentReports();
		extentReporter.attachReporter(reporter);
		extentReporter.setSystemInfo("Tester: ", "Raja Ismail");
	}*/
	
	@DataProvider
	public Object[][] dataToBeUsed() throws IOException{
		
		/*
		 * HashMap<String, String> map1 = new HashMap<String, String>();
		 * map1.put("email", "atlantaraja@gmail.com"); map1.put("password",
		 * "Allahu123"); map1.put("productName","ZARA COAT 3");
		 * 
		 * HashMap<String, String> map2 = new HashMap<String, String>();
		 * map2.put("email", "atlantaraja2@gmail.com"); map2.put("password",
		 * "Allahu123"); map2.put("productName","ADIDAS ORIGINAL");
		 */
		List<HashMap<String, String>> data= dataReaderFromJson(System.getProperty("user.dir")+"//src//test//java///ExtentReport//data.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	
	}
	public String screenShot(String testCaseName, WebDriver driver) throws IOException{
		
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//src//test//java//ExtentReport//"+ testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//test-output//"+ testCaseName+".png";
		
	}

}
