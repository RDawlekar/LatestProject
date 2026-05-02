package Utilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import StepDefinations.TestContext;
import WebDriverManager.WebDriverFactory;

public class CommonUtils extends TestContext {

	private WebDriver driver=WebDriverFactory.getDriver();

	
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
	public void actionsAlert(String actionsType,long duration) {
		
		Alert alert=explicitWaitForAlertToBePresent(duration);
		switch(actionsType)
		{
		case "accept":
		alert.accept();
		break;
		case "dismiss":
            alert.dismiss();
            break;
		case "enterText":
            alert.sendKeys("text to enter");
            break;
		}
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
	
	public void switchToDefaultContent() {
		driver.switchTo().defaultContent();
	}
	
	public void switchToFrameImmediataeParent() {
		driver.switchTo().parentFrame();
	}

	//Cookies

	public Set<Cookie> getCookie() {
		Set<Cookie> cookie=	driver.manage().getCookies();
		return cookie.size()>0?cookie:null;
	}

	//waits
	public  WebElement explicitWaitForVisibilityOfElement(String locator,int duration)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		WebElement ele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
		return ele.isDisplayed()?ele:null;
	}
	public  Alert explicitWaitForAlertToBePresent(long duration)
	{
		try {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(duration));
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		return alert!=null?alert:null;
		}
		catch (Exception e) {
			System.out.println("Alert is not present");
			return null;
		}
	}
	public WebElement shadowElement(String rootlocator,String elementToFind)
	{
		WebElement root=driver.findElement(By.cssSelector(rootlocator));
		SearchContext shadow=root.getShadowRoot();
		return shadow.findElement(By.cssSelector(elementToFind));

	}

	public void jScriptExecutorActions(String actionType,String locator)
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		switch(actionType) {
		case "click":
			js.executeScript("arguments[0].click", driver.findElement(By.xpath(locator)));
			break;
		case "Top":
			js.executeScript("window.scrollBy(0,0);");
			break;
		case "viewElement":
			js.executeScript("argument[0].scrollIntoView(true);",driver.findElement(By.xpath(locator)));
			break;
		case "bottom":
			js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
			break;
		}
	}
}
