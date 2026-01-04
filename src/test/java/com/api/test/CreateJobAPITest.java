package com.api.test;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.pojo.CreateJobPayload;
import com.api.pojo.Customer;
import com.api.pojo.CustomerAddress;
import com.api.pojo.CustomerProduct;
import com.api.pojo.Problems;
import com.api.utils.SpecUtils;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Rishabh", "Grover", "7098345321", "", "rishabhgrover@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant", "GK-2", "Inorbit", "Delhi", "110015", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct("2025-12-01T18:30:00.000Z", "18347707247898", "18347707247898", "18347707247898", "2025-12-01T18:30:00.000Z", "1", "1");
		
		Problems problems = new Problems("1","Battery Issue");
		
		Problems[] problemsArray = new Problems[1];
		
		problemsArray[0]=problems;
		
		CreateJobPayload createJobPayload = new CreateJobPayload(0, 2, 1, 1, customer , customerAddress, customerProduct, problemsArray);
			
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(Roles.FD, createJobPayload))
		.when()
		.post("/job/create")
		.then()
		.spec(SpecUtils.responseSpec_OK());
		
	}

}
