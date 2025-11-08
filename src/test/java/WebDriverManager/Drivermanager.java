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
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Drivermanager {

	private static WebDriver driver;


	public static void intialiseBrowser()
	{	
		String filename=System.getProperty("user.dir")+"//src//test//resources//debugger_address.txt";
		EdgeOptions option=new EdgeOptions();
		Map<String,Object> map = null;
		option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		option.addArguments("--start-maximized");
		WebDriverManager.edgedriver().setup();
		if(GetConfigData.getSession().toLowerCase().equals("new"))
		{
			driver =new EdgeDriver(option);
			Capabilities capabilities =((EdgeDriver) driver).getCapabilities();
			map=capabilities.asMap();	
			System.out.println("Existing browser capabilities : "+map);
			for (Map.Entry<String, Object> entry : capabilities.asMap().entrySet()) {
				System.out.println("  " + entry.getKey() + ": " + entry.getValue());
				if(entry.getKey().contains("edgeOptions"))
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
				driver =new EdgeDriver(option);
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

	public static Properties readConfig()
	{
		FileInputStream fis = null;
		Properties prop = new Properties();
		try {
			fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//resources//configurations.properties");
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
