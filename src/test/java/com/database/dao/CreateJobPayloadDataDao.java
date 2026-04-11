package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.dataproviders.api.bean.CreateJobBean;

public class CreateJobPayloadDataDao {

	private static final Logger LOGGER = LogManager.getLogger(CreateJobPayloadDataDao.class);  
	
	private static final String SQL_QUERY = """
			select
			mst_service_location_id,
			mst_platform_id,
			mst_warrenty_status_id,
			mst_oem_id,
			first_name,
			last_name,
			mobile_number,
			mobile_number_alt,
			email_id,
			email_id_alt,
			flat_number,
			apartment_name,
			street_name,
			landmark,
			area,
			pincode,
			country,
			state,
			serial_number,
			imei1,
			imei2,
			popurl,
			dop,
			mst_model_id,
			remark,
			mst_problem_id


			from tr_customer
			inner join tr_customer_address
			on tr_customer.tr_customer_address_id = tr_customer_address.id

			inner join tr_customer_product
			on tr_customer_product.tr_customer_id=tr_customer.id

			inner join tr_job_head
			on tr_job_head.tr_customer_id=tr_customer.id

			inner join map_job_problem
			on tr_job_head.id=map_job_problem.tr_job_head_id

			limit 5;

						""";

	private CreateJobPayloadDataDao() {
		
	}
	
	public static List<CreateJobBean> getCreateJobPayloadData()
	{
		//Need the connection -- DatabaseManagerr
		
		Connection conn=null;
		Statement statement;
		ResultSet resultset = null;
		List<CreateJobBean> beanList = new ArrayList<CreateJobBean>();
		
		
		try {
			
			LOGGER.info("Getting the connection from database manager");
			conn =DatabaseManager.getConnection();
			statement=conn.createStatement();
			LOGGER.info("Executing the SQL Query {} ",SQL_QUERY);
			
			resultset=statement.executeQuery(SQL_QUERY);
			while(resultset.next())
			{
				//System.out.println(resultset.getString("first_name"));
				CreateJobBean bean = new CreateJobBean();
				bean.setMst_service_location_id(resultset.getString("mst_service_location_id"));
				bean.setMst_platform_id(resultset.getString("mst_platform_id"));
				bean.setMst_warrenty_status_id(resultset.getString("mst_warrenty_status_id"));
				bean.setMst_oem_id("1");
				bean.setCustomer__first_name(resultset.getString("first_name"));
				bean.setCustomer__last_name(resultset.getString("last_name"));
				bean.setCustomer__mobile_number(resultset.getString("mobile_number"));
				bean.setCustomer__mobile_number_alt(resultset.getString("mobile_number_alt"));
				bean.setCustomer__email_id(resultset.getString("email_id"));
				bean.setCustomer__email_id_alt(resultset.getString("email_id_alt"));
				bean.setCustomer_address__flat_number(resultset.getString("flat_number"));
				bean.setCustomer_address__apartment_name(resultset.getString("apartment_name"));
				bean.setCustomer_address__street_name(resultset.getString("street_name"));
				bean.setCustomer_address__landmark(resultset.getString("landmark"));
				bean.setCustomer_address__area(resultset.getString("area"));
				bean.setCustomer_address__pincode(resultset.getString("pincode"));
				bean.setCustomer_address__country(resultset.getString("country"));
				bean.setCustomer_address__state(resultset.getString("state"));

				bean.setCustomer_product__serial_number(resultset.getString("serial_number"));
				bean.setCustomer_product__imei1(resultset.getString("imei1"));
				bean.setCustomer_product__imei2(resultset.getString("imei2"));
				bean.setCustomer_product__popurl(resultset.getString("popurl"));
				bean.setCustomer_product__dop(resultset.getString("dop"));
				bean.setCustomer_product__mst_model_id(("1"));
				bean.setProblems__id(resultset.getString("mst_model_id"));
				bean.setProblems__remark(resultset.getString("remark"));
				bean.setCustomer_product__product_id("1");
				beanList.add(bean);
				
			//	bean.setJob_problem__mst_problem_id(resultset.getString("mst_problem_id"));
			}
			
		} catch (SQLException e) {
			LOGGER.error("Cannot convert the resultset to bean",e); 
			e.printStackTrace();
		}
		
		for(CreateJobBean b:beanList)
		{
			System.out.println(b);
		}
		return beanList;
		
		//System.out.println(beanList.size());
			
	}
	
}
