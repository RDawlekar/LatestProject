package WebDriverManager;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drivermanager {
	
	private static WebDriver driver;
	
	
	public static void intialiseBrowser()
	{		
		EdgeOptions option=new EdgeOptions();
		option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		option.addArguments("--start-maximized");
//		option.addArguments("--headless=new");		
		
		WebDriverManager.edgedriver().setup();
		driver=new EdgeDriver(option);
		driver.get("https://www.amazon.com");		
	}	
		
public static WebDriver getdriver()
{
	
	return driver;
}
}
