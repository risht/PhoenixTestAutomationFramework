package com.database.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.api.request.models.CreateJobPayload;
import com.api.utils.CreateJobBeanMapper;
import com.dataproviders.api.bean.CreateJobBean;

public class DaoDemoRunner {

	public static void main(String[] args) {
		
		List<CreateJobBean> beanList = CreateJobPayloadDataDao.getCreateJobPayloadData();
		
		List<CreateJobPayload> payloadlist = new ArrayList<CreateJobPayload>();
		
		for(CreateJobBean createJobBean:beanList)
		
		{
			CreateJobPayload payload=CreateJobBeanMapper.mapper(createJobBean);
			
			payloadlist.add(payload);
		}
			
		System.out.println("-----------------------------------");
			for(CreateJobPayload payload:payloadlist)
		
			{
				System.out.println(payload);
			}
	}
}
