package com.demo.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadCSVFile_MapToPojo {
	
	public static void main(String[] args) throws IOException, CsvException {
		
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("TestData/LoginCreds.csv");		
		
		InputStreamReader isr = new InputStreamReader(is);
		
		CSVReader csvReader = new CSVReader(isr);//CSVReader Constructor
		
		//Write the code to map csv to pojo
		
		CsvToBean<UserPojo> CsvToBean  = new CsvToBeanBuilder(csvReader)
				.withType(UserPojo.class)
				.withIgnoreEmptyLine(true)
				.build();
		
		
		List<UserPojo> userList=CsvToBean.parse();
		System.out.println(userList.get(1).getUsername());
		
		
		
		
		
		
	}
	

	}
	

