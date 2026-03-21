package com.api.test.datadriven;

import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.api.services.AuthService;
import com.dataproviders.api.bean.UserBean;

public class LoginAPIDataDrivenTest {
	
	private AuthService authservice;
	
	@BeforeMethod(description= "Initializing the Auth Service")
	
	public void setup() {
		authservice	 = new AuthService();
	}
	
	@Test(description="Verifying if login api is working for FD user",
			groups = {"api","regression","datadriven"},
			dataProviderClass=com.dataproviders.DataProviderUtils.class,
			dataProvider="LoginAPIDataProvider")
	
	public void loginAPITest(UserBean userbean)  {		
					
		
		authservice.login(userbean)
		.then()
		.spec(responseSpec_OK())
		.body("message", equalTo("Success"))
		 .and()
		.body(matchesJsonSchemaInClasspath("response-schema/LoginResponseSchema.json"));
		
		
	}
	
}
