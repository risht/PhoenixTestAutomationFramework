package com.api.services;

import static com.api.constant.Roles.FD;
import static com.api.utils.SpecUtils.*;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT ="/dashboard/count";
	
	public Response count(Roles roles) {
		
		
		return given()
		.spec(requestSpecWithAuth(roles))
		.when()
		.get(COUNT_ENDPOINT);
	}
	
	
	public Response countWithNoAuthToken() {
		
		
		return given()
		.spec(requestSpec())
		.when()
		.get(COUNT_ENDPOINT);
	}
}
