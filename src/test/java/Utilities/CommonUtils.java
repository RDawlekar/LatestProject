package Utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import StepDefinations.TestContext;

public class CommonUtils extends TestContext {

	private WebDriver driver;

	public CommonUtils()
	{
		this.driver=super.getDriver();
	}
	public void launchUrl(String Url)
	{
		driver.get(Url);
	}
	public void navigate(String Url)
	{
		driver.navigate().to(Url);
	}
	public String  getTitleOfPage(String Url)
	{
		return driver.getTitle().isBlank()?driver.getTitle() :null;
	}
	public String getSourceOfPage(String Url)
	{
		return driver.getPageSource().isBlank() ? driver.getPageSource() :null;
	}

	//window handles
	public void switchToWindowByTitle(String title)
	{
		Set<String> windowHandles=driver.getWindowHandles();
		for (String window:windowHandles)
		{
			if(window.contains(title))
			{
				driver.switchTo().window(window);
				break;
			}
		}
	}
	public void openNewTab()
	{
		driver.switchTo().newWindow(WindowType.TAB);
	}

	public void openNewWindow()
	{
		driver.switchTo().newWindow(WindowType.WINDOW);
	}


	public void maximizeWindow() {
		driver.manage().window().maximize();
	}
	public Dimension getWindowSize() {
		return driver.manage().window().getSize();
	}

	//handling alerts
	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}
	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}
	public void pageloadwait(Long duration) {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(duration));
	}
	//Handling Frames
	public void switchFrameByIndex(int index)
	{
		driver.switchTo().frame(index);
	}

	public void switchFrameByName(String name)
	{
		driver.switchTo().frame(name);
	}

	public void switchFrameByLocator(String locator)
	{
		driver.switchTo().frame(driver.findElement(By.xpath(locator)));
	}

	//Cookies

	public Set<Cookie> getCookie() {
		Set<Cookie> cookie=	driver.manage().getCookies();
		return cookie.size()>0?cookie:null;
	}
	
	//waits
	public WebElement explicitWaitForVisibilityOfElement(String locator,Long duration)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		return ele.isDisplayed()?ele:null;
	}
}
