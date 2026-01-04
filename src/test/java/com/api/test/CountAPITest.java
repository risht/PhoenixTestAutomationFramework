package com.api.test;

import static com.api.constant.Roles.FD;
import static com.api.utils.AuthTokenProvider.getToken;
import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import groovyjarjarpicocli.CommandLine.Spec;

public class CountAPITest {
	
	@Test
	public void verifyCountAPIResponse()
	{
		given()
		.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
		.get("dashboard/count")
		.then()
		.spec(SpecUtils.responseSpec_OK())
		
		.body("message", equalTo("Success"))
		
		
		.body("data", notNullValue())
		
		.body("data.size()",equalTo(3))
		
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		
		.body("data.label",everyItem(not(blankOrNullString())))
		
		.body("data.key", containsInAnyOrder("pending_fst_assignment","created_today","pending_for_delivery"))
		
		.body(matchesJsonSchemaInClasspath("response-schema/CountAPIResponseSchema-FD.json"));
			
	}
		
		@Test
		public void countAPITest_MissingAuthToken() 
		{
			 given()
			 .spec(SpecUtils.requestSpec())
			.when()
			.get("dashboard/count")
			.then()
			.spec(SpecUtils.responseSpec_TEXT(401));			
			
		}
	
	
	
}
