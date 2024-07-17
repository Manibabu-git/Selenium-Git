package manibabu.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporter {
	
	public static ExtentReports getExtentReport()
	{
		String path= System.getProperty("user.dir")+"//Reports//index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setDocumentTitle("Automation selenium test results");
		reporter.config().setReportName("Test Result");
		ExtentReports extent=  new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("tester", "Manibabu");
		return extent;
	}
	

}
