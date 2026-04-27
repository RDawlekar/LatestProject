package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import Utilities.CommonUtils;
import WebDriverManager.Drivermanager;

public class CommonPageObjects {
//	static WebDriver driver;
//	
//	public void CommonPageObjects() {
//		this.driver=Drivermanager.getdriver();
//	}
	CommonUtils CommonUtils=new CommonUtils();
	public CommonPageObjects() {
		
	}
	
	public  void enterDataForSearch(WebDriver driver,String data) {
		
		try {
			CommonUtils.explicitWaitForVisibilityOfElement("//input[@id='twotabsearchtextbox']",20);
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
