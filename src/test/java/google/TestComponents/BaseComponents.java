package google.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import google.pageobjects.LandingPage;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseComponents {

	public WebDriver driver;
	public LandingPage lp;
	String jsonContent;

	public WebDriver initializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\google\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browserName.equalsIgnoreCase("firefox")) {
			// Firefox
		}

		else if (browserName.equalsIgnoreCase("edge")) {
			// Edge
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	// READING JSON 
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws JsonMappingException, JsonProcessingException {
	       
    	// Reading JSON to String
    	try {
            jsonContent = FileUtils.readFileToString(
                new File(filePath),
                StandardCharsets.UTF_8);
            // Process jsonContent here
        } catch (Exception e) {
            e.printStackTrace();
        }
    	
    	// Reading String to HashMap
    	ObjectMapper mapper = new ObjectMapper();
    	List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
    	return data;
    }

	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		lp = new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	// SCREENSHOT
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@AfterMethod(alwaysRun=true)
	public void endCode()
	{
		driver.quit();
	}
}
