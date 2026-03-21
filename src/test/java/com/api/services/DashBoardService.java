package com.api.services;

import static com.api.utils.SpecUtils.requestSpec;
import static com.api.utils.SpecUtils.requestSpecWithAuth;
import static io.restassured.RestAssured.given;

import com.api.constant.Roles;

import io.restassured.response.Response;

public class DashBoardService {

	private static final String COUNT_ENDPOINT ="/dashboard/count";
	
	private static final String DETAIL_ENDPOINT="/dashboard/details";
	
	
	public Response count(Roles roles) {
				
		return given().spec(requestSpecWithAuth(roles)).when().get(COUNT_ENDPOINT);
	}
	
	
	public Response countWithNoAuthToken() {
			
		return given().spec(requestSpec()).when().get(COUNT_ENDPOINT);
	}

	public Response details(Roles roles, Object payload)
	{
		return given().spec(requestSpecWithAuth(roles))
				.body(payload)
				.when().post(DETAIL_ENDPOINT);
	}
}
