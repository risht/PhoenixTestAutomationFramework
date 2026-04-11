package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class MasterService {

	
	private static final String MASTER_ENDPOINT ="/master";
	
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);  
	
	public Response master(Roles roles)
	{
		LOGGER.info("Making request to {} with role {} and payload {} ",MASTER_ENDPOINT,roles);
		return given()
		.spec(requestSpecWithAuth(roles))
		.when()
		.post(MASTER_ENDPOINT);
		
	}
	
}
