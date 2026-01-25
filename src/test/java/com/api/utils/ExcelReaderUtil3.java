package com.api.utils;

import java.util.Iterator;

import com.api.request.models.CreateJobPayload;
import com.dataproviders.api.bean.CreateJobBean;

public class ExcelReaderUtil3 {

	public static void main(String[] args) {
	
		Iterator<CreateJobBean> iterator=ExcelReaderUtil2.loadTestData("TestData/PhoenixTestData.xlsx", "CreateJobTestData",CreateJobBean.class);
	
		while(iterator.hasNext())
		{
			//System.out.println(iterator.next()); 
			
			CreateJobBean bean = iterator.next();
			
			CreateJobPayload createJobPayload = CreateJobBeanMapper.mapper(bean);
		
			System.out.println(createJobPayload);

		}
	}
	

}
