package com.database.dao;

import java.sql.SQLException;

import com.database.model.CustomerDBModel;

public class DaoDemoRunner2 {

	public static void main(String[] args) throws SQLException {
		
		CustomerDBModel customerDbModeldata = CustomerDao.getCustomerInfo();
		System.out.println(customerDbModeldata);
		System.out.println(customerDbModeldata.getFirst_name());
		System.out.println(customerDbModeldata.getLast_name());
		System.out.println(customerDbModeldata.getMobile_number());
	}
}
