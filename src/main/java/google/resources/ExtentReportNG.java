package google.resources;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {

	    public static ExtentReports getReport() {
	        String path = System.getProperty("user.dir") + "\\reports\\index.html";
	        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
	        reporter.config().setReportName("Automation Results");
	        reporter.config().setDocumentTitle("Test Results");

	        ExtentReports extent = new ExtentReports();
	        extent.attachReporter(reporter);
	        extent.setSystemInfo("Tester", "Kartikeya Shah");
	        return extent; 
	    }
}
