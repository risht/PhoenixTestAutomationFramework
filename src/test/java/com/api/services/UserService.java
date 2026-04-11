package com.api.services;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class UserService {

	private static final String USERDETAILS_ENDPOINT="/userdetails";
	
	private static final Logger LOGGER = LogManager.getLogger(UserService.class); 
	
	public Response userDetails(Roles roles) {	
		
		LOGGER.info("Making request to {} with role {} and payload {} ",USERDETAILS_ENDPOINT,roles);
		Response response =given()
		.spec(requestSpecWithAuth(roles))
		.when()
		.log().headers()
		.get(USERDETAILS_ENDPOINT);
		
		return response;
		
		
	}
}
