package com.api.test;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.request.models.Customer;
import com.api.request.models.CustomerAddress;
import com.api.request.models.CustomerProduct;
import com.api.request.models.Problems;
import com.api.utils.DateTimeUtil;
import com.api.utils.FakerDataGenerator;
import com.database.dao.CustomerAddressDao;
import com.database.dao.CustomerDao;
import com.database.model.CustomerAddressDBModel;
import com.database.model.CustomerDBModel;
import com.github.javafaker.Faker;

public class CreateJobAPITestwithFakeData {
	
	private CreateJobPayload createJobPayload; 
	
	private static final String COUNTRY="India";
	
	@BeforeMethod(description="Creating createjob api request payload")
	public void setUp() {
		
		
		createJobPayload = FakerDataGenerator.generateFakeCreateJobData();
		
	}
	
	
	
	
	
	@Test(description="Verify if the Create Job API is able to create Inwarranty Job", groups= {"api","smoke","regression"}) 
	public void createJobAPITest() {
		
		
		
		int customerId=given()
		.spec(requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"))
		.extract().body().jsonPath().getInt("data.tr_customer_id");
		
		Customer expectedCustomerData= createJobPayload.customer();	
		CustomerDBModel actualCustomerDataInDb=CustomerDao.getCustomerInfo(customerId);
		Assert.assertEquals(actualCustomerDataInDb.getFirst_name(), expectedCustomerData.first_name());
		Assert.assertEquals(actualCustomerDataInDb.getLast_name(), expectedCustomerData.last_name());
		Assert.assertEquals(actualCustomerDataInDb.getMobile_number(), expectedCustomerData.mobile_number());
		Assert.assertEquals(actualCustomerDataInDb.getEmail_id(), expectedCustomerData.email_id());
		Assert.assertEquals(actualCustomerDataInDb.getEmail_id_alt(), expectedCustomerData.email_id_alt());
		Assert.assertEquals(actualCustomerDataInDb.getMobile_number_alt(), expectedCustomerData.mobile_number_alt());
		
	    CustomerAddressDBModel customerAddressFromDB=CustomerAddressDao.getCustomerAddressData(actualCustomerDataInDb.getTr_customer_address_id());
		
		
		Assert.assertEquals(createJobPayload.customer_address().flat_number(), customerAddressFromDB.getFlat_number());
		Assert.assertEquals(createJobPayload.customer_address().area(), customerAddressFromDB.getArea());
		Assert.assertEquals(createJobPayload.customer_address().landmark(), customerAddressFromDB.getLandmark());
		Assert.assertEquals(createJobPayload.customer_address().state(), customerAddressFromDB.getState());
		Assert.assertEquals(createJobPayload.customer_address().street_name(), customerAddressFromDB.getStreet_name());
		Assert.assertEquals(createJobPayload.customer_address().apartment_name(), customerAddressFromDB.getApartment_name());
		Assert.assertEquals(createJobPayload.customer_address().pincode(), customerAddressFromDB.getPincode());
		Assert.assertEquals(createJobPayload.customer_address().country(), customerAddressFromDB.getCountry());
	
		
		
		
	}

}
