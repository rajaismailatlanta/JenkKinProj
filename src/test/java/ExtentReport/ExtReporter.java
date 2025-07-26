package ExtentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public  class ExtReporter {
	
	public static ExtentReports reportReturn() {
		String path=System.getProperty("user.dir")+"//src//test//java//ExtentReport//Report.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("AmeriQA Autmation Report");
		reporter.config().setDocumentTitle("Test Results");
		
		ExtentReports extentReporter = new ExtentReports();
		extentReporter.attachReporter(reporter);
		extentReporter.setSystemInfo("Tester: ", "Raja Ismail");
		return extentReporter;
	} 
}
