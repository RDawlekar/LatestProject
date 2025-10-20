package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import WebDriverManager.Drivermanager;

public class CommonPageObjects {
//	static WebDriver driver;
//	
//	public void CommonPageObjects() {
//		this.driver=Drivermanager.getdriver();
//	}
	
	
	public static void enterDataForSearch(WebDriver driver,String data) {
		
		try {
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
