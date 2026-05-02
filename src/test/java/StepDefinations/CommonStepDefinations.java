package StepDefinations;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import PageObjects.CommonPageObjects;
import WebDriverManager.Drivermanager;
import WebDriverManager.GetConfigData;
import WebDriverManager.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class CommonStepDefinations {
	private static final String OUTPUTTYPE = null;
	WebDriver driver;
	
	
		
	@Before()
	public  void startDriver()
	{
		
		Drivermanager.intialiseBrowser();		
	}
	
	@After()
	public void quitBrowser()
	{
		WebDriverFactory.getDriver().quit();
	}
	
	@BeforeStep()
	public void tearUp() {
		
		
	}
	
	   @AfterStep
	    public void takeScreenshot(Scenario sc) throws IOException {
	        if (sc.isFailed()) {
	            WebDriver driver = WebDriverFactory.getDriver();
	            if (driver != null) {
	                try {
	                    byte[] source = ((TakesScreenshot) driver)
	                        .getScreenshotAs(OutputType.BYTES);
	                    sc.attach(source, "image/png", sc.getName());
	                } catch (Exception e) {
	                    System.out.println("Screenshot failed for thread "
	                        + Thread.currentThread().getId()
	                        + ": " + e.getMessage());
	                }
	            }
	        }
	    }
@Given("User launches URL")
public void launchURL() {
    String url = GetConfigData.getURL();
    System.out.println("Launching : " + url);
    WebDriverFactory.getDriver().get(url);
}
@When("User enter search value {string}")
public void searchData(String data) {
    CommonPageObjects commonPageObjects = new CommonPageObjects();
    commonPageObjects.enterDataForSearch(WebDriverFactory.getDriver(), data);
}
}