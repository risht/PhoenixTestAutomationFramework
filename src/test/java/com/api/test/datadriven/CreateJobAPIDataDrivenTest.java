package com.api.test.datadriven;
import static com.api.utils.DateTimeUtil.getTimeWithDaysAgo;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
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

public class CreateJobAPIDataDrivenTest { 
	
	private JobService jobservice;	
	
	
	@BeforeMethod(description="Instantiating the Job Service")
	public void setUp() {
		
		
		jobservice = new JobService();
	}
	
	
	
	
	@Test(description="Verify if the Create Job API is able to create Inwarranty Job", groups= {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="CreateJobAPIDataProvider")
	
	
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		
		
		jobservice.createJob(Roles.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		jobservice = new JobService();
	}

}
