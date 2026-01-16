package com.api.utils;

import java.util.ArrayList;
import java.util.Iterator;

import com.api.request.models.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;

public class Demo {

	public static void main(String[] args) {
		
		Iterator<CreateJobBean> iterator = CSVReaderUtility.loadCSV("TestData/CreateJobData.csv", CreateJobBean.class);
		
		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
		
		while(iterator.hasNext()) {
		
			CreateJobBean c = iterator.next();
			CreateJobPayload payload = CreateJobBeanMapper.mapper(c);
			System.out.println(payload);			
			payloadList.add(payload);
		
		}
		
	}
}
