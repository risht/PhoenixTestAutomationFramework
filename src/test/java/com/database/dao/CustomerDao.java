package com.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.database.DatabaseManager;
import com.database.model.CustomerDBModel;

public class CustomerDao {

//Execute the query for the tr_Customer table! which will get details of customer

	
	private static final String CUSTOMER_DETAIL_QUERY="""
			
			select * from tr_customer where id=180381
			
			""";
	
	public static CustomerDBModel getCustomerInfo() throws SQLException {
		
	Connection conn = 	DatabaseManager.getConnection();
	
	Statement statement = conn.createStatement();
	
	ResultSet resultSet = statement.executeQuery(CUSTOMER_DETAIL_QUERY);
	
	CustomerDBModel customerDBModel = null;
	
	while(resultSet.next())
	{
		System.out.println(resultSet.getString("first_name"));
		System.out.println(resultSet.getString("email_id"));
	
	
		customerDBModel = new CustomerDBModel(resultSet.getString("first_name"), 
			resultSet.getString("last_name"), 
			resultSet.getString("mobile_number"), 
			resultSet.getString("email_id"), 
			resultSet.getString("mobile_number_alt"), 
			resultSet.getString("email_id_alt"));
	
	}
		return customerDBModel;
	
	}
	

}
