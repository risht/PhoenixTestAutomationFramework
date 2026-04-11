package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtility2 {

	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtility2.class);  
	
	private ExcelReaderUtility2() {
		
	}
	
	public static <T> Iterator<T> loadTestData(String xlxsFile,String sheetName, Class<T> clazz) {
		
		//Apache Poi ooxml library
		
		LOGGER.info("Reading the test data from .xlsx file {} and the sheet name is {} ",xlxsFile,sheetName);
		
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream(xlxsFile);
		
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {

			
			LOGGER.error("Cannot read the excel {}" ,xlxsFile,e.getMessage());
			
			e.printStackTrace();
		}
		
		//focus on sheet
		
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);//"LoginTestData"
		
		LOGGER.info("Cpnverting the XSSFSheet to POJO Class of type  {} ",sheetName,clazz);
		
		List<T>list=Poiji.fromExcel(mySheet, clazz);
		return list.iterator();
		
	}
	

}
