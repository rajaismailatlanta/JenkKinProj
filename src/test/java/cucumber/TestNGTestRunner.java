package cucumber;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="C:/Users/Raja/Eclipse-Java-Projects/ExtentReport/src/test/java/cucumber", glue="stepDefinition", 
monochrome=true,  plugin = {"html:C:/Users/Raja/Eclipse-Java-Projects/ExtentReport/src/test/java/cucumber/report.html"})

public class TestNGTestRunner extends AbstractTestNGCucumberTests{


}