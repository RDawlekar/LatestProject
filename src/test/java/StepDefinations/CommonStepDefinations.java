package StepDefinations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import PageObjects.CommonPageObjects;
import WebDriverManager.Drivermanager;
import WebDriverManager.GetConfigData;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonStepDefinations {
	static WebDriver driver;
	
	public CommonStepDefinations()
	{
		this.driver=Drivermanager.getdriver();
	}
		
	@Before()
	public static void startDriver()
	{
		
		Drivermanager.intialiseBrowser();		
	}
	
	@After()
	public static void quitBrowser()
	{
//		driver.quit();
	}
	
	@BeforeStep()
	public static void tearUp() {
		
		
	}
	
	@AfterStep()
	public static void takeScreenshot() throws IOException {
//		String destination=System.getProperty("user.dir")+"/test-output/Spark Report/screenshots/");
//		TakesScreenshot ts=(TakesScreenshot)driver;
//		File source=ts.getScreenshotAs(OutputType.FILE);
//		FileUtils.copy(source, destination);
//		
	}
	
	@Given("User launches URL")
	public void launchURL() {
		String url=GetConfigData.getURL();
		System.out.print("Launching :" + url);
		driver.get(url);		
	}
	
	@When("User enter search value {string}")
	public void searchData(String data) {
		CommonPageObjects.enterDataForSearch(driver,data);
		
	}
}
