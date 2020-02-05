package com.project1;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.project.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

public class LinksTesting1 extends BaseTest
{

	@BeforeMethod(groups = {"regression","sanity"})
	@Parameters("browser")
	public void setup(String bType) throws Exception
	{
		init();
		test=report.startTest("LinksTesting1");
		test.log(LogStatus.INFO, "initializing the Properties files (data, or, envronments, log4jconfig) ........");
		
		launch(bType);
		test.log(LogStatus.INFO, "Opened the browser :- " + p.getProperty("chromebrowser"));
		
		navigateUrl("linktexturl");
		test.log(LogStatus.INFO, "Navigated to URl :- " + p.getProperty("linktexturl"));
	}
	
	
	
	
	@Test(groups = {"regression","sanity"})
	public void linktesting1()
	
	 {
		String expval="Google Images";
		
		driver.findElement(By.linkText("Images")).click();	
		Reporter.log("Clicked on Image Link");
		String actval=driver.getTitle();
		
		Assert.assertEquals(actval, expval);
		
	 }
	
	@AfterMethod(groups = {"regression","sanity"})
	public void tearDown()
	{
		driver.quit();
		  report.endTest(test);
		  report.flush();
	}

}
