package com.api.test;

import static com.api.utils.ConfigManager.getProperty;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.models.UserCredentials;
import static com.api.utils.SpecUtils.*;

import static io.restassured.module.jsv.JsonSchemaValidator.*;

public class LoginAPITest {

	private UserCredentials userCredentials ;
	
	
	@BeforeMethod(description="Create the Payload for the Login API")	
	public void setup() {
		userCredentials = new UserCredentials("iamfd", "password");
	
	}
	
	
	
	@Test(description="Verifying if login api is working for FD user",groups = {"api","regression","smoke"})
	public void loginAPITest() throws IOException {		
					
		
		 given()
		.baseUri(getProperty("BASE_URI"))
		.spec(requestSpec(userCredentials))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		 .and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}
	
}
