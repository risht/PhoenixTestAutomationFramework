package com.api.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

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
	
	
	private CSVReaderUtility() {
		
	}
	
	    public static <T> Iterator<T> loadCSV(String pathofCSVFile,Class<T> bean)  {
			
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathofCSVFile);		
		
		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr);//CSVReader Constructor
		
		//Write the code to map csv to pojo
		
		CsvToBean<T> CsvToBean  = new CsvToBeanBuilder(csvReader)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();
		
		
		List<T> list=CsvToBean.parse();
		//System.out.println(userList);
		return list.iterator();
}

}