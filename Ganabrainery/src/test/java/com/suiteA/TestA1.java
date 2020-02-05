package com.suiteA;

import org.testng.annotations.Test;

public class TestA1 {
  @Test
  public void f() throws InterruptedException 
  {
	  System.out.println("starting A1");
	  Thread.sleep(3000);
	  System.out.println("Ending A1");
  }
}
