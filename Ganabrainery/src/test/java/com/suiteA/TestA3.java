package com.suiteA;

import org.testng.annotations.Test;

public class TestA3 {
  @Test
  public void f() throws InterruptedException 
  {
	  System.out.println("starting A3");
	  Thread.sleep(3000);
	  System.out.println("Ending A3");
  }
}
