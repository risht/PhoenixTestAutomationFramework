package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dataproviders.api.bean.UserBean;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVReaderUtility {
	
	/*
	 * Constructor is private
	 * 
	 * static-static methods
	 * 
	 * Job:Help me read CSV file and map it to bean
	 * 
	 */
	
	
	private static final Logger LOGGER = LogManager.getLogger(CSVReaderUtility.class);  

	private CSVReaderUtility() {
		
	}
	
	    public static <T> Iterator<T> loadCSV(String pathofCSVFile,Class<T> bean)  {
	    	
	    LOGGER.info("Loading the CSV File from the path {} ", pathofCSVFile);	
	    	
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathofCSVFile);		
		
		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr);//CSVReader Constructor
		
		//Write the code to map csv to pojo
		
		LOGGER.info("Converting the CSV to bean class{} ", bean);	
		
		CsvToBean<T> CsvToBean  = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		
		
		List<T> list=CsvToBean.parse();
		//System.out.println(userList);
		return list.iterator();
}

}