package google;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import google.TestComponents.BaseComponents;
//import google.TestComponents.Retry;
import google.pageobjects.CartPage;
import google.pageobjects.CheckoutPage;
import google.pageobjects.ConfirmationPage;
import google.pageobjects.LandingPage;
import google.pageobjects.ProductPage;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationTest extends BaseComponents{
	
	@Test(groups= {"ErrorHandling"})
	public void incorrectCredentials() throws IOException {
		
		lp.loginAction("shah@yahoo.com", "Robert@726385");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
	
	@Test
	public void incorrectUsername() throws IOException {
		
		lp.loginAction("shah1@yahoo.com", "Robert@726385k");
		Assert.assertEquals("Incorrect email or password.", lp.getErrorMessage());
	}
}
