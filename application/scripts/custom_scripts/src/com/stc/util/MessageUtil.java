package com.stc.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MessageUtil {
	
	static WebDriver driver;
	static Logger log;
	static {
		log = Logger.getLogger("devpinoyLogger");
	}
	
	public static void setMessage(String message)  {
		log.debug(message);
		System.out.println(message);
	}
	
}
