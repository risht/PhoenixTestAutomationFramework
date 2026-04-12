package com.api.test;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtils.responseSpec_OK;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.services.UserService;

@Listeners(com.listeners.APITestListners.class)
public class UserDetailsAPITest {
	
	private  UserService userService;
	
	@BeforeMethod(description="Setting up the UserService instance")
	public void setup() {
		userService = new UserService();
	}
	
	
	
	@Test(description="Verify if the Userdetails API response is shown correctly", groups= {"api","smoke","regression"})
	public void userDetailsAPITest() {
		
		
		userService.userDetails(FD)
		.then()
		.spec(responseSpec_OK())
		.and()
		.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsResponseSchema.json"));
		
		
		
	}

}
