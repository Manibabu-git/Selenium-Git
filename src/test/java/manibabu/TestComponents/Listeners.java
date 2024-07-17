package manibabu.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import manibabu.resources.ExtentReporter;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentReports extent = ExtentReporter.getExtentReport();
	ExtentTest	test;
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		//here result holds the test name which one is currenlty executing
		
	   test=extent.createTest(result.getMethod().getMethodName());
		
		/*if we run suite by using parallel attribute results will be not proper because multiple tests running at a time , 
		so "test" object will be overriden by multiple tests so we have to resolve it   */
	//to resolve that issue we have to provde threadid for each test(method)
	   extentTest.set(test);//here i am setting Threads for alll tests  <ErrorMessageTest<thread> ->
	
	
	
		
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		//test.log(Status.PASS, "Test passed");
		extentTest.get().log(Status.PASS, "Test passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String Filepath = null;
		try {
			driver= (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.fail(result.getThrowable());
		extentTest.get().fail(result.getThrowable());
		
		try {
			Filepath =getScreenshotTest(result.getMethod().getMethodName(),driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//test.addScreenCaptureFromPath(Filepath, result.getMethod().getMethodName());
		extentTest.get().addScreenCaptureFromPath(Filepath);
		
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		//test.skip("test got skipped:"+result.getMethod().getMethodName());
		extentTest.get().skip("test got skipped :"+result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		;
	}
	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
		extent.flush();
	
	}
	
	

}
