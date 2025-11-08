package WebDriverManager;

import java.io.FileInputStream;
import java.util.Properties;

import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GetConfigData {
	static Properties prop=Drivermanager.readConfig();
	static String URL;
	static String session;
	static Scenario sc;
	public static String getSession() {
		setSession();
		return session;
	}
	public static void setSession() {
		session = prop.getProperty("session");
		System.out.print("Working on session :" + session);
	}
	public static String getURL() {
		setURL();
		return URL;
	}
	public static void setURL() {
		
		URL =prop.getProperty("url");
		System.out.print("Launching :" + URL);
	}
	
	static String Browser;
	public String getBrowser() {
		setBrowser();
		return Browser;
	}
	public void setBrowser() {
		Browser =prop.getProperty("browser");
		
	}
	public static String getTestDataPath() {
		// TODO Auto-generated method stub
		String path=prop.getProperty("TestDataLocation");
		return path;
	}
	public static String getScenarioName() {
		// TODO Auto-generated method stub
		return sc.getName();
	}
	

}
