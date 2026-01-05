package com.api.test;

import static com.api.constant.Roles.FD;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.Test;

import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class MasterAPITest {

	@Test(description="Verify if the Master API is giving correct response", groups= {"api","smoke","regression"}) 
	public void masterAPITest() {
		
		given()
		.spec(requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data", notNullValue())
		.body("data", hasKey("mst_oem"))
		.body("data", hasKey("mst_model"))
		.body("$", hasKey("message"))
		.body("$", hasKey("data"))
		.body("data.mst_oem.size()", equalTo(2))
		.body("data.mst_model.size()", greaterThan(0))
		.body("data.mst_oem.id", everyItem(notNullValue()))
		.body("data.mst_oem.name", everyItem(notNullValue()))
		.body(matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchem.json"));

	}
			@Test(description="Verify if the Master API is giving correct status code for invalid token", groups= {"api","negative","regression"}) 
			public void InvaidTokenMasterAPITest() {
				
				 given()
				 .spec(requestSpec())
				.log().all()	
				.when()
				.post("master")
				.then()
				.log().all()
				.spec(responseSpec_TEXT(401));	
				
			}
}
