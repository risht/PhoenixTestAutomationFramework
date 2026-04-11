package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.api.request.models.CreateJobPayload;
import com.api.request.models.UserCredentials;
import com.api.utils.CSVReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.ExcelReaderUtility2;
import com.api.utils.FakerDataGenerator;
import com.api.utils.JsonReaderUtility;
import com.database.dao.CreateJobPayloadDataDao;
import com.database.dao.MapJobProblemDao;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class); 
	
	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {
		LOGGER.info("Loading Data from CSV file TestData/LoginCreds.csv");
		return CSVReaderUtility.loadCSV("TestData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {

		LOGGER.info("Loading Data from CSV file TestData/CreateJobData.csv");
		
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtility.loadCSV("TestData/CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;

		CreateJobPayload tempPayLoad;

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayLoad = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayLoad);
		}

		return payloadList.iterator();
	}
	
	
	@DataProvider(name = "CreateJobAPIFakerDataProvider",parallel = true)
	public static Iterator<CreateJobPayload> createJobFakeDataProvider() {
	
	String fakerCount=System.getProperty("fakerCount","5");
		
	int fakerCountInt= Integer.parseInt(fakerCount);
	
	LOGGER.info("Generating fake create job data with faker count {} ",fakerCountInt);
	
	Iterator<CreateJobPayload>payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		
	return payloadIterator;
	
		}
	
	@DataProvider(name = "LoginAPIJsonDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIJsonDataProvider() {

		
		LOGGER.info("Loading Data from Json file TestData/LogiAPITestData.json");
		
		return JsonReaderUtility.loadJSON("TestData/LogiAPITestData.json", UserBean[].class);

	}
	
	

	@DataProvider(name = "CreateJobAPIJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIJsonDataProvider() {

		LOGGER.info("Loading Data from Json file TestData/LogiAPITestData.json");
		
		return JsonReaderUtility.loadJSON("TestData/CreateJobAPIData.json", CreateJobPayload[].class);

	}
	
	@DataProvider(name = "LoginAPIExcelDataProvider", parallel = true)
	public static Iterator<UserBean> LoginAPIExcelDataProvider() {

		LOGGER.info("Loading Data from file TestData/PhoenixTestData.xlsx and sheet is LoginTestData");
		
		
		return ExcelReaderUtility2.loadTestData("TestData/PhoenixTestData.xlsx","LoginTestData", UserBean.class);

	}
	
	@DataProvider(name = "CreateJobAPIExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIExcelDataProvider() {

		
		LOGGER.info("Loading Data from file TestData/PhoenixTestData.xlsx and sheet is CreateJobTestData");
		
		Iterator<CreateJobBean> iterator=ExcelReaderUtility2.loadTestData("TestData/PhoenixTestData.xlsx", "CreateJobTestData",CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		CreateJobBean tempBean;

		CreateJobPayload tempPayLoad;

		while (iterator.hasNext()) {
			tempBean = iterator.next();
			tempPayLoad = CreateJobBeanMapper.mapper(tempBean);
			payloadList.add(tempPayLoad);
		}

		return payloadList.iterator();
	}
	
	
	@DataProvider(name = "CreateJobAPIDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> CreateJobAPIDBDataProvider() {
		
		LOGGER.info("Loading Data from Database for CreateJobPayload");
		
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();
		
		List<CreateJobPayload> payloadlist = new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean createJobBean:beanList)
		
		{
			CreateJobPayload payload=CreateJobBeanMapper.mapper(createJobBean);
			
			payloadlist.add(payload);
		}
			
		return payloadlist.iterator();
	}
	
	
	}