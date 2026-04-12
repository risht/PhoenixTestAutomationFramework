package com.api.test.datadriven;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.startsWith;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.constant.Roles;
import com.api.request.models.CreateJobPayload;
import com.api.services.JobService;
@Listeners(com.listeners.APITestListners.class)
public class CreateJobAPIJSONDataDrivenTest { 
	
	
private JobService jobservice;	
	
	
	@BeforeMethod(description="Instantiating the Job Service")
	public void setUp() {
		
		
		jobservice = new JobService();
	}
	
	
	@Test(description="Verify if the Create Job API is able to create Inwarranty Job", groups= {"api","regression","datadriven","faker","csv"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="CreateJobAPIJsonDataProvider")
	
	
	public void createJobAPITest(CreateJobPayload createJobPayload) {
		
		
		jobservice.createJob(Roles.FD, createJobPayload)
		.then()
		.spec(responseSpec_OK())
		.body(matchesJsonSchemaInClasspath("response-schema/CreateJobAPIResponseSchema.json"))
		.body("message", equalTo("Job created successfully. "))
		.body("data.mst_service_location_id", equalTo(1))
		.body("data.job_number", startsWith("JOB_"));
		
	}

}
