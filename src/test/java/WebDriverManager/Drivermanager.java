package WebDriverManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drivermanager {

	private static WebDriver driver;


	public static void intialiseBrowser()
	{	
		String filename=System.getProperty("user.dir")+"//src//test//resources//debugger_address.txt";
		ChromeOptions option=new ChromeOptions();
		Map<String,Object> map = null;
		option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		option.addArguments("--start-maximized");
		option.addArguments("--headless");
//		WebDriverManager.edgedriver().setup();
		if(GetConfigData.getSession().toLowerCase().equals("new"))
		{
			driver =new ChromeDriver(option);
			Capabilities capabilities =((ChromeDriver) driver).getCapabilities();
			map=capabilities.asMap();	
			System.out.println("Existing browser capabilities : "+map);
			for (Map.Entry<String, Object> entry : capabilities.asMap().entrySet()) {
				System.out.println("  " + entry.getKey() + ": " + entry.getValue());
				if(entry.getKey().contains("chromeOptions"))
				{
					System.out.println(entry.getValue());
					try {

						FileOutputStream fileOut = new FileOutputStream(filename);
						ObjectOutputStream out = new ObjectOutputStream(fileOut);
						out.writeObject(entry.getValue());
						FileInputStream fileIn = new FileInputStream(filename);
						ObjectInputStream in = new ObjectInputStream(fileIn);
						System.out.println(in.readObject().toString());
					}
					catch(IOException | ClassNotFoundException e)
					{
						e.printStackTrace();
					}
					System.out.println("Stored newly created session as : "+entry.getValue());
					break;
				}
			}

		}
		else
		{
			try{

				FileInputStream fileIn = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fileIn);			
				String hostaddress=in.readObject().toString().split("=")[1].replace("}","");
				System.out.println(hostaddress);
				option.setExperimentalOption("debuggerAddress", hostaddress);
				driver =new ChromeDriver(option);
			}
			catch(ClassNotFoundException | IOException e)
			{
				e.printStackTrace();
			}


		}

	}	

	public static WebDriver getdriver()
	{

		return driver;
	}


}
