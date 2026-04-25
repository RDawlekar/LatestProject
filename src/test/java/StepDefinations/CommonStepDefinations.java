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
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;


public class CommonStepDefinations {
	private static final String OUTPUTTYPE = null;
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
	public static void takeScreenshot(Scenario sc) throws IOException {
		String uniqueDateTime=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());		
		String destination=System.getProperty("user.dir")+"test-output/ExtentReport/ScreenShot/ScreenShot_"+uniqueDateTime+".png";
		TakesScreenshot ts=(TakesScreenshot)driver;
		byte[] source=ts.getScreenshotAs(OutputType.BYTES);
		sc.attach(source,"image/png",sc.toString());
		
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
