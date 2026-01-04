package com.api.test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Rishabh", "Grover", "7098345321", "", "rishabhgrover@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant", "GK-2", "Inorbit", "Delhi", "110015", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct("2025-12-01T18:30:00.000Z", "30347707247876", "30347707247876", "30347707247876", "2025-12-01T18:30:00.000Z", "1", "1");
		
		Problems problems = new Problems("1","Battery Issue");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList .add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer , customerAddress, customerProduct, problemsList);
			
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
