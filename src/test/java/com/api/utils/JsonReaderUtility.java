package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.request.models.UserCredentials;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtility{
	
	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtility.class); 
	
	public static <T> Iterator<T> loadJSON(String FileName, Class<T[]> clazz) {
	
	LOGGER.info("Reading the json from the file {} ",FileName);	
		
	InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(FileName);	

	ObjectMapper objectMapper = new ObjectMapper();
	
	T[] classArray;
		
	List<T>list = null;
	
	try {
		
		LOGGER.info("Converting the json data to the bean class {} ",clazz);
		
		classArray = objectMapper.readValue(is, clazz);
		
		list = Arrays.asList(classArray);
	
	} catch (IOException e) {
		
		LOGGER.error("Cannot read json from the file {} ",FileName,e);
		
		e.printStackTrace();
	}
		
	return list.iterator();
	
	
	}


	}
