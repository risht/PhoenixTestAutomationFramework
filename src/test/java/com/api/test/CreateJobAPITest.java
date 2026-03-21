package com.api.test;

import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeMethod;
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
import com.api.services.JobService;

public class CreateJobAPITest {
	
	
	
	private JobService jobservice;	
	
	
	
	private CreateJobPayload createJobPayload; 
	
	
	@BeforeMethod(description="Creating createjob api request payload and instantiating the Job Service")
	public void setUp() {
		
		Customer customer = new Customer("Rishabh", "Grover", "7098345321", "", "rishabhgrover@gmail.com", "");
		
		CustomerAddress customerAddress = new CustomerAddress("D 404", "Vasant", "GK-2", "Inorbit", "Delhi", "110015", "India", "Delhi");
		
		CustomerProduct customerProduct = new CustomerProduct(getTimeWithDaysAgo(10), "26547707147372", "26547707147372", "26547707147372", getTimeWithDaysAgo(10), 
				
		Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());
		
		Problems problems = new Problems(Problem.SMARTPHONE_IS_RUNNING_SLOW.getCode(),"Battery Issues");
		
		List<Problems> problemsList = new ArrayList<Problems>();
		
		problemsList .add(problems);
		
		createJobPayload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(), Warranty_Status.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer , customerAddress, customerProduct, problemsList);
			
		jobservice = new JobService();
	}
	
	
	
	
	
	@Test(description="Verify if the Create Job API is able to create Inwarranty Job", groups= {"api","smoke","regression"}) 
	public void createJobAPITest() {
		
		
		
		jobservice.createJob(Roles.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
