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

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.request.models.Customer;
import com.api.request.models.CustomerAddress;
import com.api.request.models.CustomerProduct;
import com.api.request.models.Problems;
import com.api.utils.DateTimeUtil;
import com.github.javafaker.Faker;

public class CreateJobAPITest2 {
	
	private CreateJobPayload createJobPayload; 
	
	private static final String COUNTRY="India";
	
	@BeforeMethod(description="Creating createjob api request payload")
	public void setUp() {
		
		Faker faker = new Faker(new Locale("en-ind"));
		
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String mobileNumber= faker.numerify("70##########");
		String altmobileNumber= faker.numerify("70##########");
		String customeremailAddress= faker.internet().emailAddress();
		String altcustomeremailAddress= faker.internet().emailAddress();
		
		
		Customer customer = new Customer(fname, lname, mobileNumber, altmobileNumber, customeremailAddress, altcustomeremailAddress); 
		System.out.println(customer);
		
		
		String flatNumber = faker.numerify("###");
		String apartmentName= faker.address().streetName();
		String streetName= faker.address().streetName();
		String landmark= faker.address().streetName();
		String area= faker.address().streetName();
		String pincode= faker.numerify("#####");
		
		String state= faker.address().state();
		
		CustomerAddress customeraddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,pincode,COUNTRY,state);
		
		System.out.println(customeraddress);
		
		String dop = DateTimeUtil.getTimeWithDaysAgo(10);
		String imeiSerialNumber = faker.numerify("##############");
		String popurl = faker.internet().url();
		
		CustomerProduct customerproduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber, popurl, 1, 1);
		
		System.out.println(customerproduct);
		
		String fakeremark= faker.lorem().sentence(20);
		
		Random random = new Random();
		
		int problemId=random.nextInt(26)+1;//Zero does not come into picture
		
		Problems problems = new Problems(problemId, fakeremark);
		
		System.out.println(problems);
		
		List<Problems> problemList = new ArrayList<Problems>();
		
		problemList.add(problems);
		
		
		createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer, customeraddress, customerproduct, problemList);
		
	}
	
	
	
	
	
	@Test(description="Verify if the Create Job API is able to create Inwarranty Job", groups= {"api","smoke","regression"}) 
	public void createJobAPITest() {
		
		
		
		given()
		.spec(requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
