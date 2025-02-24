package google.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import dev.failsafe.internal.util.Assert;
import google.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> prodCart = driver.findElements(By.cssSelector(".cartSection h3"));
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderList;

	public Boolean verifyOrderDisplay(String product) {
		Boolean match = orderList.stream().anyMatch(f -> f.getText().equalsIgnoreCase(product));
		return match;
	}
}
