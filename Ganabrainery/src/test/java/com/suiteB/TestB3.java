package com.suiteB;

import org.testng.annotations.Test;

public class TestB3 {
  @Test
  public void f() throws InterruptedException 
  {
	  System.out.println("starting B3");
	  Thread.sleep(3000);
	  System.out.println("Ending B3");
  }
}
