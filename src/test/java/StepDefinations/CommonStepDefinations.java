package StepDefinations;

import org.openqa.selenium.WebDriver;

import WebDriverManager.Drivermanager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonStepDefinations {
	WebDriver driver;
	
	public CommonStepDefinations()
	{
		this.driver=Drivermanager.getdriver();
	}
		
	@Before()
	public static void startDriver()
	{
		Drivermanager.intialiseBrowser();
		
	}
	
	@Given("User launches URL")
	public void launchURL() {
		
	}
}
