package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT ="/dashboard/count";
	
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	
	private static final Logger LOGGER = LogManager.getLogger(DashBoardService.class);  
	
	public Response count(Roles roles) {
				
		LOGGER.info("Making Request to the {} for the role{} ",COUNT_ENDPOINT,roles);
		
		return given().spec(requestSpecWithAuth(roles)).when().get(COUNT_ENDPOINT);
	}
	
	
	public Response countWithNoAuthToken() {
		
		LOGGER.info("Making Request to the {} with no Auth Token{} ",COUNT_ENDPOINT);
		
		return given().spec(requestSpec()).when().get(COUNT_ENDPOINT);
	}

	public Response details(Roles roles, Object payload) {
	
	LOGGER.info("Making Request to the {} with role and payload {} ",DETAIL_ENDPOINT,roles,payload);	
	
	{
		return given().spec(requestSpecWithAuth(roles))
				.body(payload)
				.when().post(DETAIL_ENDPOINT);
	}

	}
	
}
