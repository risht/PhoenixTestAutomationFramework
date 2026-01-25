package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.api.request.models.UserCredentials;
import com.dataproviders.api.bean.UserBean;
import com.poiji.bind.Poiji;

public class ExcelReaderUtil2 {

	private ExcelReaderUtil2() {
		
	}
	
	public static <T> Iterator<T> loadTestData(String sheetName, Class<T> clazz) {
		
		//Apache Poi ooxml library
		
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream("TestData\\PhoenixTestData.xlsx");
		
		XSSFWorkbook myWorkBook = null;
		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//focus on sheet
		
		XSSFSheet mySheet = myWorkBook.getSheet(sheetName);//"LoginTestData"
		
		List<T>DataList=Poiji.fromExcel(mySheet, clazz);
		return DataList.iterator();
		
	}
	

}
