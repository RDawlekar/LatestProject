package WebDriverManager;

import org.openqa.selenium.WebDriver;

public class WebDriverFactory {
	
	 private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public static WebDriver getWebDriver() {
	        return driver.get();
	    }

	    public static void setWebDriver(WebDriver dr) {
	        driver.set(dr);
	    }

	    // ✅ This is what cleans up after each scenario
	    public static void removeWebDriver() {
	        driver.remove();
	    }
	}
