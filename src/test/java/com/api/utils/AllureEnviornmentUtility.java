package com.api.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AllureEnviornmentUtility {

	private static final Logger LOGGER = LogManager.getLogger(AllureEnviornmentUtility.class); 
	
	public static void createEnviornmnetPropertiesFile() {
		
		
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();
		
		
		//enviornment.properties
		
		Properties prop  = new Properties();
		prop.setProperty("Name", "Jatin");
		
		prop.setProperty("{Project Name","Phoenix Test Automation Framework");
		prop.setProperty("Env",ConfigManager.env);
		prop.setProperty("BASE_URI",ConfigManager.getProperty("BASE_URI"));
		System.out.println(System.getProperty("os.name"));
		prop.setProperty("Operating System",System.getProperty("os.name"));
		
		FileWriter fw;
		try {
			fw = new FileWriter(folderPath+"/enviornment.properties");
			prop.store(fw, "My Properties File");
			LOGGER.info("Created the enviornment properties file at {} ",folderPath);
			
		} catch (IOException e) {
			LOGGER.error("Unable to create the enviornment.properties file ", e);
			e.printStackTrace();
		}
		
		
		
		
		
	}
}
