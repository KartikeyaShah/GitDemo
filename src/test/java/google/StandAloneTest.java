package google;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import google.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest

//	public static void main(String[] args) {
//
//		String product = "IPHONE 13 PRO";
//		
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		LandingPage lp = new LandingPage(driver);
//
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//		driver.get("https://rahulshettyacademy.com/client");
//
//		// ***************************************Entering Login details************************************************//
//		driver.findElement(By.id("userEmail")).sendKeys("kartikeyashah@yahoo.com");
//		driver.findElement(By.id("userPassword")).sendKeys("Robert@726385k");
//		driver.findElement(By.id("login")).click();
//
//		// ***************************************Waiting for awhile to let the system load*****************************//
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
//
//		// ***************************************Selecting a product from the list*************************************//
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
//
//		WebElement prod = products.stream().filter(s -> s.findElement(By.cssSelector("b")).getText().equals(product)).findFirst().orElse(null);
//
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//
//		// ***************************************Waiting for awhile to let the system load****************************//
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container"))); // For appearing successfully added message
//																										
//		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating")))); // For disappearing loading animation
//
//		driver.findElement(By.cssSelector("[routerlink*='/dashboard/cart']")).click();
//
//		List<WebElement> prodCart = driver.findElements(By.cssSelector(".cartWrap"));
//
//		Boolean match = prodCart.stream().anyMatch(f -> f.getText().equalsIgnoreCase(product));
////		Assert.assertTrue(match);
//
//		driver.findElement(By.cssSelector(".totalRow button")).click();
//
//		//***************************************Auto Suggestive Dropdown*******************************************//
//		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
//		List<WebElement> country = driver.findElements(By.cssSelector("button[type='button']"));
//
//		country.stream().filter(o -> o.getText().equalsIgnoreCase("India")).findFirst().ifPresent(o -> o.click());
//
//		driver.findElement(By.cssSelector(".btnn.action__submit.ng-star-inserted")).click();
//
//		String message = driver.findElement(By.cssSelector(".hero-primary")).getText();
//		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));
//
//	}

{
	public static void main(String[] args)

	{

		String productName = "IPHONE 13 PRO";

		ChromeOptions options = new ChromeOptions();

		options.addArguments("--remote-allow-origins=*");

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver(options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.manage().window().maximize();

		driver.get("https://rahulshettyacademy.com/client");

		// LandingPage landingPage = new LandingPage(driver);

		driver.findElement(By.id("userEmail")).sendKeys("anshika@gmail.com");

		driver.findElement(By.id("userPassword")).sendKeys("Iamking@000");

		driver.findElement(By.id("login")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

		// List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class, 'mb-3')]"));

		WebElement prod = products.stream().filter(product ->

		product.findElement(By.xpath(".//div[@class='card-body']//b")).getText().equals(productName)).findFirst()
				.orElse(null);

		prod.findElement(By.xpath(".//div[@class='card-body']/button[2]")).click();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

		// ng-animating

		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));

		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));

		Assert.assertTrue(match);

		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);

		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		driver.findElement(By.cssSelector(".action__submit")).click();

		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

		driver.close();

	}
}
