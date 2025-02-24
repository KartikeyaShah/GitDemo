package google.pageobjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import google.AbstractComponents.AbstractComponent;

public class ProductPage extends AbstractComponent {

	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
	@FindBy(css = ".mb-3")
	List<WebElement> products;

//	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	@FindBy(css = ".ng-animating")
	WebElement spinner;

	By productBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		waitForElementsToAppear(productBy); // We are using the AbstractComponent class here
		return products;
	}

	public WebElement getProductByName(String product) {
		WebElement prod = products.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(product))
				.findFirst().orElse(null);
		return prod;
	}

	public void addToCartProduct(String product) {
		WebElement prod = getProductByName(product);
		prod.findElement(addToCart).click();
		waitForElementsToAppear(toastMsg);
		waitForElementToDisappear(spinner);
	}
}
