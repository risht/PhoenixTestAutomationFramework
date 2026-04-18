package com.api.test;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.api.constant.Roles.*;
import com.api.request.models.Details;
import com.api.services.DashBoardService;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

import static com.api.utils.SpecUtils.*;


@Listeners(com.listeners.APITestListners.class)
@Epic("Job Management")
@Feature("Job Details")



public class DetailsAPITest {

	private DashBoardService dashboardService;
	private Details detailspayload;
	
	
	@BeforeMethod(description = "Instantiating the Dashboard service and creating detail payload")
	
	public void setup() {
		
		dashboardService= new DashBoardService();
		detailspayload = new Details("created_today");
	}

	
	@Story("UserDetails should be shown")
	@Description("Verify if the Details API response is shown correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description ="Verify if Details API is working properly",groups = {"api","smoke","e2e"})
	public void detailAPITest() {
		
		dashboardService.details(FD, detailspayload)
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"));
	}
}
