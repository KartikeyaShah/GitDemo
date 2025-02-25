package google;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import google.TestComponents.BaseComponents;
import google.pageobjects.CartPage;
import google.pageobjects.CheckoutPage;
import google.pageobjects.ConfirmationPage;
import google.pageobjects.LandingPage;
import google.pageobjects.OrderPage;
import google.pageobjects.ProductPage;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneSmokeTest extends BaseComponents{
	
//	String product = "IPHONE 13 PRO";
	
	@Test(dataProvider="getData")
	public void submitOrder(HashMap<String,String> input) throws IOException {
		
		// ***************************************Opening Landing Page**************************************************//
		
		// ***************************************Entering Login details************************************************//
		
		ProductPage pp = lp.loginAction(input.get("email"), input.get("password"));
		
		// ***************************************Selecting a product from the list*************************************//
		
		pp.getProductList();
		pp.addToCartProduct(input.get("product"));

		// ******************************************Opening Cart Page**************************************************//
		
		CartPage cp = pp.goToCartPage();

		// **************************************Opening Checkout Page**************************************************//

		Boolean match = cp.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);

		CheckoutPage ckp = cp.goToCheckoutPage();

		//***************************************Auto Suggestive Dropdown*******************************************//

		ckp.autoSuggestiveDropDownAction();
		ckp.selectAutoSuggestiveDropDownAction();

		ConfirmationPage cmp = ckp.placeOrder();

		//***************************************Order Confirmation*************************************************//
		
		String message = cmp.verifyText();
		Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));

	}
	
//	@Test(dependsOnMethods= {"submitOrder"}, dataProvider="getData")
//	public void OrderSequenceValidation(HashMap<String,String> input) throws IOException
//	{
//		ProductPage pp = lp.loginAction(input.get("email"), input.get("password"));
//		OrderPage op = pp.goToOrderPage();
//		Assert.assertTrue(op.verifyOrderDisplay(input.get("product")));
//		
//	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"kartikeyashah@yahoo.com", "Robert@726385k", "IPHONE 13 PRO"}, {"shradhapandey@gmail.com", "ShradhaLovesKartikeya@123", "BANARSI SAREE"}};
//	}
	
	@DataProvider
	public Object[][] getData() throws JsonMappingException, JsonProcessingException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "kartikeyashah@yahoo.com");
//		map.put("password", "Robert@726385k");
//		map.put("product", "IPHONE 13 PRO");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shradhapandey@gmail.com");
//		map1.put("password", "ShradhaLovesKartikeya@123");
//		map1.put("product", "ZARA COAT 3");
		
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\google\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
}
