package com.dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.api.request.models.CreateJobPayload;
import com.api.utils.CSVReaderUtility;
import com.api.utils.CreateJobBeanMapper;
import com.api.utils.FakerDataGenerator;
import com.dataproviders.api.bean.CreateJobBean;
import com.dataproviders.api.bean.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginAPIDataProvider", parallel = true)
	public static Iterator<UserBean> loginAPIDataProvider() {

		return CSVReaderUtility.loadCSV("TestData/LoginCreds.csv", UserBean.class);

	}

	@DataProvider(name = "CreateJobAPIDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobDataProvider() {

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
	
	Iterator<CreateJobPayload>payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		
	return payloadIterator;
	
		}
	
	}