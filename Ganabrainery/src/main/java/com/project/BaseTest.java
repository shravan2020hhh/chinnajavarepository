package com.project;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.io.FileHandler;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest 
{
	public static WebDriver driver;
	public static String projectPath=System.getProperty("user.dir");
	public static FileInputStream fis;
	public static Properties p;
	public static Properties e;
	public static Properties envprop;
	public static Properties orprop;
	public static ExtentReports report;
	public static ExtentTest test;
	public static String screenshotFileName;

	
	static
	{
		Date dt=new Date();
		screenshotFileName=dt.toString().replace(':', '_').replace(' ', '_')+".png";
	}
	
	public static void init() throws Exception
	{
		System.out.println("properties is loading.....");
		fis=new FileInputStream(projectPath+"//data.properties");
		p=new Properties();
		p.load(fis);
		
		fis=new FileInputStream(projectPath+"//environment.properties");
		e=new Properties();
		e.load(fis);
		String str = e.getProperty("env");
		System.out.println(str);
		
		fis=new FileInputStream(projectPath+"//"+str+".properties");
		envprop=new Properties();
		envprop.load(fis);
		//System.out.println(envprop.getProperty("axisurl"));
		
		fis=new FileInputStream(projectPath+"//or.properties");
		orprop=new Properties();
		orprop.load(fis);
		
		fis=new FileInputStream(projectPath+"//log4j.properties");
		PropertyConfigurator.configure(fis);
		
		report= ExtentManager.getInstance();
	}
	
	public static void launch(String browser)
	{
		if(browser.equals("chrome")) 
		{
			
			ChromeOptions ch=new ChromeOptions();
			ch.addArguments("user-data-dir=C:\\Users\\nprav\\AppData\\Local\\Google\\Chrome\\User Data\\Profile 1");
			ch.addArguments("--disable-notifications ");
			//System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver_win32 (1)\\chromedriver.exe");
			//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//drivers//chromedriver.exe");
			 driver=new ChromeDriver(ch);
		}
		else if(browser.equals("firefox")) 
		{
			//System.setProperty("webdriver.gecko.driver","C:\\selenium\\geckodriver-v0.26.0-win64\\geckodriver.exe" );
			//System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//drivers//geckodriver.exe");
	       
			ProfilesIni pro=new ProfilesIni();
		FirefoxProfile v = pro.getProfile("praveen ff");
		v.setPreference("dom.webnotifications.enabled", false);
			
			FirefoxOptions opt=new FirefoxOptions();
			opt.setProfile(v);
			
			driver=new FirefoxDriver(opt);
		}
		
	}
	
	
	public static void navigateUrl(String url)
	{
		driver.get(envprop.getProperty(url));
	}
	
	
	public static void elementClick(String locatorKey) 
	{
		getElement(locatorKey).click();
		//driver.findElement(By.xpath(orprop.getProperty(locatorKey))).click();
	}
	

	public static WebElement getElement(String locatorKey) 
	{
		WebElement element=null;
		if(locatorKey.endsWith("_id")) {
			element=driver.findElement(By.id(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_name")) {
			element=driver.findElement(By.name(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_classname")) {
			element=driver.findElement(By.className(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_xpath")) {
			element=driver.findElement(By.xpath(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_css")) {
			element=driver.findElement(By.cssSelector(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_linktext")) {
			element=driver.findElement(By.linkText(orprop.getProperty(locatorKey)));
		}else if(locatorKey.endsWith("_partiallinktext")) {
			element=driver.findElement(By.partialLinkText(orprop.getProperty(locatorKey)));
		}
		
		return element;
	}

	public static void typeValue(String locatorKey, String text) 
	{
		getElement(locatorKey).sendKeys(orprop.getProperty(text));
		//driver.findElement(By.name(orprop.getProperty(locatorKey))).sendKeys(orprop.getProperty(text));
	}
	

	public static void selectItem(String locatorKey, String item) 
	{
		getElement(locatorKey).sendKeys(orprop.getProperty(item));
		//driver.findElement(By.id(orprop.getProperty(locatorKey))).sendKeys(orprop.getProperty(item));
	}
	
	public static boolean verifyLink(String locatorKey, String expectedLink) 
	{
		String actualLink = getElement(locatorKey).getText();
		if(actualLink.equals(expectedLink))
			return true;
		else
			return false;
	}
	
	
	//  **************************  Reportings    ********************************
	
	public static void reportSuccess(String passMsg) {
		test.log(LogStatus.PASS, passMsg);
	}

	public static void reportFailure(String failMsg) throws Exception {
		test.log(LogStatus.FAIL, failMsg);
		takesScreenshot();
	}

	public static void takesScreenshot() throws Exception 
	{
		Date dt=new Date();
		String imgPath = dt.toString().replace(':', '_').replace(' ', '_')+".png";
		File srcFile = ((TakesScreenshot)driver). getScreenshotAs(OutputType. FILE);
		FileHandler.copy(srcFile, new File(projectPath+"//failure//"+imgPath));
		test.log(LogStatus.INFO, "Atachment --> " + test.addScreenCapture(projectPath+"//failure//"+imgPath));
	}
	
	
}
