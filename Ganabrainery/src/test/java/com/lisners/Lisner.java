package com.lisners;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.project.BaseTest;

public class Lisner extends BaseTest  implements ITestListener             
{

	public void onTestFailure(ITestResult result) 
	{
		if(!result.isSuccess())
		{
			try 
			{
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileHandler.copy(scrFile, new File(projectPath+"//failure//"+screenshotFileName));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			
			Reporter.log("<a href='" + projectPath+"//failure//"+screenshotFileName + "'> <img src='" + projectPath+"//failure//"+screenshotFileName + "' height='100' width='100'/> </a>");
			Reporter.log("Test has Failed:" + result.getMethod().getMethodName());
		}
	}
	
	
	
	
	public void onTestSuccess(ITestResult obj1) 
	{
		if(obj1.isSuccess())
		{
			try 
			{
				File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				FileHandler.copy(scrFile, new File(projectPath+"//success//"+screenshotFileName));
			
				Reporter.log("<a href='" + projectPath+"//success//"+screenshotFileName + "'> <img src='" + projectPath+"//success//"+screenshotFileName + "' height='100' width='100'/> </a>");
				Reporter.log("Test has Success:" + obj1.getMethod().getMethodName());
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	
	
	
	
	public void onTestStart(ITestResult arg0) 
	{
		Reporter.log("Test started Running:" + arg0.getMethod().getMethodName());
	}
	
		
	public void onTestSkipped(ITestResult result) 
	{
		Reporter.log("Test is skipped:" + result.getMethod().getMethodName());
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) 
	{
	
		
	}

	public void onFinish(ITestContext arg0) 
	{
		//Reporter.log("Test is finished:" + ((ITestResult) arg0).getMethod().getMethodName());
		
	}

}
