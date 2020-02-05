package com.suiteA;

import org.testng.annotations.Test;

public class TestA2 {
  @Test
  public void f() throws InterruptedException 
  {
	  System.out.println("starting A2");
	  Thread.sleep(3000);
	  System.out.println("Ending A2");
  }
}
