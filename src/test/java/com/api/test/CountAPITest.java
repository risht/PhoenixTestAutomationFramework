package com.api.test;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static com.api.utils.SpecUtils.responseSpec_TEXT;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.DashBoardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.constant.Roles.FD;

@Listeners(com.listeners.APITestListners.class)
@Epic("Job Management")
@Feature("Job Count")


public class CountAPITest {
	
	
	private  DashBoardService dashboardService;
	
	@BeforeMethod(description="Setting up the DashBoardService instance")
	public void setup() {
		dashboardService = new DashBoardService();
	}
	
	
	
	
	@Story("Job Count Data should be shown")
	@Description("Verify if the Count API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description="Verify if the Count API is giving correct response", groups= {"api","smoke","regression"}) 
	public void verifyCountAPIResponse()
	{
		dashboardService.count(FD)
		.then()
		.spec(responseSpec_OK())
		
		.body("message", equalTo("Success"))
		
		
		.body("data", notNullValue())
		
		.body("data.size()",equalTo(3))
		
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		
		.body("data.label",everyItem(not(blankOrNullString())))
		
		.body("data.key", containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
			
	}
		
		@Test(description="Verify if the Count API is giving correct status code for invalid token", groups= {"api","negative","regression"}) 
		public void countAPITest_MissingAuthToken() 
		{
			dashboardService.countWithNoAuthToken()
			.then()
			.spec(responseSpec_TEXT(401));			
			
		}
	
	
	
}
