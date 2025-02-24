package google.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import google.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userName = driver.findElement(By.id("userEmail")).sendKeys("kartikeyashah@yahoo.com");
//	WebElement password = driver.findElement(By.id("userPassword")).sendKeys("Robert@726385k");
//	WebElement submit = driver.findElement(By.id("login")).click();

	@FindBy(id = "userEmail")
	WebElement userName;

	@FindBy(id = "userPassword")
	WebElement passwordEle;

	@FindBy(id = "login")
	WebElement submit;
	
	
	@FindBy(css = ".ng-tns-c4-0.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error")
	WebElement errorMessage;

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErrorMessage()
	{
		waitForWebelementToAppear(errorMessage);
		return errorMessage.getText();
	}

	public ProductPage loginAction(String email, String password) {
		userName.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductPage pp = new ProductPage(driver);
		return pp;
	}

}
