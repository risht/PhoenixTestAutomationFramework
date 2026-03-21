package com.api.test.datadriven;

import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.request.models.UserCredentials;
import com.api.services.AuthService;

public class LoginAPIJSONDataDrivenTest {
	
private AuthService authService;
	
	@BeforeMethod(description="Setting up the Auth Service reference")
	
	public void setup() {
		
		authService = new AuthService();
	
	}
	
	
	
	
	@Test(description="Verifying if login api is working for FD user",
			groups = {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIJsonDataProvider")
	
	public void loginAPITest(UserCredentials userCredentails)  {		
					
		
		authService.login(userCredentails) 
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		 .and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}
	
}
