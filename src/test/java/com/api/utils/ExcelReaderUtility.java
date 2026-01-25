package com.api.utils;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

	public static void main(String[] args) throws IOException {
		
		//Apache Poi ooxml library
		
		InputStream is = Thread.currentThread().getContextClassLoader().
				getResourceAsStream("TestData\\PhoenixTestData.xlsx");
		
		XSSFWorkbook myWorkBook = new XSSFWorkbook(is);
		
		//focus on sheet
		
		XSSFSheet mySheet = myWorkBook.getSheet("LoginTestData");
		XSSFRow myRow ;
		XSSFCell myCell ;
		//System.out.println(myCell);
		
		
		int lastRowIndex=mySheet.getLastRowNum();
		System.out.println(lastRowIndex);
	
		
		XSSFRow rowHeader = mySheet.getRow(0);
		int lastIndexoffCol= rowHeader.getLastCellNum()-1;//return the total number of Cols
		System.out.println(lastIndexoffCol);
		
		for(int rowIndex=0; rowIndex<=lastRowIndex;rowIndex++) 
		{
			for(int colIndex=0; colIndex<=lastIndexoffCol;colIndex++) 
			{
				myRow = mySheet.getRow(rowIndex);
				myCell = myRow.getCell(colIndex);
				System.out.print(myCell + " ");
			}
			System.out.println();
		}
		
	}
	

}
