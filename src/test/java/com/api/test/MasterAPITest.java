package com.api.test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.api.utils.SpecUtils;

import io.restassured.module.jsv.JsonSchemaValidator;

import static com.api.constant.Roles.*;
import static com.api.utils.AuthTokenProvider.*;
import static com.api.utils.ConfigManager.*;

public class MasterAPITest {

	@Test 
	public void masterAPITest() {
		
		given()
		.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
		.post("master")
		.then()
		.spec(SpecUtils.responseSpec_OK())
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
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterAPIResponseSchem.json"));

	}
			@Test
			public void InvaidTokenMasterAPITest() {
				
				 given()
				 .spec(SpecUtils.requestSpec())
				.log().all()	
				.when()
				.post("master")
				.then()
				.log().all()
				.spec(SpecUtils.responseSpec_TEXT(401));	
				
			}
}
