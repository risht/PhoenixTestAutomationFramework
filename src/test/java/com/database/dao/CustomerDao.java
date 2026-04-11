package com.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

//Execute the query for the tr_Customer table! which will get details of customer

	private static final Logger LOGGER = LogManager.getLogger(CustomerDao.class);  
	
	private static final String CUSTOMER_DETAIL_QUERY="""
			
			select * from tr_customer where id= ?
			
			""";
	
	private CustomerDao() {
		
	}
	
	public static CustomerDBModel getCustomerInfo(int customerId)  {
		
	CustomerDBModel customerDBModel = null;	
		
	try {	
	
	LOGGER.info("Getting the connection from database manager");	
		
	Connection conn = 	DatabaseManager.getConnection();
	
	PreparedStatement preparedstatement = conn.prepareStatement(CUSTOMER_DETAIL_QUERY);
	
	preparedstatement.setInt(1, customerId);
	
	LOGGER.info("Executing the SQL QUERY{} ",CUSTOMER_DETAIL_QUERY);
	
	ResultSet resultSet = preparedstatement.executeQuery();
	
	while(resultSet.next())
	{
		System.out.println(resultSet.getString("first_name"));
		System.out.println(resultSet.getString("email_id"));
	
	
		customerDBModel = new CustomerDBModel(
				
			resultSet.getInt("id"),
				
			resultSet.getString("first_name"), 
			resultSet.getString("last_name"), 
			resultSet.getString("mobile_number"), 
			resultSet.getString("email_id"), 
			resultSet.getString("mobile_number_alt"), 
			resultSet.getString("email_id_alt"),
			resultSet.getInt("tr_customer_address_id")
				
			);
	
	}
	}
	
	catch(SQLException e) {
		
		LOGGER.error("Cannot convert the resultset to CustomerModel bean",e); 
		System.err.print(e.getMessage());
	}
		return customerDBModel;
	
	}
	

}
