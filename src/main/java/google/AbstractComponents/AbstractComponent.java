package google.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import google.pageobjects.CartPage;
import google.pageobjects.OrderPage;

public class AbstractComponent {

	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
	@FindBy(css="[routerlink*='/dashboard/cart']")
	WebElement cartButton;
	
//	driver.findElement(By.xpath("//button[@routerlink='/dashboard/myorders']"));
	@FindBy(xpath="//button[@routerlink='/dashboard/myorders']")
	WebElement orderButton;
	
	public void waitForElementsToAppear(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebelementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	}
	
	public CartPage goToCartPage()
	{
		cartButton.click();
		CartPage cp = new CartPage(driver);
		return cp;
	}
	
	public OrderPage goToOrderPage()
	{
		orderButton.click();
		OrderPage op = new OrderPage(driver);
		return op;
	}

}
