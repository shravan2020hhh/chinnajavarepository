package com.project1;

import org.testng.annotations.Test;

import com.project.BaseTest;
import com.relevantcodes.extentreports.LogStatus;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.AfterMethod;

public class TC_003 extends BaseTest
{
  
  @BeforeMethod(groups = {"regression"})
  @Parameters("browser")
  public void startProcess(String bType) throws Exception {
	
	    init();
		test=report.startTest("TC_003");
		test.log(LogStatus.INFO, "Loading properties files .........");
		
		launch(bType);
		test.log(LogStatus.INFO, "Launching the Browser :-"+ p.getProperty("chromebrowser"));
		
		navigateUrl("amazonurl");
		test.log(LogStatus.INFO, "Launched the url :- "+ envprop.getProperty("amazonurl"));
	  
  }
  
  @Test(groups = {"regression"})
  public void amazon() 
  {
	  selectItem("amazondropbox_id","amazondropvalue");
		test.log(LogStatus.INFO, "Selected the item :- " + orprop.getProperty("amazondropvalue")+ " by using he property :- " + orprop.getProperty("amazondropbox_id"));
		
		typeValue("amazonsearchbox_id","amazontextvalue");
		test.log(LogStatus.INFO, "Entered the value :- " + orprop.getProperty("amazontextvalue")+ " by using he property :- " + orprop.getProperty("amazonsearchbox_id"));
		
		elementClick("amazonsearchbutton_xpath");
		test.log(LogStatus.INFO, "Clicked on element by using the property :- " + orprop.getProperty("amazonsearchbutton_xpath"));  
  }

  @AfterMethod(groups = {"regression"})
  public void endProcess() 
  {
	  driver.quit();
	  
	  report.endTest(test);
		report.flush();
  }

}
