package com.stc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertyUtil {

	private static PropertyUtil instance = new PropertyUtil();
	private static Properties prop;

	private PropertyUtil() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(URLDecoder.decode(Thread
					.currentThread().getContextClassLoader()
					.getResource("selenium.properties").getPath(), "UTF-8"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	

	}
	
	public static PropertyUtil getInstance(){
	      return instance;
	}
	
	public static void reload() {
		prop = new Properties();
		try {
			prop.load(new FileInputStream(new File(URLDecoder.decode(Thread
					.currentThread().getContextClassLoader()
					.getResource("selenium.properties").getPath(), "UTF-8"))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public static String getProperty(String name) throws Exception {
		String value = prop.getProperty(name);
		if(value==null) {
			throw new Exception("PropertyUtil:getProperty() == Property '" + name + "' doesn't exist");
		} else {
			return value;
		}
	}
	
}
