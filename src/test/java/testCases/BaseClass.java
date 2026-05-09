package testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.random.RandomGenerator;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;  //log4j
import org.apache.logging.log4j.Logger;  //log4j
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
public class BaseClass {
	
	//public static WebDriver driver;
	public  WebDriver driver;

	public Logger logger;
	public Properties p;
	
	@BeforeClass(groups = {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os,String br) throws InterruptedException, IOException
	{
		
		//LOADING config.properties file
		FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties");	  
		p= new Properties();
	    p.load(file);
	    
	    logger = LogManager.getLogger(getClass());
	    //========selenium Grid========//
	    if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
	    {
	        DesiredCapabilities capabilities = new DesiredCapabilities();

	        //os
	        if(os.equalsIgnoreCase("windows"))
	        {
	            capabilities.setPlatform(Platform.WIN11);
	        }
	        else if(os.equalsIgnoreCase("mac"))
	        {
	            capabilities.setPlatform(Platform.MAC);
	        }
	        else if(os.equalsIgnoreCase("linux"))
	        {
	            capabilities.setPlatform(Platform.LINUX);
	        }
	        else
	        {
	            System.out.println("No matching os");
	            return;
	        }
	      //browser
	        switch(br.toLowerCase())
	        {
	            case "chrome":
	                capabilities.setBrowserName("chrome");
	                break;

	            case "edge":
	                capabilities.setBrowserName("MicrosoftEdge");
	                break;
	                
	            case "firefox":
	                capabilities.setBrowserName("firefox");
	                break;

	            default:
	                System.out.println("No matching browser");
	                return;
	        }

	        driver = new RemoteWebDriver(
	        		java.net.URI.create("http://localhost:4444").toURL(),
	                capabilities);
	    }
	    if(p.getProperty("execution_env").equalsIgnoreCase("local"))
	    {
				switch (br.toLowerCase())
				{
				case "chrome" : driver = new ChromeDriver(); break;
				case "edge"  : driver = new EdgeDriver(); break;
				case "firefox" : driver = new FirefoxDriver(); break;
				default : System.out.println("Invalid browser");return;
				}
	    }
	    //=========//
//		switch (br.toLowerCase())
//		{
//		case "chrome" : driver = new ChromeDriver(); break;
//		case "edge"  : driver = new EdgeDriver(); break;
//		case "firefox" : driver = new FirefoxDriver(); break;
//		default : System.out.println("Invalid browser");return;
//		}
		//driver = new ChromeDriver();
	    driver.manage().window().maximize();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	
	    //driver.get("http://localhost/opencart/upload/index.php");
	    //  driver.get("https://demo.opencart.com/");
		//driver.get("https://tutorialsninja.com/demo/");
	    driver.get(p.getProperty("appurl"));   //reading url from config.properties file
	}		


	@AfterClass (groups = {"Sanity","Regression","Master"})
	public void teardown()
	{
		driver.quit();
	}
	
	public String randomString(int length) {
	    String chars = "abcdefghijklmnopqrstuvwxyz";
	    RandomGenerator random = RandomGenerator.getDefault();

	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }

	    return sb.toString();
	}

	public String randomAlphaNumeric(int length) {
	    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }

	    return sb.toString();
	}
	public String randomNumber(int length) {
	    String chars = "0123456789";
	    SecureRandom random = new SecureRandom();
	    StringBuilder sb = new StringBuilder();

	    for (int i = 0; i < length; i++) {
	        sb.append(chars.charAt(random.nextInt(chars.length())));
	    }

	    return sb.toString();
	}
	public String CaptureScreen(String tname) throws IOException
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		File sourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath =System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
		File targetFile = new File(targetFilePath);
		FileUtils.copyFile(sourceFile, targetFile);
		
		return targetFilePath;
	}
}
