package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtility2 {

	private ExcelReaderUtility2() {
		
	}
	
	public static <T> Iterator<T> loadTestData(String xlxsFile,String sheetName, Class<T> clazz) {
		
		//Apache Poi ooxml library
		
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream(xlxsFile);
		
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//focus on sheet
		
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);//"LoginTestData"
		
		List<T>list=Poiji.fromExcel(mySheet, clazz);
		return list.iterator();
		
	}
	

}
