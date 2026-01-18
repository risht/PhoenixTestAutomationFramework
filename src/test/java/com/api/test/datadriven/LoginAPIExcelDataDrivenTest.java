package com.api.test.datadriven;

import static com.api.utils.ConfigManager.getProperty;
import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import com.api.request.models.UserCredentials;

public class LoginAPIExcelDataDrivenTest {
	
	@Test(description="Verifying if login api is working for FD user",
			groups = {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIExcelDataProvider")
	
	public void loginAPITest(UserCredentials userCredentails)  {		
					
		
		 given()
		.baseUri(getProperty("BASE_URI"))
		.spec(requestSpec(userCredentails))
		.when()
		.post("login")
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		 .and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}
	
}
