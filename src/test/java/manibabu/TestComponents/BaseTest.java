package manibabu.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import manibabu.SeleniumFrameworkDesign.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver intializeDriver() throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//manibabu//resources//GlobalData.properties");
		prop.load(fis);
		//String browserName = prop.getProperty("browser");
		
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		//In the above we handling the code by both ways browser value will pick from mvm command as well as properties file
		System.out.println(browserName);
		if (browserName.contains("chrome")) {
			ChromeOptions options= new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			
			if(browserName.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440,900));
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;

	}
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver= intializeDriver();
		landingPage =new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
//	public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException
//	{
//		//read json into string 
//		String jsonContent =FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
//		//Here StandardCharsets.UTF_8 is encrypting formate to change json to string,we can achieve without this also but can resolve deprication with this
//		//read string into hashmap 
//		ObjectMapper mapper = new ObjectMapper(); //this mapper class can be imported from "jackson databind" package
//		//data will be store like Object[][] {{data[0]},{data[1]}} this will store list type like {value,value}
//		List<HashMap<String,String>> data= mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
//		return data;
//		
//		
//				
//	}
	public List<HashMap<String, String>> getJsonToMap(String filePath) throws IOException
	{
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		ObjectMapper mapper= new ObjectMapper();
		List<HashMap<String,String>> data = mapper.readValue(jsonContent,new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	public String getScreenshotTest(String testcaseName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source =ts.getScreenshotAs(OutputType.FILE);
		// C://Users//umanibab//eclipse-workspace//SeleniumFrameworkDesign//ScreenShots
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"//ScreenShots//"+testcaseName+".png"));
		return System.getProperty("user.dir")+"//ScreenShots//"+testcaseName+".png";
	}

}
