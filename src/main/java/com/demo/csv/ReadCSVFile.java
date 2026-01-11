package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile {
	
	public static void main(String[] args) throws IOException, CsvException {
		
		//Code to read CSV file in Java !!!
		
		
		/*
		 * 
		 * //File csvFile = new File("C:\\Users\\RISHABH GROVER\\Desktop\\SDET with Jatin Notes\\Java Module\\PhoenixTest-AutomationFramework\\src\\main\\resources\\TestData\\LoginCreds.csv");	
		//FileReader fr = new FileReader(csvFile);
		 * 
		 * 
		 */
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/LoginCreds.csv");		
		
		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr);//CSVReader Constructor
		
		//Requires a reader!!
		
		List<String[]> datalist=csvReader.readAll();
		
		for(String[] dataArray :datalist)

		{
			//System.out.println(dataArray[0]);//First Col data
			//System.out.println(dataArray[1]);//Second Col data
			
			for(String data : dataArray)
			{
				
				
				System.out.print(data+" ");
			
		}
				System.out.println("");
	}
	

	}
	
}
