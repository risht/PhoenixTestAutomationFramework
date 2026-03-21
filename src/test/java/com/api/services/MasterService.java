package com.api.services;

import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class MasterService {

	
	private static final String MASTER_ENDPOINT ="/master";
	
	public Response master(Roles roles)
	{
		return given()
		.spec(requestSpecWithAuth(roles))
		.when()
		.post(MASTER_ENDPOINT);
		
	}
	
}
