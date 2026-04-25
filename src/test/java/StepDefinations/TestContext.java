package StepDefinations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;

import WebDriverManager.Drivermanager;

public class TestContext {
	
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return Drivermanager.getdriver();
	}


}
