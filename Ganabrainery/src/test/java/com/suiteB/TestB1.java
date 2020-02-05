package com.suiteB;

import org.testng.annotations.Test;

public class TestB1 {
  @Test
  public void f() throws InterruptedException 
  {
	  System.out.println("starting B1");
	  Thread.sleep(3000);
	  System.out.println("Ending B1");
  }
}
