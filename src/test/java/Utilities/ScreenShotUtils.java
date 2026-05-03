package Utilities;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import WebDriverManager.WebDriverFactory;

public class ScreenShotUtils {
	
	 public void capture( String testName) {
	        try {
	        	String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
	        	WebDriver driver = WebDriverFactory.getDriver();
	        	JavascriptExecutor js = (JavascriptExecutor) driver;
	        	js.executeScript("window.scrollBY(0,0);");
	        	TakesScreenshot ts= (TakesScreenshot)driver;
	        	File source=ts.getScreenshotAs(OutputType.FILE);
	        	FileUtils.copyFile(source,new File("./Screenshots/"+testName+timestamp+".png"));
	        	
	        }
	        catch(Exception e)
	        {
	        	
	        }
	 }

}
