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
import com.api.utils.FakerDataGenerator;
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
