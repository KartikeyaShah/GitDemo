package google.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import google.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement autoSuggestiveDropDown;

//	List<WebElement> country = driver.findElements(By.cssSelector("button[type='button']"));
	@FindBy(css = "button[type='button']")
	List<WebElement> country;

//	driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
	@FindBy(css = ".btnn.action__submit.ng-star-inserted")
	WebElement placeOrder;

	public void autoSuggestiveDropDownAction() {
		autoSuggestiveDropDown.sendKeys("ind");
	}

	public void selectAutoSuggestiveDropDownAction() {
		country.stream().filter(o -> o.getText().equalsIgnoreCase("India")).findFirst().ifPresent(o -> o.click());
	}

	public ConfirmationPage placeOrder() {
		placeOrder.click();
		return new ConfirmationPage(driver);
	}
}
