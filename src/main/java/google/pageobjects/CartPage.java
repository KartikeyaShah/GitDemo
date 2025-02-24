package google.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dev.failsafe.internal.util.Assert;
import google.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> prodCart = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = ".cartSection h3")
	List<WebElement> prodCart;

//	driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkout;

	public Boolean verifyProductDisplay(String product) {
		Boolean match = prodCart.stream().anyMatch(f -> f.getText().equalsIgnoreCase(product));
		return match;
	}

	public CheckoutPage goToCheckoutPage() {
		checkout.click();
		CheckoutPage ckp = new CheckoutPage(driver);
		return ckp;
	}
}
