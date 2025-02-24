package google.TestComponents;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import google.resources.ExtentReportNG;

public class Listeners extends BaseComponents implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReport();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
	
	public void onTestStart(ITestResult result) {
	  test = extent.createTest(result.getMethod().getMethodName());
	  extentTest.set(test);
	  }

	  public void onTestSuccess(ITestResult result) {
		  extentTest.get().log(Status.PASS, "Test Passed Successfully");
	  }

	  public void onTestFailure(ITestResult result) {
	    extentTest.get().fail(result.getThrowable());
	    
	    try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    //Screenshot
	    String filePath = null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	  }

	  public void onTestSkipped(ITestResult result) {
	
	  }

	  public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	    // not implemented
	  }

	  public void onTestFailedWithTimeout(ITestResult result) {
	    onTestFailure(result);
	  }

	  public void onStart(ITestContext context) {
	    // not implemented
	  }

	  public void onFinish(ITestContext context) {
	    extent.flush();
	  }
}
