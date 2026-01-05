package com.api.test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.api.constant.Model;
import com.api.constant.OEM;
import com.api.constant.Platform;
import com.api.constant.Problem;
import com.api.constant.Product;
import com.api.constant.Roles;
import com.api.constant.ServiceLocation;
import com.api.constant.Warranty_Status;
import com.api.request.models.CreateJobPayload;
import com.api.request.models.Customer;
import com.api.request.models.CustomerAddress;
import com.api.request.models.CustomerProduct;
import com.api.request.models.Problems;
import static com.api.utils.DateTimeUtil.*;
import com.api.utils.SpecUtils;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class CreateJobAPITest {
	
	
	
	
	@Test
	public void createJobAPITest() {
		
		Customer customer = new Customer("Rishabh", "Grover", "7098345321", "", "rishabhgrover@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant", "GK-2", "Inorbit", "Delhi", "110015", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "45247707147876", "45247707147876", "45247707147876", getTimeWithDaysAgo(10), 
				
		Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Issues");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList .add(problems);
		
		CreateJobPayload createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer , customerAddress, customerProduct, problemsList);
			
		
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
