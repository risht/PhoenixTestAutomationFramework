package com.database.dao;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;

import java.sql.SQLException;

import com.api.constant.Model;
import com.api.constant.Product;
import com.api.request.models.CustomerProduct;
import com.database.model.CustomerProductDBModel;

public class DaoDemoRunner2 {

	public static void main(String[] args) throws SQLException {
		
//		CustomerDBModel customerDbModeldata = CustomerDao.getCustomerInfo(180381);
//		System.out.println(customerDbModeldata);
//		System.out.println(customerDbModeldata.getFirst_name());
//		System.out.println(customerDbModeldata.getLast_name());
//		System.out.println(customerDbModeldata.getMobile_number());

//		CustomerAddressDBModel customerAddressDBModel=CustomerAddressDao.getCustomerAddressData(184339);
//		System.out.println(customerAddressDBModel);
	
		CustomerProductDBModel customerProductDBModel= CustomerProductDao.getProductInfofromDB(186540);
		System.out.println(customerProductDBModel);
		
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "43247707147262", "43247707147262", "43247707147262", getTimeWithDaysAgo(10), 
				
		Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		
		System.out.println(customerProduct);

		
	}
}
