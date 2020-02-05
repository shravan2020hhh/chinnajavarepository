package com.project;

import java.io.File;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager extends BaseTest
{
	public static ExtentReports extent;
	
	public static ExtentReports getInstance()
	{
		if(extent==null)
		{
			Date dt=new Date();
			System.out.println(dt);
			String filePath = dt.toString().replace(':', '_').replace(' ', '_')+".html";
			extent=new ExtentReports(projectPath+"//HTMLReports//"+filePath);
			extent.loadConfig(new File(projectPath+"//extentreportconfig.xml"));
			extent.addSystemInfo("Eniviroment", e.getProperty("env"));
		}
		return extent;
	}

}
